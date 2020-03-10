package com.sophia.cake.service;

import com.philosophy.base.common.Triple;
import com.philosophy.base.util.StringsUtils;
import com.philosophy.excel.common.ExcelBase;
import com.sophia.cake.config.BaseConfigure;
import com.sophia.cake.entity.db.Junior;
import com.sophia.cake.entity.db.JuniorFormula;
import com.sophia.cake.entity.db.Senior;
import com.sophia.cake.entity.db.SeniorFormula;
import com.sophia.cake.entity.db.Superior;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020-03-10 21:28
 */
@Service
@Slf4j
public class ExcelService {
    private BaseConfigure baseConfigure;

    @Autowired
    public void setBaseConfigure(BaseConfigure baseConfigure) {
        this.baseConfigure = baseConfigure;
    }

    private Map<String, Junior> juniorMap = new HashMap<>(100);
    private Map<String, Senior> seniorMap = new HashMap<>(100);
    private Map<String, Superior> superiorMap = new HashMap<>(100);

    private List<Integer> getIndexes(Row row) {
        List<Integer> indexes = new ArrayList<>(10);
        for (Cell cell : row) {
            if (ExcelBase.getCellValue(cell).contains("材料")) {
                int index = cell.getColumnIndex();
                indexes.add(index);
            }
        }
        return indexes;
    }

    private void getJuniors(Sheet sheet) {
        sheet.removeRow(sheet.getRow(0));
        for (Row row : sheet) {
            log.debug("index = {}", row.getRowNum());
            Junior junior = new Junior();
            String name = ExcelBase.getCellValue(row.getCell(0));
            log.debug("name = {}", name);
            junior.setName(name);
            String capacity = ExcelBase.getCellValue(row.getCell(1));
            log.debug("capacity = {}", capacity);
            if (StringsUtils.isEmpty(capacity)) {
                continue;
            }
            junior.setCapacity(Integer.parseInt(capacity));
            String price = ExcelBase.getCellValue(row.getCell(2));
            log.debug("price = {}", price);
            if (StringsUtils.isEmpty(price)) {
                continue;
            }
            junior.setPrice(Float.parseFloat(price));
            Float pricePerCapacity = Float.parseFloat(price) / Integer.parseInt(capacity);
            junior.setPricePerCapacity(pricePerCapacity);
            junior.setCapacityType("克");
            log.debug("it will put {} to juniorMap", junior.getName());
            juniorMap.put(junior.getName(), junior);
        }
    }

    private void getSeniors(Sheet sheet) {
        List<Integer> indexes = getIndexes(sheet.getRow(0));
        int size = indexes.size();
        Integer[] arrays = indexes.toArray(new Integer[size]);
        log.debug("indexes is {}", Arrays.toString(arrays));
        sheet.removeRow(sheet.getRow(0));
        for (Row row : sheet) {
            log.debug("index = {}", row.getRowNum());
            Senior senior = new Senior();
            String name = ExcelBase.getCellValue(row.getCell(0));
            log.debug("name = {}", name);
            if (StringsUtils.isEmpty(name)) {
                continue;
            }
            senior.setName(name);
            Set<JuniorFormula> juniorFormulas = new HashSet<>();
            for (int index : indexes) {
                log.debug("index is = {}", index);
                String cellValue = ExcelBase.getCellValue(row.getCell(index));
                log.debug("cellValue = {}", cellValue);
                if (!StringsUtils.isEmpty(cellValue)) {
                    JuniorFormula juniorFormula = new JuniorFormula();
                    String count = ExcelBase.getCellValue(row.getCell(index + 1));
                    log.debug("count = {}", count);
                    juniorFormula.setCount(Float.parseFloat(count));
                    Junior junior = juniorMap.get(cellValue);
                    juniorFormula.setTotalPrice(Float.parseFloat(count) * junior.getPricePerCapacity());
                    juniorFormula.setJunior(junior);
                    juniorFormulas.add(juniorFormula);
                }
            }
            Float totalPrice = 0.0f;
            for (JuniorFormula formula : juniorFormulas) {
                totalPrice += formula.getTotalPrice();
            }
            senior.setTotalPrice(totalPrice);
            senior.setJuniorFormulas(juniorFormulas);
            log.debug("it will put {} to basic", senior.getName());
            seniorMap.put(senior.getName(), senior);
        }
    }

    private void getSuperiors(Sheet sheet) {
        List<Integer> indexes = getIndexes(sheet.getRow(0));
        int size = indexes.size();
        Integer[] arrays = indexes.toArray(new Integer[size]);
        log.debug("indexes is {}", Arrays.toString(arrays));
        sheet.removeRow(sheet.getRow(0));
        for (Row row : sheet) {
            log.debug("index = {}", row.getRowNum());
            Superior superior = new Superior();
            String name = ExcelBase.getCellValue(row.getCell(0));
            log.debug("name = {}", name);
            if (StringsUtils.isEmpty(name)) {
                continue;
            }
            superior.setName(name);
            Set<JuniorFormula> juniorFormulas = new HashSet<>();
            Set<SeniorFormula> seniorFormulas = new HashSet<>();
            for (int index : indexes) {
                log.debug("index is = {}", index);
                String cellValue = ExcelBase.getCellValue(row.getCell(index));
                log.debug("cellValue = {}", cellValue);
                if (!StringsUtils.isEmpty(cellValue)) {
                    Junior junior = juniorMap.get(cellValue);
                    Senior senior = seniorMap.get(cellValue);
                    String countStr = ExcelBase.getCellValue(row.getCell(index + 1));
                    Float count = Float.parseFloat(countStr);
                    log.debug("count = {}", count);
                    // 表示是原材料配方
                    if (null != junior) {
                        JuniorFormula juniorFormula = new JuniorFormula();
                        juniorFormula.setJunior(junior);
                        juniorFormula.setCount(count);
                        juniorFormula.setTotalPrice(count * junior.getPricePerCapacity());
                        juniorFormulas.add(juniorFormula);
                    }
                    // 表示是初级产品
                    if (null != senior) {
                        SeniorFormula seniorFormula = new SeniorFormula();
                        seniorFormula.setSenior(senior);
                        seniorFormula.setCount(count);
                        seniorFormula.setTotalPrice(count * senior.getTotalPrice());
                        seniorFormulas.add(seniorFormula);
                    }
                }
            }
            Float totalPrice = 0.0f;
            for (JuniorFormula formula : juniorFormulas) {
                totalPrice += formula.getTotalPrice();
            }
            for (SeniorFormula formula : seniorFormulas) {
                totalPrice += formula.getTotalPrice();
            }
            superior.setTotalPrice(totalPrice);
            superior.setJuniorFormulas(juniorFormulas);
            superior.setSeniorFormulas(seniorFormulas);
            log.debug("it will put {} to superior", superior.getName());
            superiorMap.put(superior.getName(), superior);
        }
    }

    @SneakyThrows
    public Triple<List<Junior>, List<Senior>, List<Superior>> getEntity() {
        String file = baseConfigure.getExcel();
        Path path = Paths.get(file);
        Workbook workbook = ExcelBase.openWorkbook(path);
        Sheet juniorSheet = workbook.getSheet("原材料");
        Sheet seniorSheet = workbook.getSheet("基础产品");
        Sheet superiorSheet = workbook.getSheet("中级产品");
        log.info("#############            getJuniors             #############");
        getJuniors(juniorSheet);
        log.info("#############            getSeniors            #############");
        getSeniors(seniorSheet);
        log.info("#############            getSuperiors             #############");
        getSuperiors(superiorSheet);
        return new Triple<>(new ArrayList<>(juniorMap.values()),
                new ArrayList<>(seniorMap.values()),
                new ArrayList<>(superiorMap.values()));
    }
}

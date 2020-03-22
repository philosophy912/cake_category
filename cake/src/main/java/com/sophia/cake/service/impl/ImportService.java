package com.sophia.cake.service.impl;

import com.philosophy.base.common.Triple;
import com.philosophy.base.util.StringsUtils;
import com.philosophy.excel.common.ExcelBase;
import com.sophia.cake.config.BaseConfigure;
import com.sophia.cake.entity.po.Basic;
import com.sophia.cake.entity.po.BasicFormula;
import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.po.MaterialFormula;
import com.sophia.cake.entity.po.Middle;
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
 * @date 2020-03-08 21:57
 */
@Service
@Slf4j
public class ImportService {

    private BaseConfigure baseConfigure;

    @Autowired
    public void setBaseConfigure(BaseConfigure baseConfigure) {
        this.baseConfigure = baseConfigure;
    }


    private Map<String, Material> materials = new HashMap<>(100);
    private Map<String, Basic> basics = new HashMap<>(100);
    private Map<String, Middle> middles = new HashMap<>(100);

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

    private void getMaterials(Sheet sheet) {
        sheet.removeRow(sheet.getRow(0));
        for (Row row : sheet) {
            log.debug("index = {}", row.getRowNum());
            Material material = new Material();
            String name = ExcelBase.getCellValue(row.getCell(0));
            log.debug("name = {}", name);
            material.setName(name);
            String capacity = ExcelBase.getCellValue(row.getCell(1));
            log.debug("capacity = {}", capacity);
            if (StringsUtils.isEmpty(capacity)) {
                continue;
            }
            material.setCapacity(Integer.parseInt(capacity));
            String price = ExcelBase.getCellValue(row.getCell(2));
            log.debug("price = {}", price);
            if (StringsUtils.isEmpty(price)) {
                continue;
            }
            material.setPrice(Float.parseFloat(price));
            Float pricePerCapacity = Float.parseFloat(price) / Integer.parseInt(capacity);
            material.setPricePerUnit(pricePerCapacity);
            material.setUnit("克");
            log.debug("it will put {} to basic", material);
            materials.put(material.getName(), material);
        }
    }


    private void getBasics(Sheet sheet) {
        List<Integer> indexes = getIndexes(sheet.getRow(0));
        int size = indexes.size();
        Integer[] arrays = indexes.toArray(new Integer[size]);
        log.debug("indexes is {}", Arrays.toString(arrays));
        sheet.removeRow(sheet.getRow(0));
        for (Row row : sheet) {
            log.debug("index = {}", row.getRowNum());
            Basic basic = new Basic();
            String name = ExcelBase.getCellValue(row.getCell(0));
            log.debug("name = {}", name);
            if (StringsUtils.isEmpty(name)) {
                continue;
            }
            basic.setName(name);
            Set<MaterialFormula> materialFormulas = new HashSet<>();
            for (int index : indexes) {
                log.debug("index is = {}", index);
                String materialName = ExcelBase.getCellValue(row.getCell(index));
                log.debug("materialName = {}", materialName);
                if (!StringsUtils.isEmpty(materialName)) {
                    MaterialFormula materialFormula = new MaterialFormula();
                    String count = ExcelBase.getCellValue(row.getCell(index + 1));
                    log.debug("count = {}", count);
                    materialFormula.setCount(Float.parseFloat(count));
                    Material material = materials.get(materialName);
                    materialFormula.setPrice(Float.parseFloat(count) * material.getPricePerUnit());
                    materialFormula.setMaterial(material);
                    materialFormulas.add(materialFormula);
                }
            }
            Float totalPrice = 0.0f;
            for (MaterialFormula formula : materialFormulas) {
                totalPrice += formula.getPrice();
            }
            basic.setPrice(totalPrice);
            basic.setMaterialFormulaSet(materialFormulas);
            log.debug("it will put {} to basic", basic);
            basics.put(basic.getName(), basic);
        }
    }


    private void getMiddle(Sheet sheet) {
        List<Integer> indexes = getIndexes(sheet.getRow(0));
        sheet.removeRow(sheet.getRow(0));
        for (Row row : sheet) {
            log.debug("index = {}", row.getRowNum());
            Middle middle = new Middle();
            String name = ExcelBase.getCellValue(row.getCell(0));
            log.debug("name = {}", name);
            if (StringsUtils.isEmpty(name)) {
                continue;
            }
            middle.setName(name);
            Set<MaterialFormula> materialFormulas = new HashSet<>();
            Set<BasicFormula> basicFormulas = new HashSet<>();
            for (int index : indexes) {
                String materialName = ExcelBase.getCellValue(row.getCell(index));
                log.debug("materialName = {}", materialName);
                if (!StringsUtils.isEmpty(materialName)) {
                    MaterialFormula materialFormula = new MaterialFormula();
                    BasicFormula basicFormula = new BasicFormula();
                    String count = ExcelBase.getCellValue(row.getCell(index + 1));
                    log.debug("count = {}", count);
                    // 判断是Material还是Basic
                    Material material = materials.get(materialName);
                    Basic basic = basics.get(materialName);
                    if (null != material) {
                        materialFormula.setCount(Float.parseFloat(count));
                        materialFormula.setPrice(Float.parseFloat(count) * material.getPricePerUnit());
                        materialFormula.setMaterial(material);
                        materialFormulas.add(materialFormula);
                    }
                    if (null != basic) {
                        basicFormula.setCount(Float.parseFloat(count));
                        basicFormula.setPrice(Float.parseFloat(count) * basic.getPrice());
                        basicFormula.setBasic(basic);
                        basicFormulas.add(basicFormula);
                    }
                }
            }
            Float totalPrice = 0.0f;
            for (MaterialFormula formula : materialFormulas) {
                totalPrice += formula.getPrice();
            }
            for (BasicFormula formula : basicFormulas) {
                totalPrice += formula.getPrice();
            }
            middle.setPrice(totalPrice);
            middle.setMaterialFormulaSet(materialFormulas);
            middle.setBasicFormulaSet(basicFormulas);
            log.debug("it will put {} to middles", middle);
            middles.put(middle.getName(), middle);
        }
    }


    @SneakyThrows
    public Triple<List<Material>, List<Basic>, List<Middle>> getEntity() {
        String file = baseConfigure.getExcel();
        Path path = Paths.get(file);
        Workbook workbook = ExcelBase.openWorkbook(path);
        Sheet materialSheet = workbook.getSheet("原材料");
        Sheet basicSheet = workbook.getSheet("基础产品");
        Sheet middleSheet = workbook.getSheet("中级产品");
        log.info("#############            getMaterials             #############");
        getMaterials(materialSheet);
        log.info("#############            getBasics             #############");
        getBasics(basicSheet);
        log.info("#############            getMiddle             #############");
        getMiddle(middleSheet);
        return new Triple<>(new ArrayList<>(materials.values()),
                new ArrayList<>(basics.values()),
                new ArrayList<>(middles.values()));
    }

}

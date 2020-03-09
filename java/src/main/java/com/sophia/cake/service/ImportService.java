package com.sophia.cake.service;

import com.philosophy.base.common.Triple;
import com.philosophy.base.util.StringsUtils;
import com.philosophy.excel.common.ExcelBase;
import com.sophia.cake.config.BaseConfigure;
import com.sophia.cake.entity.Basic;
import com.sophia.cake.entity.BasicProduct;
import com.sophia.cake.entity.Material;
import com.sophia.cake.entity.MaterialProduct;
import com.sophia.cake.entity.Middle;
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
import java.util.UUID;

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

    private String getUUID() {
        return UUID.randomUUID().toString();
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
            material.setId(getUUID());
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
            material.setPricePerCapacity(pricePerCapacity);
            material.setCapacityType("克");
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
            basic.setId(getUUID());
            String name = ExcelBase.getCellValue(row.getCell(0));
            log.debug("name = {}", name);
            if (StringsUtils.isEmpty(name)) {
                continue;
            }
            basic.setName(name);
            Set<MaterialProduct> materialProducts = new HashSet<>();
            for (int index : indexes) {
                log.debug("index is = {}", index);
                String materialName = ExcelBase.getCellValue(row.getCell(index));
                log.debug("materialName = {}", materialName);
                if (!StringsUtils.isEmpty(materialName)) {
                    MaterialProduct materialProduct = new MaterialProduct();
                    materialProduct.setId(getUUID());
                    String count = ExcelBase.getCellValue(row.getCell(index + 1));
                    log.debug("count = {}", count);
                    materialProduct.setCount(Float.parseFloat(count));
                    Material material = materials.get(materialName);
                    materialProduct.setTotalPrice(Float.parseFloat(count) * material.getPricePerCapacity());
                    materialProduct.setMaterial(material);
                    materialProducts.add(materialProduct);
                }
            }
            Float totalPrice = 0.0f;
            for (MaterialProduct materialProduct : materialProducts) {
                totalPrice += materialProduct.getTotalPrice();
            }
            basic.setTotalPrice(totalPrice);
            basic.setMaterialProducts(materialProducts);
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
            middle.setId(getUUID());
            String name = ExcelBase.getCellValue(row.getCell(0));
            log.debug("name = {}", name);
            if (StringsUtils.isEmpty(name)) {
                continue;
            }
            middle.setName(name);
            Set<MaterialProduct> materialProducts = new HashSet<>();
            Set<BasicProduct> basicProducts = new HashSet<>();
            for (int index : indexes) {
                String materialName = ExcelBase.getCellValue(row.getCell(index));
                log.debug("materialName = {}", materialName);
                if (!StringsUtils.isEmpty(materialName)) {
                    MaterialProduct materialProduct = new MaterialProduct();
                    materialProduct.setId(getUUID());
                    BasicProduct basicProduct = new BasicProduct();
                    basicProduct.setId(getUUID());
                    String count = ExcelBase.getCellValue(row.getCell(index + 1));
                    log.debug("count = {}", count);
                    materialProduct.setCount(Float.parseFloat(count));
                    // 判断是Material还是Basic
                    Material material = materials.get(materialName);
                    Basic basic = basics.get(materialName);
                    if (null != material) {
                        materialProduct.setTotalPrice(Float.parseFloat(count) * material.getPricePerCapacity());
                        materialProduct.setMaterial(material);
                        materialProducts.add(materialProduct);
                    }
                    if (null != basic) {
                        basicProduct.setTotalPrice(Float.parseFloat(count) * basic.getTotalPrice());
                        basicProduct.setBasic(basic);
                        basicProducts.add(basicProduct);
                    }
                }
            }
            Float totalPrice = 0.0f;
            for (MaterialProduct materialProduct : materialProducts) {
                totalPrice += materialProduct.getTotalPrice();
            }
            for (BasicProduct basicProduct : basicProducts) {
                totalPrice += basicProduct.getTotalPrice();
            }
            middle.setTotalPrice(totalPrice);
            middle.setMaterialProducts(materialProducts);
            middle.setBasicProducts(basicProducts);
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

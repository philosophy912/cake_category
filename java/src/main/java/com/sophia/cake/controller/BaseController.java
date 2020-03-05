package com.sophia.cake.controller;

import com.philosophy.base.util.StringsUtils;
import com.philosophy.excel.common.ExcelBase;
import com.sophia.cake.entity.Basic;
import com.sophia.cake.entity.Material;
import com.sophia.cake.entity.MaterialProduct;
import com.sophia.cake.service.MaterialProductService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020-03-04 22:36
 */
@Controller
@Slf4j
public class BaseController {

    private MaterialProductService materialProductService;

    @Autowired
    public void setMaterialProductService(MaterialProductService materialProductService) {
        this.materialProductService = materialProductService;
    }


    private List<Material> getMaterials(Sheet sheet) {
        List<Material> materials = new ArrayList<>(50);
        sheet.removeRow(sheet.getRow(0));
        for (Row row : sheet) {
            log.info("index = {}", row.getRowNum());
            Material material = new Material();
            String name = ExcelBase.getCellValue(row.getCell(0));
            log.info("name = {}", name);
            material.setName(name);
            String capacity = ExcelBase.getCellValue(row.getCell(1));
            log.info("capacity = {}", capacity);
            if (StringsUtils.isEmpty(capacity)) {
                break;
            }
            material.setCapacity(Integer.parseInt(capacity));
            String price = ExcelBase.getCellValue(row.getCell(2));
            log.info("price = {}", price);
            if (StringsUtils.isEmpty(price)) {
                break;
            }
            material.setPrice(Float.parseFloat(price));
            Float pricePerCapacity = Float.parseFloat(price) / Integer.parseInt(capacity);
            material.setPricePerCapacity(pricePerCapacity);
            material.setCapacityType("克");
            materials.add(material);
        }
        return materials;
    }

    private Material getMaterial(List<Material> materials, String materialName) {
        for (Material material : materials) {
            if (material.getName().equalsIgnoreCase(materialName)) {
                return material;
            }
        }
        throw new RuntimeException("not found material [" + materialName + "]");
    }


    private List<Basic> getBasics(Sheet sheet, List<Material> materials) {
        List<Basic> basics = new ArrayList<>(50);
        Row firstRow = sheet.getRow(0);
        List<Integer> materialsIndex = new ArrayList<>(10);
        for (Cell cell : firstRow) {
            if (ExcelBase.getCellValue(cell).contains("材料")) {
                int index = cell.getRowIndex();
                materialsIndex.add(index);
            }
        }
        sheet.removeRow(sheet.getRow(0));
        for (Row row : sheet) {
            log.info("index = {}", row.getRowNum());
            Basic basic = new Basic();
            String name = ExcelBase.getCellValue(row.getCell(0));
            log.info("name = {}", name);
            basic.setName(name);
            MaterialProduct materialProduct = new MaterialProduct();
            Set<Material> materialHashSet = new HashSet<>();
            for (int index : materialsIndex) {
                String materialName = ExcelBase.getCellValue(row.getCell(index));
                log.info("materialName = {}", materialName);
                if (null != materialName) {
                    String count = ExcelBase.getCellValue(row.getCell(index + 1));
                    log.info("count = {}", count);
                    materialProduct.setCount(Integer.parseInt(count));
                    Material material = getMaterial(materials, materialName);
                    materialHashSet.add(material);
                }
            }
            //todo
        }
        return basics;
    }


    @SneakyThrows
    @RequestMapping("/")
    public String update() {
        String file = "D:\\Workspace\\cake_category\\java\\src\\main\\resources\\价格核算.xlsx";
        Path path = Paths.get(file);
        Workbook workbook = ExcelBase.openWorkbook(path);
        Sheet materialSheet = workbook.getSheet("原材料");
        List<Material> materials = getMaterials(materialSheet);
        materials.forEach(material -> materialProductService.addMaterial(material));
        Sheet basicSheet = workbook.getSheet("基础产品");


        return "success";
    }
}

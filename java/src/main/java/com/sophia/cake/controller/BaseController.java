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
import java.util.LinkedList;
import java.util.List;

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

    @SneakyThrows
    @RequestMapping("/")
    public String update() {
        String file = "D:\\Workspace\\cake_category\\java\\src\\main\\resources\\价格核算.xlsx";
        Path path = Paths.get(file);
        Workbook workbook = ExcelBase.openWorkbook(path);
        Sheet sheet = workbook.getSheet("原材料");
        List<Material> materialList = new ArrayList<>(50);
        sheet.removeRow(sheet.getRow(0));
        for (Row row : sheet) {
            log.info("index = {}", row.getRowNum());
            Material material = new Material();
            String name = ExcelBase.getCellValue(row.getCell(0));
            log.info("name = {}", name);
            material.setName(name);
            String capacity = ExcelBase.getCellValue(row.getCell(1));
            log.info("capacity = {}", capacity);
            if(StringsUtils.isEmpty(capacity)){
                break;
            }
            material.setCapacity(Integer.parseInt(capacity));
            String  price =ExcelBase.getCellValue(row.getCell(2));
            log.info("price = {}", price);
            if(StringsUtils.isEmpty(price)){
                break;
            }
            material.setPrice(Float.parseFloat(price));
            Float pricePerCapacity = Float.parseFloat(price)/Integer.parseInt(capacity);
            material.setPricePerCapacity(pricePerCapacity);
            material.setCapacityType("克");
            materialList.add(material);
        }
        for (Material material : materialList) {
            materialProductService.addMaterial(material);
        }
        Sheet basicSheet = workbook.getSheet("基础产品");
        List<Basic> basics = new ArrayList<>(50);
        List<Integer> materialsIndex = new ArrayList<>(10);
        Row row = sheet.getRow(0);
        for(Cell cell: row){
            if (ExcelBase.getCellValue(cell).contains("材料")){
                materialsIndex.add(cell.getRowIndex());
            }
        }

        sheet.removeRow(sheet.getRow(0));

        return "success";
    }
}

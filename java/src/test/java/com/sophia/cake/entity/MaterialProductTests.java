package com.sophia.cake.entity;


import java.lang.reflect.Field;

/**
 * @author lizhe
 * @date 2020-03-04 21:16
 */
public class MaterialProductTests {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Material materialProduct = new Material();
        materialProduct.setId(1);
        materialProduct.setName("原材料");
        materialProduct.setCapacityType("克");
        materialProduct.setCapacity(120);
        materialProduct.setPrice(12.0f);
        materialProduct.setPricePerCapacity(0.1f);

        Material materialProduct1 = new Material();
        materialProduct1.setId(2);
        materialProduct1.setName("原材料1");
        materialProduct1.setCapacityType("升");
        materialProduct1.setCapacity(12);
        materialProduct1.setPrice(1.2f);
        materialProduct1.setPricePerCapacity(0.1f);


        Material product = new Material();
        product.setId(2);
        product.setPrice(10.0f);
        product.setCapacity(200);
        product.setPricePerCapacity(0.05f);
        Field[] fields = product.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getType());
            String name = field.getName();
            field.setAccessible(true);
            Object value = field.get(product);
            if(value!=null) {
                Field targetField = materialProduct.getClass().getDeclaredField(name);
                targetField.setAccessible(true);
                targetField.set(materialProduct, value);
            }
        }
        System.out.println(materialProduct);
        System.out.println(product);


    }

}

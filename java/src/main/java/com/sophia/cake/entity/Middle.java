package com.sophia.cake.entity;

import com.sophia.cake.api.IEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020/3/6 10:13
 **/
@Setter
@Getter
public class Middle implements IEntity {
    private String id;

    /**
     * 中级产品名称
     */
    private String name;
    /**
     * 总价
     */
    private Float totalPrice;

    /**
     * 一种中级材料可以有多个原材料
     */
    private Set<MaterialProduct> materialProducts = new HashSet<>();

    /**
     * 一种中级材料可以有多个基础材料
     */
    private Set<BasicProduct> basicProducts = new HashSet<>();

    @Override
    public void update() {
        for (MaterialProduct product : materialProducts) {
            if (null != product) {
                product.update();
                totalPrice += product.getTotalPrice();
            }

        }
        for (BasicProduct product : basicProducts) {
            if (null != product) {
                product.update();
                totalPrice += product.getTotalPrice();
            }

        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(COMMA).append(name).append(COMMA).append(totalPrice).append(COMMA);
        materialProducts.forEach(materialProduct -> sb.append(materialProduct.getId()).append(SEMICOLON));
        sb.append(COMMA);
        basicProducts.forEach(basicProduct -> sb.append(basicProduct.getId()).append(SEMICOLON));
        return sb.toString();
    }
}

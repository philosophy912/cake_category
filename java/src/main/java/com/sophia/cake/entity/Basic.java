package com.sophia.cake.entity;

import com.sophia.cake.api.IEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020/3/6 10:12
 **/
@Setter
@Getter
public class Basic implements IEntity {
    private String id;
    /**
     * 基础产品名称
     */
    private String name;

    /**
     * 总价
     */
    private Float totalPrice;

    /**
     * 一种基础材料可以有多个原材料
     * One2Many
     * 主键由One2Many维护
     */
    private Set<MaterialProduct> materialProducts = new HashSet<>();

    @Override
    public void update() {
        for (MaterialProduct product : materialProducts) {
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
        return sb.toString();
    }
}

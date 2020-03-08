package com.sophia.cake.entity;

import com.sophia.cake.api.IEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lizhe
 * @date 2020/3/6 10:16
 **/
@Setter
@Getter
public class MaterialProduct implements IEntity {
    private String id;
    /**
     * 原材料数量
     */
    private Float count;
    /**
     * 总价
     */
    private Float totalPrice;

    /**
     * 一种材料产品只对应一种原材料
     */
    private Material material;

    @Override
    public void update() {
        material.update();
        totalPrice = count * material.getPricePerCapacity();
    }

    @Override
    public String toString() {
        return id + COMMA + count + COMMA + totalPrice + COMMA + material.getId();
    }
}

package com.sophia.cake.entity;

import com.sophia.cake.api.IEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lizhe
 * @date 2020/3/6 10:09
 **/
@Setter
@Getter
public class Material implements IEntity {
    /**
     * 唯一ID号
     */
    private String id;

    /**
     * 原材料名字
     */
    private String name;

    /**
     * 容量
     */
    private Integer capacity;
    /**
     * 容量单位
     */
    private String capacityType;
    /**
     * 单价
     */
    private Float price;
    /**
     * 每单位价格
     */
    private Float pricePerCapacity;

    @Override
    public void update() {
        if (null != price && null != capacity) {
            pricePerCapacity = price / capacity;
        }
    }

    @Override
    public String toString() {
        return id + COMMA + name + COMMA + capacity + COMMA + capacityType + COMMA + price + COMMA + pricePerCapacity;
    }
}

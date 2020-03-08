package com.sophia.cake.entity;

import com.sophia.cake.api.IEntity;
import lombok.Getter;
import lombok.Setter;


/**
 * @author lizhe
 * @date 2020/3/6 10:17
 **/
@Setter
@Getter
public class BasicProduct implements IEntity {
    private String id;
    /**
     * 基础产品数量
     */
    private Float count;
    /**
     * 总价
     */
    private Float totalPrice;

    /**
     * 一种基础材料产品只对应一种基础产品
     */
    private Basic basic;

    @Override
    public void update() {
        basic.update();
        totalPrice = count * basic.getTotalPrice();
    }

    @Override
    public String toString() {
        return id + COMMA + count + COMMA + totalPrice + COMMA + basic.getId();
    }
}

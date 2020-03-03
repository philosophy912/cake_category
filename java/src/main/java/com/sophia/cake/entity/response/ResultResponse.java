package com.sophia.cake.entity.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lizhe
 * @date 2020-02-25 14:06
 */
@Setter
@Getter
public class ResultResponse<T> {
    private boolean success = true;
    private List<T> data = new ArrayList<>(1);
    private EnvData envData = new EnvData();
}

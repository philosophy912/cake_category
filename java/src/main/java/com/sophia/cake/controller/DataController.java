package com.sophia.cake.controller;

import com.philosophy.base.common.Triple;
import com.philosophy.base.entity.ResultResponse;
import com.sophia.cake.entity.Basic;
import com.sophia.cake.entity.Material;
import com.sophia.cake.entity.Middle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lizhe
 * @date 2020/3/6 13:40
 **/
@RestController
@Slf4j
public class DataController extends BaseController {


    private Triple<List<Material>, List<Basic>, List<Middle>> getEntity() {
        return importService.getEntity();
    }


    @RequestMapping("/")
    public ResultResponse<Triple<List<Material>, List<Basic>, List<Middle>>> update() {
        Triple<List<Material>, List<Basic>, List<Middle>> triple = getEntity();
        List<Triple<List<Material>, List<Basic>, List<Middle>>> data = new ArrayList<>();
        data.add(triple);
        ResultResponse<Triple<List<Material>, List<Basic>, List<Middle>>> response = new ResultResponse<>();
        response.setData(data);
        response.setSuccess(true);
        return response;
    }
}

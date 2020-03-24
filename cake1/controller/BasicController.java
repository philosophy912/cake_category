package com.sophia.cake.controller;

import com.philosophy.base.entity.ResultResponse;
import com.sophia.cake.entity.po.Basic;
import com.sophia.cake.entity.vo.BasicVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-03-18 22:17
 */
@RestController
@RequestMapping("/basic")
public class BasicController extends BaseController {

    @ResponseBody
    @RequestMapping("/query")
    public ResultResponse<Basic> query() {
        ResultResponse<Basic> response = new ResultResponse<>();
        List<Basic> basicList = basicService.findAll();
        if (basicList.size() == 0) {
            response.setSuccess(false);
        }
        response.setData(basicList);
        return response;
    }

    @ResponseBody
    @RequestMapping("/find")
    public ResultResponse<BasicVo> queryBasic() {
        ResultResponse<BasicVo> response = new ResultResponse<>();
        List<BasicVo> basicList = basicService.findBasics();
        if (basicList.size() == 0) {
            response.setSuccess(false);
        }
        response.setData(basicList);
        return response;
    }

    @ResponseBody
    @RequestMapping("/add")
    public ResultResponse<Basic> add(@RequestBody Basic basic) {
        ResultResponse<Basic> response = new ResultResponse<>();
        utils.update(basic);
        response.setSuccess(basicService.add(basic));
        return response;
    }


    @ResponseBody
    @RequestMapping("/update")
    public ResultResponse<Basic> update(@RequestBody Basic basic) {
        ResultResponse<Basic> response = new ResultResponse<>();
        response.setSuccess(basicService.update(basic));
        return response;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultResponse<Basic> delete(@RequestBody Basic basic) {
        ResultResponse<Basic> response = new ResultResponse<>();
        response.setSuccess(basicService.delete(basic.getId()));
        return response;
    }
}

# -*- coding:utf-8 -*-
# --------------------------------------------------------
# Copyright (C), 2016-2020, China TSP, All rights reserved
# --------------------------------------------------------
# @Name:        test_material.py
# @Purpose:     todo
# @Author:      lizhe
# @Created:     2020/03/30 21:53
# --------------------------------------------------------
import allure
import pytest
import requests
from common.logger import logger

# base_url = "http://10.100.193.59:9999/material"
base_url = "http://127.0.0.1/middle"


@pytest.fixture(scope='class', autouse=True)
@allure.suite('中级产品测试')
def suite():
    with allure.step('前置条件'):
        logger.debug("前置条件")
    yield suite
    with allure.step('结束动作'):
        logger.debug("结束动作")


@pytest.mark.usefixtures("suite")
class TestMiddle(object):

    @allure.story()
    def test_middle_query(self):
        with allure.step('1. 发送query请求'):
            r = requests.get(f"{base_url}/query")
            result = r.json()
            status = result["success"]
            data = result["data"]
        with allure.step('检查返回的状态码'):
            logger.info(f"result is {result}")
            assert r.status_code == 200
        with allure.step("检查返回的状态"):
            assert status
        with allure.step("检查返回的数量是否正确"):
            assert len(data) == 6

    @allure.story()
    def test_middle_delete(self):
        with allure.step('1. 发送query请求'):
            param = {"id": 1}
            response = requests.post(f"{base_url}/delete", data=param)
            result = response.json()
            status = result["success"]
            data = result["data"]
            logger.info(f"data is {data}")
        with allure.step('检查返回的状态码'):
            logger.info(f"result is {result}")
            assert response.status_code == 200
        with allure.step("检查返回的状态"):
            assert status

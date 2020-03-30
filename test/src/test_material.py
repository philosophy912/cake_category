# -*- coding:utf-8 -*-
# --------------------------------------------------------
# Copyright (C), 2016-2020, China TSP, All rights reserved
# --------------------------------------------------------
# @Name:        test_material.py
# @Purpose:     todo
# @Author:      lizhe
# @Created:     2020/03/30 21:53
# --------------------------------------------------------
import requests
from common.logger import logger

base_url = "http://127.0.0.1/material"


class TestMaterial(object):

    def test_material_query(self):
        r = requests.get(f"{base_url}/query")
        result = r.json()
        logger.info(f"result is {result}")
        status = result["success"]
        data = result["data"]
        assert r.status_code == 200
        assert status == True
        assert len(data) == 60


if __name__ == '__main__':
    test = TestMaterial()
    test.test_material_query()
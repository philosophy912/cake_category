# -*- coding:utf-8 -*-

import os
import sys

from loguru import logger as _logger

# LOG的格式
Format = "<g>[{time:YYYY-MM-DD HH:mm:ss.SSS}]</g>" \
         "<level>[{level: ^9}]</level>|" \
         "<c><i>{file: ^20}</i></c>|<e>MOD:<i>{module: ^20}</i></e>|" \
         "<m>FUNC:<i>{function: ^20}</i></m>|" \
         "<y>Line:{line: >3}</y> " \
         "<b>[LOG]</b> <level><u>{message}</u></level>"

# LOG的等级
Level = "INFO"

# LOG存放的路径
filePath = None

# 件最大存放量
ROTATION = "20 MB"

# 控制台输出
_logger.remove(0)
_logger.add(sys.stdout, level=Level, format=Format)

if filePath:
    Path = filePath
    if not os.path.isdir(filePath):
        Path = os.getcwd() + "\\logs"
        os.makedirs(Path)
    _logger.add(os.path.join(Path, "log_{time}.log"), level=Level, format=Format, rotation=ROTATION)

# 返回logger对象
logger = _logger

import os
import codecs
import configparser

#读取配置文件目录
proDir=os.path.split(os.path.realpath(__file__))[0]
configPath=os.path.join(proDir,"config.ini")

#读取配置文件
class ReadConfig:
    def __init__(self):
        fd=open(configPath)  #打开配置文件
        data=fd.read()       #读取配置文件内容

        if data[:3]==codecs.BOM_UTF8:
            data=data[3]
            file=codecs.open(configPath,"w")
            file.write(data)
            file.close()
        fd.close()

        self.cf=configparser.ConfigParser()
        self.cf.read(configPath)

    def get_email(self,name):
        value=self.cf.get("EMAIL",name)
        return value

    def get_http(self,name):
        value=self.cf.get("HTTP",name)
        return value

    def get_filePath(self,name):
        value=self.cf.get("FILE_PATH",name)
        return value




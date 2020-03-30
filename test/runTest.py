import common.configHTTP as configHTTP
import readConfig as readConfig
import common.configEmail as configEmail
import json


if __name__ == '__main__':
#初始化，参数
#读配置文件
    localconfigHTTP = configHTTP.ConfigHttp()

    localconfigHTTP.headers={'Accept':' */*','Accept-Encoding':' gzip, deflate, br','Accept-Language': 'zh-CN,zh;q=0.9',
'Connection': 'keep-alive','Host':'www.baidu.com','User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36'}
    localconfigHTTP.url="/s?"
    localconfigHTTP.params={'ie':'utf-8','csq': '1','pstg': '20','mod': '2','wd':'Python 接口测试查找response中的某个关键字的值'}
    response1=localconfigHTTP.get()
    state_code=response1.status_code
    content=response1.text
    print("本次请求的内容为："+content)











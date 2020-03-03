import axios from 'axios';
import { toast, loading } from './toast';
import Tools from './tools';
import Logger from 'chivy';
const log = new Logger('utils/request');
const service = axios.create({
    baseURL: '/',
    timeout: 5000
});
// 请求拦截器
service.interceptors.request.use(config => {
    log.debug('url is ' + JSON.stringify(config.url));
    log.debug('data is ' + JSON.stringify(config.data));
    loading();
    return config;
});
// 响应拦截器
service.interceptors.response.use(
    response => {
        clear();
        if (response.status === 200) {
            const data = response.data;
            const status = data.envData.responseStatusCode;
            switch (status) {
                case '200':
                    if (Tools.isEmpty(data.data)) {
                        return data;
                    } else {
                        if (data.data.length === 1) {
                            return data.data[0];
                        } else {
                            return data.data;
                        }
                    }
                case '4001':
                    toast('服务器故障，查询不到数据');
                    return Promise.reject(JSON.stringify({ type: status }));
                case '4002':
                    toast('创建失败');
                    return Promise.reject(JSON.stringify({ type: status }));
                case '4003':
                    toast('修改失败');
                    return Promise.reject(JSON.stringify({ type: status }));
                case '4004':
                    toast('删除失败');
                    return Promise.reject(JSON.stringify({ type: status }));
            }
        } else {
            toast('服务器发生故障[' + response.status + ']，请稍后再试', 'error');
            return Promise.reject(JSON.stringify({ type: response.status }));
        }
    },
    error => {
        toast('服务器访问超时，请联系管理员', 'fail');
        clear();
        log.error('error be found ' + JSON.stringify(error));
        return Promise.reject(error);
    }
);


export default service;
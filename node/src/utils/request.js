import axios from 'axios';
import { toast } from './toast';
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
    return config;
});
// 响应拦截器
service.interceptors.response.use(
    response => {
        if (response.status === 200) {
            const data = response.data;
            log.debug('response data is ' + JSON.stringify(data));
            const status = data.success;
            if(status){
                return data.data;
            }else {
                return Promise.reject(JSON.stringify({ type: status }));
            }
        } else {
            toast('服务器发生故障[' + response.status + ']，请稍后再试', 'error');
            return Promise.reject(JSON.stringify({ type: response.status }));
        }
    },
    error => {
        toast('服务器访问超时，请联系管理员', 'fail');
        log.error('error be found ' + JSON.stringify(error));
        return Promise.reject(error);
    }
);


export default service;
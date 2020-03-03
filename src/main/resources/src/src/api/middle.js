import service from '@/utils/request';
import Logger from 'chivy';

const log = new Logger('api/middle');

const baseUrl = "/middle/";
const query = baseUrl + 'query';
const add = baseUrl + 'add';
const update = baseUrl + 'update';
const del = baseUrl + 'delete';


// 获取所有的中级产品数据
export const queryMiddle = () => {
    return service({
        url: query,
        method: 'post',
    });
};
// 更新
export const updateMiddle = param => {
    const data = {
        name: param.name,
        unit: param.unit,
        materials: param.materials
    };
    return service({
        url: update,
        method: 'post',
        data
    });
};
// 添加
export const addMiddle = param => {
    const data = {
        name: param.name,
        unit: param.unit,
        materials: param.materials
    };
    return service({
        url: add,
        method: 'post',
        data
    });
};

// 删除
export const delMiddle = param => {
    const data = {
        id: param.id
    };
    return service({
        url: del,
        method: 'post',
        data
    })
};
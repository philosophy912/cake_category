import service from '@/utils/request';
import Logger from 'chivy';

const log = new Logger('api/material');

const baseUrl = "/material/";
const query = baseUrl + 'query';
const add =  baseUrl + 'add';
const update =  baseUrl +'update';
const del =  baseUrl +'delete';


// 获取所有的中级产品数据
export const queryMaterials = () => {
    return service({
        url: query,
        method: 'post',
    });
};
// 更新
export const updateMaterial = param => {
    const data = {
        name: param.name,
        capacity: param.capacity,
        capacityType: param.capacityType,
        price: param.price
    };
    return service({
        url: update,
        method: 'post',
        data
    });
};
// 添加
export const addMaterial = param => {
    const data = {
        name: param.name,
        capacity: param.capacity,
        capacityType: param.capacityType,
        price: param.price
    };
    return service({
        url: add,
        method: 'post',
        data
    });
};

// 删除
export const delMaterial = param => {
    const data = {
        id: param.id
    };
    return service({
        url: del,
        method: 'post',
        data
    })
};




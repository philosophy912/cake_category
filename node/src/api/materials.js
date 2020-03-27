import service from '@/utils/request';
import Logger from 'chivy';

const log = new Logger('api/material');

const baseUrl = '/material/';
const query = baseUrl + 'query';
const queryName = baseUrl + 'queryName?name=';
const add = baseUrl + 'add';
const update = baseUrl + 'update';
const del = baseUrl + 'delete';
const material = baseUrl + 'queryMaterial'


// 获取所有原材料的序号以及名称
export const queryMaterialName = () => {
    return service({
        url: material,
        method: 'get'
    })
}

// 获取单个原材料的名称
export const queryMaterialByName = name => {
    return service({
        url: queryName + name,
        method: 'get',
    });
};


// 获取所有的原材料数据
export const queryMaterials = () => {
    return service({
        url: query,
        method: 'get',
    });
};

// 更新
export const updateMaterial = param => {
    const data = {
        name: param.name,
        capacity: param.capacity,
        unit: param.unit,
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
        unit: param.unit,
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




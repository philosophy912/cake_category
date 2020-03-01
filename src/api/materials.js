import service from '@/utils/request';
import Logger from 'chivy';

const log = new Logger('api/material');

// 获取所有的中级产品数据
export const getMaterials = () => {
    return service({
        url: '/materials/getAll',
        method: 'post',
    });
};

export const updateMaterials = param => {
    const data = {
        param: param.material;
    };
    return service({
        url: '/materials/update',
        method: 'post',
        data
    });
};
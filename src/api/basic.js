import service from '@/utils/request';
import Logger from 'chivy';

const log = new Logger('api/basic');

// 获取所有的中级产品数据
export const getBasic = () => {
    return service({
        url: '/basic/getAll',
        method: 'post',
    })
};
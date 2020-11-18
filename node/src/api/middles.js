import service from '@/utils/request';
import Logger from 'chivy';

const log = new Logger('api/middle');

const baseUrl = '/middle/';
const query = baseUrl + 'query';
const queryName = baseUrl + 'queryName';
const add = baseUrl + 'add';
const update = baseUrl + 'update';
const del = baseUrl + 'delete';


// 获取所有的中级产品数据
export const queryMiddles = param => {
  const data = {
    envData: param.envData
  };
  return service({
    url: query,
    method: 'post',
    data
  });
};

// 获取单个原材料的名称
export const queryMiddleByName = param => {
  const data = {
    name: param.name,
    envData: param.envData
  };
  return service({
    url: queryName + name,
    method: 'post',
    data
  });
};

// 更新
export const updateMiddle = param => {
  const data = {
    id: param.id,
    name: param.name,
    unit: param.unit,
    capacity: param.capacity,
    formulas: param.formulas
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
    capacity: param.capacity,
    formulas: param.formulas
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
  });
};

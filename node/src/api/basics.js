import service from '@/utils/request';
import Logger from 'chivy';

const log = new Logger('api/basic');

const baseUrl = '/basic/';
const query = baseUrl + 'query';
const queryName = baseUrl + 'queryName?name=';
const add = baseUrl + 'add';
const update = baseUrl + 'update';
const del = baseUrl + 'delete';
const basic = baseUrl + 'queryBasic';


// 获取所有中级产品的序号以及名称
export const queryBasicName = () => {
  return service({
    url: basic,
    method: 'get'
  });
};

// 获取单个中级产品的名称
export const queryBasicByName = name => {
  return service({
    url: queryName + name,
    method: 'get'
  });
};

// 获取所有的中级产品数据
export const queryBasics = () => {
  return service({
    url: query,
    method: 'get'
  });
};
// 更新
export const updateBasic = param => {
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
export const addBasic = param => {
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
export const delBasic = param => {
  const data = {
    id: param.id
  };
  return service({
    url: del,
    method: 'post',
    data
  });
};

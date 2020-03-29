export default class Tools {
  // 对象属性已定义
  static isUndefined = obj => typeof (obj) === 'undefined';
  // 对象属性未定义
  static isNotUndefined = obj => !Tools.isUndefined(obj);
  // 对象是否为空
  static isEmpty = obj => (obj === null || obj === '' || typeof (obj) === 'undefined');
  // 对象非空
  static isNotEmpty = obj => !Tools.isEmpty(obj);

  static isNull = obj => obj === null;

  static isNotNull = obj => !Tools.isNull(obj);

  static isZero = obj => obj === 0;

  static isNotZero = obj => !Tools.isZero(obj);

  static isEqual = (obj1, obj2) => obj1 === obj2;

  static isNotEqual = (obj1, obj2) => !Tools.isEqual(obj1, obj2);

  static MaterialName = '原材料';

  static BasicName = '基础产品';

  static MiddleName = '中级产品';

  static saveButton = '保存';

  static addButton = '新增';

  static createMaterial() {
    const material = {
      type: Tools.MaterialName,
      value: '',
      count: 0
    };
    return material;
  };

  static createProduct() {
    const product = {
      type: Tools.BasicName,
      value: '',
      count: 0
    };
    return product;
  };

  static createRow() {
    const row = {
      name: '',
      capacity: 0,
      price: 0,
      unit: '个',
      formulas: []
    };
    return row;
  }

  static createMaterialRow(flag) {
    const row = Tools.createRow();
    if (!flag) {
      row.formulas.push(Tools.createMaterial());
    }
    // 为真返回无数据的row，
    return row;
  }

  static createProductRow(flag) {
    const row = Tools.createRow();
    if (!flag) {
      row.formulas.push(Tools.createProduct());
    }
    return row;
  }
  // 深拷贝
  static deepClone(target) {
    // 定义一个变量
    let result;
    // 如果当前需要深拷贝的是一个对象的话
    if (typeof target === 'object') {
      // 如果是一个数组的话
      if (Array.isArray(target)) {
        result = []; // 将result赋值为一个数组，并且执行遍历
        for (let i in target) {
          // 递归克隆数组中的每一项
          result.push(Tools.deepClone(target[i]))
        }
        // 判断如果当前的值是null的话；直接赋值为null
      } else if (target === null) {
        result = null;
        // 判断如果当前的值是一个RegExp对象的话，直接赋值    
      } else if (target.constructor === RegExp) {
        result = target;
      } else {
        // 否则是普通对象，直接for in循环，递归赋值对象的所有值
        result = {};
        for (let i in target) {
          result[i] = Tools.deepClone(target[i]);
        }
      }
      // 如果不是对象的话，就是基本数据类型，那么直接赋值
    } else {
      result = target;
    }
    return result;
  }
};



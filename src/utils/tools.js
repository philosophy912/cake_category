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

  static createMaterial() {
    const material = {
      type: '原材料',
      value: '',
      count: 0
    };
    return material;
  };
  static createProduct() {
    const product = {
      type: '基础产品',
      value: '',
      count: 0
    };
    return product;
  };
};

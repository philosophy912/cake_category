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
};

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

  static createRow(flag) {
    const row = {
      name: '',
      count: 0,
      price: 0,
      unit: '个',
      totalPrice: 0,
      materials: []
    };
    if (flag) {
      row.products = []
    }
    return row;
  }

  static createMaterialRow(flag) {
    const row = Tools.createRow(false);
    if(!flag){
      row.materials.push(Tools.createMaterial());
    }
    // 为真返回无数据的row，
    return row;
  }

  static createProductRow(flag) {
    const row = Tools.createRow(true);
    if (!flag) {
      row.materials.push(Tools.createMaterial());
      row.products.push(Tools.createProduct());
    }
    return row;
  }
};

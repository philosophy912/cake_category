import Logger from 'chivy';
const log = new Logger('utils/validate');
const regexMatch = (str, regex) => new RegExp(regex).test(str);
// 账号校验，不能有特殊字符
export const isNameValid = (rule, value, callback) => {
  log.debug('value content is ' + value.content);
  if (value.content === '') {
    value.must ? callback(new Error('请输入' + value.label)) : callback();
  } else if (!regexMatch(value.content, '^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$')) {
    callback(new Error('分类不能有特殊字符'));
  } else {
    callback();
  }
};
// 价格校验
export const isPriceValid = (rule, value, callback) => {
  if (value.content === '') {
    value.must ? callback(new Error('请输入' + value.label)) : callback();
  } else if (!regexMatch(value.content, '^([1-9][0-9]*)+(.[0-9]{1,2})?$')) {
    callback(new Error('请输入正确的' + value.label));
  } else {
    callback();
  }
};
// 正整数校验
export const isIntegerValid = (rule, value, callback) => {
  if (value.content === '') {
    value.must ? callback(new Error('请输入' + value.label)) : callback();
  } else if (!regexMatch(value.content, '^[+]{0,1}(\\d+)$')) {
    callback(new Error('请输入正确的' + value.label));
  } else {
    callback();
  }
};

// 选择项校验
export const isSelectValid = (rule, value, callback) => {
  if (value.content === '') {
    value.must ? callback(new Error('请选择' + value.label)) : callback();
  } else {
    callback();
  }
};

// 手机号码校验
export const isMobileValid = (rule, value, callback) => {
  if (value.content === '') {
    value.must ? callback(new Error('请输入' + value.label)) : callback();
  } else if (!regexMatch(value.content, '^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$')) {
    callback(new Error('请输入正确的手机号码'));
  } else {
    callback();
  }
};
// 电子邮箱校验
export const isEmailValid = (rule, value, callback) => {
  log.debug('value content is ' + value.content);
  if (value.content === '') {
    value.must ? callback(new Error('请输入' + value.label)) : callback();
  } else if (!regexMatch(value.content, '^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$')) {
    callback(new Error('请输入正确的电子邮箱'));
  } else {
    callback();
  }
};
// 折扣校验
export const isDiscountValid = (rule, value, callback) => {
  if (value.content === '') {
    value.must ? callback(new Error('请输入' + value.label)) : callback();
  } else if (!regexMatch(value.content, '^0.[0-9]{1,2}')) {
    callback(new Error('请输入正确折扣数字'));
  } else {
    callback();
  }
};

// 密码校验
export const isPwdValid = (rule, value, callback) => {
  if (value.content === '') {
    value.must ? callback(new Error('请输入' + value.label)) : callback();
  } else if (!regexMatch(value.content, '^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$')) {
    callback(new Error('请输入正确密码'));
  } else {
    callback();
  }
};
// 日期校验
export const isDateValid = (rule, value, callback) => {
  if (value.content === '') {
    value.must ? callback(new Error('请输入' + value.label)) : callback();
  } else if (!regexMatch(value.content, '^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$')) {
    callback(new Error('请输入正确密码'));
  } else {
    callback();
  }
};

// 日期校验
export const isCapacityTypeValid = (rule, value, callback) => {
  const capacityType = ['克', '千克' , '个', '毫升', '升', '片']
  if (value.content === '') {
    value.must ? callback(new Error('请输入' + value.label)) : callback();
  } else if (capacityType.indexOf(value.count) == -1) {
    callback(new Error('请输入容量单位'));
  } else {
    callback();
  }
};

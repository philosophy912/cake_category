import { Loading } from 'element-ui';
import { Message } from 'element-ui';

export const toast = (message, type = 'info') => {
  Message({
    message: message,
    type: type
  });
};

export const loading = (message = '') => {
  let loadingInstance = Loading.service({
    text: message
  });
  return loadingInstance;
};

export const clear = () => {
  let loadingInstance = Loading.service();
  loadingInstance.close();
};

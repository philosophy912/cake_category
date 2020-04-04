import { Loading } from 'element-ui';
import { Message } from 'element-ui';

let loadingCount = 0;
let loading;

export const toast = (message, type = 'info') => {
  Message({
    message: message,
    type: type
  });
};

const startLoading = (message = '') => {
  loading = Loading.service({
    lock: true,
    text: message,
    background: 'rgba(0, 0, 0, 0.7)'
  });
};

const endLoading = () => {
  loading.close();
}


export const showLoading = (message) => {
  if (loadingCount === 0) {
    startLoading(message);
  }
  loadingCount += 1;
}

export const hideLoading = () => {
  // debugger
  if (loadingCount <= 0) {
    return
  }
  loadingCount -= 1;
  if (loadingCount === 0) {
    endLoading()
  }
}
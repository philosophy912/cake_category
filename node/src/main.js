import Vue from 'vue';
import App from './App.vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router';
import Tools from '@/utils/tools';

Vue.use(ElementUI);
Vue.config.productionTip = false;
Vue.prototype.$tools = Tools;

new Vue({
  router,
  render: (h) => h(App),
}).$mount('#app');

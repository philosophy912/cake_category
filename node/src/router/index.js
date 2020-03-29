import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    redirect: '/material'
  },
  {
    path: '/middle',
    name: 'Middle',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import('../views/Middle.vue')
  },
  {
    path: '/material',
    name: 'Material',
    component: () => import('../views/Material.vue')
  },
  {
    path: '/basic',
    name: 'Basic',
    component: () => import('../views/Basic.vue')
  }
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

export default router;

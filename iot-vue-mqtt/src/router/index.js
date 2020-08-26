import Vue from 'vue'
import Router from 'vue-router'
import mqtt from '@/view/mqtt'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'mqtt',
      component: mqtt
    }
  ]
})

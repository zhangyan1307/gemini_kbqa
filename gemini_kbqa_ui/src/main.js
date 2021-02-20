import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'normalize.css/normalize.css';
import './styles/index.scss';
import App from './App.vue';
import router from './router';

Vue.use(ElementUI);
import Chat from 'jwchat';
/* 在 0.2.041 之前的版本需要引入 css */
// import 'jwchat/lib/JwChat.css';
Vue.use(Chat);


Vue.config.productionTip = false;

new Vue({
  router,
  render: h => h(App),
}).$mount('#app');

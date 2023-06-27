import { createApp } from "vue";
import { createPinia } from "pinia";
import App from "./App.vue";
import router from "./router";
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import globalComponents from "./global-components";
import utils from "./utils";
import "./assets/css/app.css";
import axios from 'axios';
import echarts from "@/global-components/echarts/echarts";


axios.defaults.withCredentials = true;
axios.defaults.baseURL = "http://localhost:10086/"; // 设置axios的基础请求路径
axios.defaults.timeout = 2000; // 设置axios的请求时间
const app = createApp(App);
app.config.globalProperties.$http=axios;
app.config.globalProperties.$echarts=echarts;

app.use(router)
app.use(createPinia());
app.use(ElementPlus);
globalComponents(app);
utils(app);




app.mount("#app");

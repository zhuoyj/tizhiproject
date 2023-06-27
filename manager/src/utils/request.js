import axios from "axios";
const service=axios.create({
    baseURL:"http://127.0.0.1:10086"
})
//请求拦截
service.interceptors.request.use(config=>config);
//响应拦截
service.interceptors.response.use(res=>{
    return res;
},error => {
    return Promise.reject(error);
})
export default service;

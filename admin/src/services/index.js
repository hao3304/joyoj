import axios from 'axios'
import Cookies from 'js-cookie';

const ROOT = '';
const instance = axios.create({
    baseURL: ROOT + '/api',
    timeout: 8000,
    headers: {'Content-Type': 'application/json',Authorization:Cookies.get("adminToken","")?'Bearer ' + Cookies.get("adminToken",""): undefined}
});

instance.interceptors.response.use((res) =>{
  return res.data;
    // if(res.errno == 0){
    //     return res.data
    // }else{
    //
    //     switch (res.errno) {
    //         case 320106:
    //         case 320104:{
    //             return res.message;
    //         }break;
    //     }
    //     Vue.$Message.error(res.data.message);
    //     return 'error';
    // }
}, (error) => {
    const {response} = error;
    //
    // Vue.$Notice.error({
    //     title: "警告",
    //     desc: response.data.Message||response.data,
    //     duration:4
    // });
    return Promise.reject(error);
});

export default instance;

export const RESTful = function (target) {
    return  {
        findAll:params=> instance.get(`/${target}`,{params}),
        add:params=> instance.post(`/${target}`,params),
        remove:id=> instance.delete(`/${target}/${id}`),
        update:(id,params)=> instance.put(`/${target}/${id}`,params),
        findOne: (id)=> instance.get(`/${target}/${id}`)
    }
}

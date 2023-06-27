import { defineStore } from "pinia";
import axios from "@/utils/request";
import {ref} from "vue";
import menu from "lucide-vue-next/dist/esm/icons/menu";

export const useSideMenuStore = defineStore("sideMenu", {
  state: () => ({
    menus: [

    ],
  }),
  actions:{
    async three(){
      const  arr=[];
      const user = JSON.parse(sessionStorage.getItem('admin'));
      let res=await axios.get('/menu/getMenus/' + user.role_id);
       res.data.forEach(c=>{
        if (c!=null){
          arr.push({
            icon:c.icon,
            pageName:c.pageName,
            title:c.title
          })
        }

      })

      this.menus=arr;
    }
  }
});

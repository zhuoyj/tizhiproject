<template>
  <div
      class="py-5 md:py-0 -mx-3 px-3 sm:-mx-8 sm:px-8 bg-black/[0.15] dark:bg-transparent"
  >
    <DarkModeSwitcher/>
    <MainColorSwitcher/>
    <MobileMenu/>
    <div class="flex mt-[4.7rem] md:mt-0 overflow-hidden">
      <!-- BEGIN: Side Menu -->

      <nav class="side-nav">
        <router-link
            :to="{ name: 'side-menu-page-1' }"
            class="intro-x flex items-center pl-5 pt-4 mt-3"
            tag="a"
        >
          <img
              alt="Tinker Tailwind HTML Admin Template"
              class="w-6"
              src="@/assets/images/logo.svg"
          />
          <span class="hidden xl:block text-white text-lg ml-3" style="font-size: 14px;"> 大学生体质健康管理系统 </span>
          <!--          <p v-for="(item,index) in temp" :key="index">{{item.pageName}}</p>-->

        </router-link>
        <div class="side-nav__devider my-6"></div>
        <ul>
          <template v-for="(item, index) in formattedMenu">
            <li
                v-if="item == 'devider'"
                :key="item + index"
                class="side-nav__devider my-6"
            ></li>

            <li v-else :key="item + index">
              <SideMenuTooltip
                  :class="{
                  'side-menu--active': item.active,
                   'side-menu--open': item.activeDropdown,
                }"
                  :content="item.title"
                  :href="router.resolve({ name: item.pageName }).path"
                  class="side-menu"
                  tag="a"
                  @click="linkTo(item, router, $event)"
              >
                <div class="side-menu__icon">
                  <component :is="item.icon"/>
                </div>
                <div class="side-menu__title">
                  {{ item.title }}

                </div>

              </SideMenuTooltip>

            </li>
          </template>
        </ul>

      </nav>
      <!-- END: Side Menu -->
      <!-- BEGIN: Content -->
      <div class="content">
        <TopBar/>
        <router-view/>
      </div>
      <!-- END: Content -->
    </div>
  </div>
</template>

<script setup>
import {computed, inject, onMounted, provide, reactive, ref, toRef, watch, h} from "vue";
import {useRoute, useRouter} from "vue-router";
import {helper as $h} from "@/utils/helper";
import {useSideMenuStore} from "@/stores/side-menu";
import TopBar from "@/components/top-bar/Main.vue";
import MobileMenu from "@/components/mobile-menu/Main.vue";
import DarkModeSwitcher from "@/components/dark-mode-switcher/Main.vue";
import MainColorSwitcher from "@/components/main-color-switcher/Main.vue";
import SideMenuTooltip from "@/components/side-menu-tooltip/Main.vue";
import {linkTo, nestedMenu, enter, leave} from "./index";
import dom from "@left4code/tw-starter/dist/js/dom";
import axios from '@/utils/request';
import {defineStore, storeToRefs} from "pinia";
import {ElNotification} from 'element-plus'
import qs from "qs";

const route = useRoute();
const router = useRouter();
const formattedMenu = ref([]);
const user = JSON.parse(sessionStorage.getItem('admin'));
// const sideMenuStore = useSideMenuStore();
// const {menu} = storeToRefs(sideMenuStore);
// onMounted(async () => {
//   await sideMenuStore.three();
// })
let temp = ref([]);
onMounted(async () => {
  dom("body").removeClass("error-page").removeClass("login").addClass("main");
  let arr = [];
  let res = await axios.get('/menu/getMenus/' + user.rid);
  res.data.forEach(c => {
    if (c != null) {
      arr.push({
        icon: c.icon,
        pageName: c.pageName,
        title: c.title
      })
    }

  })
  arr.forEach((item, key) => {
    let menuItem = arr[key];
    menuItem.active = '';
    if (menuItem.pageName === route.name) {
      menuItem.active = route.name;
    }

  })

  temp.value = arr;

  formattedMenu.value = $h.toRaw(temp.value);

  initWebSocket();

  initMsgs();
})
watch(route, (n, o) => {

  temp.value.forEach((item, key) => {
    let menuItem = temp.value[key];
    menuItem.active = '';
    if (menuItem.pageName === n.name) {
      menuItem.active = n.name;
    }

  })
}, {immediate: true})
//const sideMenu = computed(() => nestedMenu(sideMenuStore.menu, route));
provide("forceActiveMenu", (pageName) => {
  route.forceActiveMenu = pageName;
  formattedMenu.value = $h.toRaw(temp.value);
});

watch(
    computed(() => route.path),
    () => {
      delete route.forceActiveMenu;

      formattedMenu.value = $h.toRaw(temp.value);
    }
);
const websock = ref({});

const initWebSocket = function () {
  var url = 'ws://127.0.0.1:10086/websocket/' + user.id;

  websock.value = new WebSocket(url);
  websock.value.onopen = websocketonopen;
  websock.value.onerror = websocketonerror;
  websock.value.onmessage = websocketonmessage;
  websock.value.onclose = websocketclose;

}
const websocketonopen = () => {
  console.log("WebSocket连接成功");
}
const websocketonerror = (e) => {

  console.log("WebSocket连接发生错误");
}

const websocketonmessage = (e) => {
  // initUser();
  var resJson = JSON.parse(e.data);

  ElNotification({
    title: '提示',
    message: h('i', {style: 'color: teal'}, resJson.prints),

  })
  var obj = {
    aid: resJson.uname,
    uid: user.id
  }
  axios.post('/msg/changeStatus', qs.stringify(obj));
}
const websocketclose = (e) => {
  console.log(e);
}
const sendMsg = () => {
  // var obj = {
  //   touid: selectUser.value.id,
  //   msg: contents.value
  // }
  // websock.value.send(JSON.stringify(obj));
  // contents.value = '';

}
const alertMsg=(msg)=>{
  ElNotification({
    title: '提示',
    message: h('i', {style: 'color: teal'},msg )
  })
}

const initMsgs = async () => {
  let res = await axios.get('/msg/list?uid=' + user.id);
  for (let i = 0; i < res.data.length; i++) {
    let result=res.data[i];
    let msg=result.uname+"发送了"+result.aname;
    setTimeout(function (m){
      alertMsg(msg);
    },1000,msg)
    var obj = {
      aid: res.data.aid,
      uid: res.data.uid
    }
    axios.post('/msg/changeStatus', qs.stringify(obj));
  }
}
</script>

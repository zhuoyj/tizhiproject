<template>
  <div>
    <DarkModeSwitcher/>
    <div class="container sm:px-10">
      <div class="block xl:grid grid-cols-2 gap-4">
        <!-- BEGIN: Login Info -->
        <div class="hidden xl:flex flex-col min-h-screen">
          <a class="-intro-x flex items-center pt-5" href="">
            <img
                alt="Midone Tailwind HTML Admin Template"
                class="w-6"
                src="@/assets/images/logo.svg"
            />
            <span class="text-white text-lg ml-3"> 大学生体质健康管理系统 </span>
          </a>
          <div class="my-auto">
            <img
                alt="Midone Tailwind HTML Admin Template"
                class="-intro-x w-1/2 -mt-16"
                src="@/assets/images/illustration.svg"
            />
            <div
                class="-intro-x text-white font-medium text-4xl leading-tight mt-10"
            >
              大学生体质健康管理系统
            </div>
            <div
                class="-intro-x mt-5 text-lg text-white text-opacity-70 dark:text-slate-400"
            >
              human horth system manager
            </div>
          </div>
        </div>
        <!-- END: Login Info -->
        <!-- BEGIN: Login Form -->
        <div class="h-screen xl:h-auto flex py-5 xl:py-0 my-10 xl:my-0">
          <div
              class="my-auto mx-auto xl:ml-20 bg-white dark:bg-darkmode-600 xl:bg-transparent px-5 sm:px-8 py-8 xl:p-0 rounded-md shadow-md xl:shadow-none w-full sm:w-3/4 lg:w-2/4 xl:w-auto"
          >
            <h2
                class="intro-x font-bold text-2xl xl:text-3xl text-center xl:text-left"
            >
              登 录
            </h2>
            <div class="intro-x mt-2 text-slate-400 xl:hidden text-center">
              欢迎登录【大学生体质健康管理系统】
            </div>
            <div class="intro-x mt-8">
              <input
                  v-model="account"
                  class="intro-x login__input form-control py-3 px-4 block"
                  placeholder="请输入您的账号"
                  type="text"
              />
              <input
                  v-model="password"
                  class="intro-x login__input form-control py-3 px-4 block mt-4"
                  placeholder="请输入您的密码"
                  type="password"
              />
            </div>
            <div
                class="intro-x flex text-slate-600 dark:text-slate-500 text-xs sm:text-sm mt-4"
            >
              <div class="flex items-center mr-auto">
                <input
                    id="remember-me"
                    class="form-check-input border mr-2"
                    type="checkbox"
                />
                <label class="cursor-pointer select-none" for="remember-me"
                >记住我</label
                >
              </div>
              <a href="">忘记密码?</a>
            </div>
            <div class="intro-x mt-5 xl:mt-8 text-center xl:text-left">
              <button
                  class="btn btn-primary py-3 px-4 w-full xl:w-32 xl:mr-3 align-top"
                  @click="login"
              >
                登录
              </button>
              <button
                  class="btn btn-outline-secondary py-3 px-4 w-full xl:w-32 mt-3 xl:mt-0 align-top"
                  @click="$router.push('/regView')"
              >
                注册
              </button>
            </div>
            <div
                class="intro-x mt-10 xl:mt-24 text-slate-600 dark:text-slate-500 text-center xl:text-left"
            >
              版权信息：copy@right www.163.com

            </div>
          </div>
        </div>
        <!-- END: Login Form -->
      </div>
    </div>
    <Modal :show="buttonModalPreview" backdrop="static" @hidden="buttonModalPreview = false">
      <a class="absolute right-0 top-0 mt-3 mr-3" href="javascript:;" @click="buttonModalPreview = false">
        <XIcon class="w-8 h-8 text-slate-400"/>
      </a>
      <ModalBody class="p-0">
        <div class="p-5 text-center">
          <CheckCircleIcon class="w-16 h-16 text-success mx-auto mt-3"/>
          <div class="text-3xl mt-5">登录成功</div>
          <div class="text-slate-500 mt-2">
            点击下面按钮关闭
          </div>
        </div>
        <div class="px-5 pb-8 text-center">
          <button class="btn btn-primary w-24" type="button" @click="buttonModalPreview = false">
            确定
          </button>
        </div>
      </ModalBody>
    </Modal>
    <Modal :show="warningModalPreview" @hidden="warningModalPreview = false">
      <ModalBody class="p-0">
        <div class="p-5 text-center">
          <XCircleIcon class="w-16 h-16 text-warning mx-auto mt-3"/>
          <div class="text-3xl mt-5">错误...</div>
          <div class="text-slate-500 mt-2">账号或者密码错误!</div>
        </div>
        <div class="px-5 pb-8 text-center">
          <button class="btn w-24 btn-primary" type="button" @click="warningModalPreview = false">
            Ok
          </button>
        </div>

      </ModalBody>
    </Modal>
  </div>
</template>
<script setup>
import {onMounted, ref, watch} from "vue";
import DarkModeSwitcher from "@/components/dark-mode-switcher/Main.vue";
import dom from "@left4code/tw-starter/dist/js/dom";
import axios from 'axios'
import qs from 'qs'
import {useRouter} from "vue-router";
import {ElMessage} from "element-plus";

onMounted(() => {
  dom("body").removeClass("main").removeClass("error-page").addClass("login");
});
const $router = useRouter();
const account = ref();
const password = ref();
let buttonModalPreview = ref(false);
let warningModalPreview = ref(false);
const login = () => {
  var obj = {
    username: account.value,
    password: password.value,
  }
  axios.post('/user/weblogin', qs.stringify(obj)).then(resp => {
    if (resp.data.status!=1){
      ElMessage.error('账号未审核！')
      return;
    }
    sessionStorage.setItem('admin', JSON.stringify(resp.data));
    buttonModalPreview.value = true;
    //$router.push({path: '/main'})
  }).catch(() => {
    warningModalPreview.value = true;
  })
}
watch(buttonModalPreview, (newval, oldval) => {
  if (newval == false) {
    $router.push({path: '/indexView'})
  }
})

</script>


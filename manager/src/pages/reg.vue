<template>
  <div>
    <DarkModeSwitcher/>
    <div class="container sm:px-10">
      <div class="block xl:grid grid-cols-2 gap-4">
        <!-- BEGIN: Register Info -->
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
              大学生体质健康管理系统 <br/>
              账号注册.
            </div>
            <div
                class="-intro-x mt-5 text-lg text-white text-opacity-70 dark:text-slate-400"
            >
              register human horth system manager place
            </div>
          </div>
        </div>
        <!-- END: Register Info -->
        <!-- BEGIN: Register Form -->
        <div class="h-screen xl:h-auto flex py-5 xl:py-0 my-10 xl:my-0">
          <div
              class="my-auto mx-auto xl:ml-20 bg-white dark:bg-darkmode-600 xl:bg-transparent px-5 sm:px-8 py-8 xl:p-0 rounded-md shadow-md xl:shadow-none w-full sm:w-3/4 lg:w-2/4 xl:w-auto"
          >
            <h2
                class="intro-x font-bold text-2xl xl:text-3xl text-center xl:text-left"
            >
              注 册
            </h2>
            <form class="validate-form" @submit.prevent="save">

              <div class="intro-x mt-8 text-slate-500 block mt-2 text-xs sm:text-sm">
                <div class="input-form mt-3 intro-x mt-8">
                  <label
                      class="form-label w-full flex flex-col sm:flex-row"
                      for="validation-form-3"
                  >
                    权限
                    <span class="sm:ml-auto mt-1 sm:mt-0 text-xs text-slate-500"
                    >必须选择权限</span
                    >
                  </label>
                  <TomSelect
                             v-model.trim="validate.rid.$model"
                             :class="{ 'border-danger': validate.rid.$error }"
                             class="form-control w-full"
                             :options="{
                      placeholder: '请选择您的权限',
                    }" >
                    <option v-for="(item,index) in roles" :key="index" :value="item.id">{{
                        item.name
                      }}
                    </option>

                  </TomSelect>
                  <template v-if="validate.rid.$error">
                    <div
                        v-for="(error, index) in validate.rid.$errors"
                        :key="index"
                        class="text-danger mt-2"
                    >
                      {{ error.$message }}
                    </div>
                  </template>
                </div>
                <div class="input-form mt-3">
                  <label
                      class="form-label w-full flex flex-col sm:flex-row"
                      for="validation-form-3"
                  >
                    班级
                    <span class="sm:ml-auto mt-1 sm:mt-0 text-xs text-slate-500"
                    >必须选择班级</span
                    >
                  </label>
                  <TomSelect v-model="formData.userclassid" :options="{
                      placeholder: '请选择您的班级',
                    }" class="w-full">
                    <option v-for="(item,index) in userClasses" :key="index" :value="item.id">{{
                        item.name
                      }}
                    </option>

                  </TomSelect>
                </div>
                <div class="input-form mt-3">
                  <label
                      class="form-label w-full flex flex-col sm:flex-row"
                      for="validation-form-3"
                  >
                    账号
                    <span class="sm:ml-auto mt-1 sm:mt-0 text-xs text-slate-500"
                    >必须填账号</span
                    >
                  </label>
                  <input
                      id="validation-form-3"
                      v-model.trim="validate.account.$model"
                      :class="{ 'border-danger': validate.account.$error }"
                      class="intro-x login__input form-control py-3 px-4 block mt-4"
                      name="account"
                      placeholder="账号"
                      type="text"
                  />
                  <template v-if="validate.account.$error">
                    <div
                        v-for="(error, index) in validate.account.$errors"
                        :key="index"
                        class="text-danger mt-2"
                    >
                      {{ error.$message }}
                    </div>
                  </template>
                </div>
                <div class="input-form mt-3">
                  <label
                      class="form-label w-full flex flex-col sm:flex-row"
                      for="validation-form-3"
                  >
                    密码
                    <span class="sm:ml-auto mt-1 sm:mt-0 text-xs text-slate-500"
                    >必须填写旧的密码</span
                    >
                  </label>
                  <input
                      id="validation-form-3"
                      v-model.trim="validate.password.$model"
                      :class="{ 'border-danger': validate.password.$error }"
                      class="form-control"
                      name="password"
                      placeholder="确认密码"
                      type="password"
                  />
                  <template v-if="validate.password.$error">
                    <div
                        v-for="(error, index) in validate.password.$errors"
                        :key="index"
                        class="text-danger mt-2"
                    >
                      {{ error.$message }}
                    </div>
                  </template>
                </div>
                <div class="input-form mt-3">
                  <label
                      class="form-label w-full flex flex-col sm:flex-row"
                      for="validation-form-2"
                  >
                    新的密码
                    <span class="sm:ml-auto mt-1 sm:mt-0 text-xs text-slate-500"
                    >必须填写新的密码</span
                    >
                  </label>
                  <input
                      id="validation-form-2"
                      v-model.trim="validate.newpwd.$model"
                      :class="{ 'border-danger': validate.newpwd.$error }"
                      class="form-control"
                      name="newpwd"
                      placeholder="新的密码"
                      type="password"
                  />
                  <template v-if="validate.newpwd.$error">
                    <div
                        v-for="(error, index) in validate.newpwd.$errors"
                        :key="index"
                        class="text-danger mt-2"
                    >
                      {{ error.$message }}
                    </div>
                  </template>
                </div>
                <div class="input-form">
                  <label
                      class="form-label w-full flex flex-col sm:flex-row"
                      for="validation-form-1"
                  >
                    确认密码
                    <span class="sm:ml-auto mt-1 sm:mt-0 text-xs text-slate-500"
                    >必须填写确认的密码</span
                    >
                  </label>
                  <input
                      id="validation-form-1"
                      v-model.trim="validate.twopwd.$model"
                      :class="{ 'border-danger': validate.twopwd.$error }"
                      class="form-control"
                      name="twopwd"
                      placeholder="旧的密码"
                      type="password"
                  />
                  <template v-if="validate.twopwd.$error">
                    <div
                        v-for="(error, index) in validate.twopwd.$errors"
                        :key="index"
                        class="text-danger mt-2"
                    >
                      {{ error.$message }}
                    </div>
                  </template>
                </div>



              </div>
              <div
                  class="intro-x flex items-center text-slate-600 dark:text-slate-500 mt-4 text-xs sm:text-sm"
              >
                <input
                    id="remember-me"
                    class="form-check-input border mr-2"
                    type="checkbox"
                />
                <label class="cursor-pointer select-none" for="remember-me"
                >同意协议</label
                >
                <a class="text-primary dark:text-slate-200 ml-1" href=""
                >查看协议</a
                >.
              </div>
              <div class="intro-x mt-5 xl:mt-8 text-center xl:text-left">
                <button
                    type="submit"
                    class="btn btn-primary py-3 px-4 w-full xl:w-32 xl:mr-3 align-top"
                >
                  注册
                </button>
                <button
                    @click="$router.push({path:'/'})"
                    class="btn btn-outline-secondary py-3 px-4 w-full xl:w-32 mt-3 xl:mt-0 align-top"
                >
                  登录
                </button>
              </div>
            </form>
          </div>
        </div>
        <!-- END: Register Form -->
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted, reactive, ref, toRefs} from "vue";
import DarkModeSwitcher from "@/components/dark-mode-switcher/Main.vue";
import dom from "@left4code/tw-starter/dist/js/dom";
import axios from "@/utils/request";
import {helpers, required} from "@vuelidate/validators";
import {useVuelidate} from "@vuelidate/core";
import qs from "qs";
import {ElMessage} from "element-plus";

const formData = reactive({
  rid: '',
  account: "",
  newpwd: "",
  twopwd: "",
  password: "",

});
const roles = ref([]);
const userClasses = ref([]);
onMounted(async () => {

  roles.value = [{
    id:3,name:'老师'
  }];
  dom("body").removeClass("main").removeClass("error-page").addClass("login");
  let resclass = await axios.get('/userClass/list');
  userClasses.value = resclass.data;
});
const fn = ref(false);
const validateAccount =  (value) => {


  axios.get('/user/getAccount?account=' + value).then(resp => {

    fn.value = false;
    return fn.value;
    callback();
  }).catch(() => {

    fn.value = true;
    return fn.value;
    callback();
  })

  return fn.value;
  callback();
}

const validatePass2 = (value) => {
  if (value !== formData.newpwd) {

    return false;
  } else {

    return true;
  }
  callback();
};

const rules = {
  account: {
    required: helpers.withMessage("账号是必填项！", required),
    validateAccount: helpers.withMessage("账号已经存在！", validateAccount)
  },
  newpwd: {
    required: helpers.withMessage("新的密码是必填项！", required)
  },
  twopwd: {
    required: helpers.withMessage("确认是必填项！", required),
    validatePass2: helpers.withMessage("两次密码不一致！", validatePass2),
  },
  password: {
    required: helpers.withMessage("密码是必填项！", required)
  },
  rid:{
    required: helpers.withMessage("权限是必填项！", required)
  }
};

const validate = useVuelidate(rules, toRefs(formData));

const save = () => {

  validate.value.$touch();
  if (!validate.value.$invalid) {
    axios({
      method:  'post',
      url: '/user',
      data: qs.stringify(formData)
    }).then(() => {
      ElMessage.success('注册成功')
    })
  }else {
    return false;
  }

  // if (validate.value.$invalid) {
  //
  //
  // } else {
  //
  // }
};
</script>

<template>
  <div class="col-span-12 lg:col-span-8 2xl:col-span-9">
    <!-- BEGIN: Display Information -->
    <div class="intro-y box lg:mt-5">
      <div
          class="flex items-center p-5 border-b border-slate-200/60 dark:border-darkmode-400"
      >
        <h2 class="font-medium text-base mr-auto">{{ isEdit ? '编辑' : '增加' }}用户</h2>
      </div>
      <div class="p-5">
        <form class="validate-form" @submit.prevent="save">
          <div class="flex flex-col-reverse xl:flex-row flex-col">
            <div class="flex-1 mt-6 xl:mt-0">
              <div v-if="!isEdit">
<!--                <div class="input-form mt-3">-->
<!--                  <label-->
<!--                      class="form-label w-full flex flex-col sm:flex-row"-->
<!--                      for="validation-form-3"-->
<!--                  >-->
<!--                    权限-->
<!--                    <span class="sm:ml-auto mt-1 sm:mt-0 text-xs text-slate-500"-->
<!--                    >必须选择权限</span-->
<!--                    >-->
<!--                  </label>-->
<!--                  <TomSelect  v-model="formData.rid" :options="{-->
<!--                      placeholder: '请选择您的权限',-->
<!--                    }" class="w-full">-->
<!--                    <option v-for="(item,index) in rolrs" :key="index" :value="item.id">{{-->
<!--                        item.name-->
<!--                      }}-->
<!--                    </option>-->

<!--                  </TomSelect>-->
<!--                </div>-->
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
                      class="form-control"
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
              <div>
                <div class="input-form mt-3" v-if="formData.rid==3||formData.rid==4">
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
                <div class="input-form">
                  <div class="col-span-12 2xl:col-span-6">
                    <div>
                      <label class="form-label w-full flex flex-col sm:flex-row" for="update-profile-form-1"
                      >姓名</label
                      >
                      <input
                          id="update-profile-form-1"
                          v-model="formData.name"
                          class="form-control"
                          placeholder="Input text"
                          type="text"
                      />
                    </div>

                  </div>
                </div>
                <div class="input-form">
                  <div class="col-span-12 2xl:col-span-6">
                    <div>
                      <label class="form-label w-full flex flex-col sm:flex-row" for="update-profile-form-1"
                      >联系电话</label
                      >
                      <input
                          id="update-profile-form-1"
                          v-model="formData.phone"
                          class="form-control"
                          placeholder="Input text"
                          type="text"
                      />
                    </div>

                  </div>
                </div>
                <div class="input-form">
                  <div class="col-span-12">
                    <div class="mt-3">
                      <label class="form-label w-full flex flex-col sm:flex-row" for="update-profile-form-5"
                      >地址</label
                      >
                      <textarea
                          id="update-profile-form-5"
                          v-model="formData.address"
                          class="form-control"
                          placeholder="Adress"
                      ></textarea
                      >
                    </div>
                  </div>
                </div>
              </div>

            </div>

            <div class="w-52 mx-auto xl:mr-0 xl:ml-6">
              <div
                  class="border-2 border-dashed shadow-sm border-slate-200/60 dark:border-darkmode-400 rounded-md p-5"
              >
                <div
                    class="h-40 relative image-fit cursor-pointer zoom-in mx-auto"
                >
                  <img
                      ref="pic"
                      :src="formData.headpic"
                      alt="请选择您的头像"
                      class="rounded-md"
                  />
                  <Tippy
                      class="w-5 h-5 flex items-center justify-center absolute rounded-full text-white bg-danger right-0 top-0 -mr-2 -mt-2"
                      content="Remove this profile photo?"
                      tag="div"
                  >
                    <xIcon class="w-4 h-4"/>
                  </Tippy>
                </div>
                <div class="mx-auto cursor-pointer relative mt-5">
                  <button class="btn btn-primary w-full" type="button">
                    选择头像
                  </button>
                  <input
                      id="imgfile1"
                      class="w-full h-full top-0 left-0 absolute opacity-0"
                      type="file"
                      @change="changeImg"
                  />
                </div>
              </div>
            </div>
          </div>
          <button class="btn btn-primary mt-5" type="submit">
            提交
          </button>
        </form>
      </div>
    </div>
    <!-- END: Display Information -->
  </div>
</template>
<script setup>
import { onMounted, reactive, ref, toRefs, watch} from "vue";
import {email, integer, maxLength, minLength, required, url,} from "@vuelidate/validators";
import {useVuelidate} from "@vuelidate/core";
import {helpers} from "@vuelidate/validators";
import axios from "@/utils/request";
import qs from "qs";

const props = defineProps(["oldData", "isEdit"]);
const emit = defineEmits(["close"]);

const formData = reactive({
  account: "",
  newpwd: "",
  twopwd: "",
  password: "",
  rid:''
});
const isEdit = ref(false);
watch(props.oldData, (n, o) => {
  formData.headpic = n.value.headpic;
  formData.name = n.value.name;
  formData.phone = n.value.phone;
  formData.address = n.value.address;
  formData.account = n.value.account;
  formData.password = n.value.password;
  formData.rid = n.value.rid;
  formData.id = n.value.id;
  formData.userclassid = n.value.userclassid;
  formData.idcardno=n.value.idcardno;
  formData.userid=n.value.userid;
  formData.age=n.value.age;
  formData.sex=n.value.sex;
  formData.status=n.value.status;
  //console.log(formData.value)
},)
watch(props.isEdit, (n, o) => {
  isEdit.value = n.value;
})
const pic = ref();
const changeImg = () => {

  const fd = new FormData();
  if (document.getElementById("imgfile1").files[0] != null) {
    fd.append('file', document.getElementById("imgfile1").files[0]);
  }
  axios.post('/upload/image', fd, {headers: {'Content-type': 'multipart/form-data'}}).then(resp => {
    formData.headpic = resp.data;
    pic.value.src = resp.data;


  })
}
const fn = ref(false);
const validateAccount = (value) => {
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
  }
};

const validate = useVuelidate(rules, toRefs(formData));

const save = () => {

  validate.value.$touch();
  axios({
    method: isEdit.value ? 'put' : 'post',
    url: '/user',
    data: qs.stringify(formData)
  }).then(() => {
    emit('close');
  })

};
const rolrs = ref([]);
onMounted(async () => {
  let res = await axios.get('/role/list');
  rolrs.value=res.data;
  loadClasses();
})

const userClasses = ref([]);
const loadClasses= async () => {
  let resclass = await axios.get('/userClass/list');
  userClasses.value = resclass.data;
}

const loadPage=(rid)=>{
  setTimeout(function (){
    formData.rid=rid;
    console.log(formData)
  },500)
}
defineExpose({
  loadPage
})

</script>
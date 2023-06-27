<template>
  <div class="intro-y flex items-center mt-8">
    <h2 class="text-lg font-medium mr-auto">修改密码</h2>
  </div>
  <div class="grid grid-cols-12 gap-6 mt-5">
    <div class="intro-y col-span-12 lg:col-span-12">
      <!-- BEGIN: Form Validation -->
      <form class="validate-form" @submit.prevent="save">
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


        <button class="btn btn-primary mt-5" type="submit">
          提交
        </button>
      </form>
      <!-- END: Form Validation -->
    </div>
  </div>
  <Modal :show="buttonModalPreview" @hidden="buttonModalPreview = false">
    <a class="absolute right-0 top-0 mt-3 mr-3" href="javascript:;" @click="buttonModalPreview = false">
      <XIcon class="w-8 h-8 text-slate-400"/>
    </a>
    <ModalBody class="p-0">
      <div class="p-5 text-center">
        <CheckCircleIcon class="w-16 h-16 text-success mx-auto mt-3"/>
        <div class="text-3xl mt-5">操作成功</div>
        <div class="text-slate-500 mt-2">
          密码修改成功
        </div>
      </div>
      <div class="px-5 pb-8 text-center">
        <button class="btn btn-primary w-24" type="button" @click="buttonModalPreview = false">
          确定
        </button>
      </div>
    </ModalBody>
  </Modal>
</template>

<script setup>
import {onMounted, reactive, ref, toRefs, watch} from "vue";
import {email, integer, maxLength, minLength, required, url,} from "@vuelidate/validators";
import {useVuelidate} from "@vuelidate/core";
import {helpers} from "@vuelidate/validators";
import axios from "axios";

const emit = defineEmits(["close"])

const buttonModalPreview = ref(false);
const user = ref(JSON.parse(sessionStorage.getItem('admin')));

const formData = reactive({
  newpwd: "",
  twopwd: "",
  password: "",

});
const validatePass = (value) => {

  if (value != user.value.password) {
    return false;
  } else {
    return true;
  }
}

const validatePass2 = (value) => {
  if (value !== formData.newpwd) {
    return false;
  } else {
    return true;
  }
};

const rules = {
  newpwd: {
    required: helpers.withMessage("新的密码是必填项！", required)
  },
  twopwd: {
    required: helpers.withMessage("确认是必填项！", required),
    validatePass2: helpers.withMessage("两次密码不一致！", validatePass2),
  },
  password: {
    required: helpers.withMessage("密码是必填项！", required),
    validatePass: helpers.withMessage("请输入正确的原始密码！", validatePass),
  }
};

const validate = useVuelidate(rules, toRefs(formData));

const save = () => {

  validate.value.$touch();

  if (validate.value.$invalid) {


  } else {
    axios.post('/user/changwPwd?uid=' + user.value.id + '&pwd=' + formData.newpwd).then(resp => {
      buttonModalPreview.value = true;

    })
  }
};
watch(buttonModalPreview, (n, o) => {
  if (n == false) {
    emit('close');
  }
})
</script>

<template>
  <div class="col-span-12 lg:col-span-8 2xl:col-span-9">
    <!-- BEGIN: Display Information -->
    <div class="intro-y box lg:mt-5">
      <div
          class="flex items-center p-5 border-b border-slate-200/60 dark:border-darkmode-400"
      >
        <h2 class="font-medium text-base mr-auto">{{ isEdit ? '编辑' : '增加' }}</h2>
      </div>

      <div class="p-5">
        <form class="validate-form" @submit.prevent="save">
          <div class="flex flex-col-reverse xl:flex-row flex-col">
            <div class="flex-1 mt-6 xl:mt-0">
              <div>
                <div class="input-form mt-3">
                  <label
                      class="form-label w-full flex flex-col sm:flex-row"
                      for="validation-form-3"
                  >
                    学生
                    <span class="sm:ml-auto mt-1 sm:mt-0 text-xs text-slate-500"
                    >必须选择学生</span
                    >
                  </label>
                  <TomSelect
                      v-model.trim="validate.uid.$model"
                      :class="{'border-danger': validate.uid.$error }"
                      :options="{
                      placeholder: '请选择学生',
                    }"
                      class="w-full">
                    <option v-for="(item,index) in tousers" :key="index" :value="item.id">{{
                        item.name
                      }}
                    </option>

                  </TomSelect>
                  <template v-if="validate.uid.$error">
                    <div
                        v-for="(error, index) in validate.uid.$errors"
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
                    分数
                    <span class="sm:ml-auto mt-1 sm:mt-0 text-xs text-slate-500"
                    >必须填分数</span
                    >
                  </label>
                  <input
                      id="validation-form-3"
                      v-model.trim="validate.score.$model"
                      :class="{ 'border-danger': validate.score.$error }"
                      class="form-control"
                      name="score"
                      placeholder="分数"
                      type="text"
                  />
                  <template v-if="validate.score.$error">
                    <div
                        v-for="(error, index) in validate.score.$errors"
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
                    存放地址
                    <span class="sm:ml-auto mt-1 sm:mt-0 text-xs text-slate-500"
                    >必须填存放地址</span
                    >
                  </label>
                  <Dropzone
                      ref="dpz"
                      :options="{
                  url: 'http://localhost:10086/upload/uploadimage',
                  thumbnailWidth: 150,
                  maxFiles: 1,
                  headers: { 'My-Awesome-Header': 'header value' },
                  init:function (){
                     this.on('success',function (f,d){
                       test(f,d);
                     })
                     this.on('complete',function (f){
                       this.removeFile(f);
                     })
                  }

                }"
                      class="dropzone">

                    <div class="text-lg font-medium">
                      Drop files here or click to upload.
                    </div>
                    <div class="text-gray-600">
                      This is just a demo dropzone. Selected files are
                      <span class="font-medium">not</span> actually uploaded.
                    </div>
                  </Dropzone>

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
import {defineProps, onMounted, provide, reactive, ref, toRefs, watch} from "vue";
import {email, integer, maxLength, minLength, required, url,} from "@vuelidate/validators";
import {useVuelidate} from "@vuelidate/core";
import {helpers} from "@vuelidate/validators";
import axios from "@/utils/request";
import qs from "qs";
import {ElMessage} from "element-plus";

const dpz = ref();

const emit = defineEmits(["close"]);
const rules = {
  score: {required: helpers.withMessage("分数是必填项！", required)},
  uid: {required: helpers.withMessage("学生是必填项！", required)},
};
const form = ref({});
const isEdit = ref(false);
const validate = useVuelidate(rules, form);
const test = (f, d) => {
  console.log(document.getElementById('ddpfile'));
  form.value.videos = '';
  form.value.videos = d.fileName;
  ElMessage.success('上传成功')
}
const save = () => {

  validate.value.$touch();
  if (!validate.value.$invalid) {
    axios({
      method: form.value.isEdit ? 'put' : 'post',
      url: '/actionScore',
      data: qs.stringify(form.value)
    }).then(() => {
      emit('close');
    })
  }


};

const loadAid = (id) => {

  form.value.aid = id;

}

const loadactionScorePage = (obj) => {
  setTimeout(function (){
    form.value = obj;
    isEdit.value = form.value.isEdit;
    loadtoUser();
  },500)

}
defineExpose({
  loadactionScorePage,
  loadAid,

})
const tousers = ref([]);
const user = ref(JSON.parse(sessionStorage.getItem('admin')));

const loadtoUser= async ()=>{
  console.log(user)
  var url='/user/list?rid=4';
  if (user.value.rid==3){
    url+='&cid='+user.value.userclassid;
  }
  let res = await axios.get(url);
  tousers.value = res.data;
}

</script>
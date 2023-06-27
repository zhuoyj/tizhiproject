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
<!--                <div class="input-form mt-3">-->
<!--                  <label-->
<!--                      class="form-label w-full flex flex-col sm:flex-row"-->
<!--                      for="validation-form-3"-->
<!--                  >-->
<!--                    班级-->
<!--                    <span class="sm:ml-auto mt-1 sm:mt-0 text-xs text-slate-500"-->
<!--                    >必须选择班级</span-->
<!--                    >-->
<!--                  </label>-->
<!--                  <TomSelect-->
<!--                      v-model.trim="validate.cid.$model"-->
<!--                      :class="{'border-danger': validate.cid.$error }"-->
<!--                      :options="{-->
<!--                      placeholder: '请选择班级',-->
<!--                    }"-->
<!--                      class="w-full">-->
<!--                    <option v-for="(item,index) in tousers" :key="index" :value="item.id">{{-->
<!--                        item.name-->
<!--                      }}-->
<!--                    </option>-->

<!--                  </TomSelect>-->
<!--                  <template v-if="validate.cid.$error">-->
<!--                    <div-->
<!--                        v-for="(error, index) in validate.cid.$errors"-->
<!--                        :key="index"-->
<!--                        class="text-danger mt-2"-->
<!--                    >-->
<!--                      {{ error.$message }}-->
<!--                    </div>-->
<!--                  </template>-->
<!--                </div>-->
                <div class="input-form mt-3">
                  <label
                      class="form-label w-full flex flex-col sm:flex-row"
                      for="validation-form-3"
                  >
                    测试日期
                    <span class="sm:ml-auto mt-1 sm:mt-0 text-xs text-slate-500"
                    >必须填测试日期</span
                    >
                  </label>
                  <div class="relative">
                    <div
                        class="absolute rounded-l w-10 h-full flex items-center justify-center bg-slate-100 border text-slate-500 dark:bg-darkmode-700 dark:border-darkmode-800 dark:text-slate-400" style="height: 40px; font-size: 14px;">
                      <CalendarIcon />
                    </div>
                    <Litepicker
                        v-model.trim="validate.times.$model"
                        :class="{ 'border-danger': validate.times.$error }"
                        :options="{
                             format:'YYYY-MM-DD',
                    autoApply: false,
                    showWeekNumbers: true,
                    dropdowns: {
                      minYear: 1990,
                      maxYear: null,
                      months: true,
                      years: true,
                    },
                  }" class="form-control pl-12"/>
                    <template v-if="validate.times.$error">
                      <div
                          v-for="(error, index) in validate.times.$errors"
                          :key="index"
                          class="text-danger mt-2"
                      >
                        {{ error.$message }}
                      </div>
                    </template>
                  </div>

                </div>
                <div class="input-form mt-3">
                  <label
                      class="form-label w-full flex flex-col sm:flex-row"
                      for="validation-form-3"
                  >
                    测试名称
                    <span class="sm:ml-auto mt-1 sm:mt-0 text-xs text-slate-500"
                    >必须填测试名称</span
                    >
                  </label>
                  <input
                      id="validation-form-3"
                      v-model.trim="validate.name.$model"
                      :class="{ 'border-danger': validate.name.$error }"
                      class="form-control"
                      name="name"
                      placeholder="测试名称"
                      type="text"
                  />
                  <template v-if="validate.name.$error">
                    <div
                        v-for="(error, index) in validate.name.$errors"
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
                    测试地点
                    <span class="sm:ml-auto mt-1 sm:mt-0 text-xs text-slate-500"
                    >必须填测试地点</span
                    >
                  </label>
                  <input
                      id="validation-form-3"
                      v-model.trim="validate.address.$model"
                      :class="{ 'border-danger': validate.address.$error }"
                      class="form-control"
                      name="address"
                      placeholder="测试地点"
                      type="text"
                  />
                  <template v-if="validate.address.$error">
                    <div
                        v-for="(error, index) in validate.address.$errors"
                        :key="index"
                        class="text-danger mt-2"
                    >
                      {{ error.$message }}
                    </div>
                  </template>
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
import {defineExpose, defineProps, onMounted, reactive, ref, toRefs, watch} from "vue";
import {email, integer, maxLength, minLength, required, url,} from "@vuelidate/validators";
import {useVuelidate} from "@vuelidate/core";
import {helpers} from "@vuelidate/validators";
import axios from "@/utils/request";
import qs from "qs";

const user = ref(JSON.parse(sessionStorage.getItem('admin')));
const emit = defineEmits(["close"]);
const rules = {
  name: {required: helpers.withMessage("测试名称是必填项！", required),},
  address: {required: helpers.withMessage("测试地点是必填项！", required),},
  times: {required: helpers.withMessage("测试时间是必填项！", required),}
};


const form = ref({});
const isEdit = ref(false);
const validate = useVuelidate(rules, form);

const save = () => {
  validate.value.$touch();
  if (!validate.value.$invalid) {

    axios({
      method: form.value.isEdit ? 'put' : 'post',
      url: '/actions',
      data: qs.stringify(form.value)
    }).then(() => {
      emit('close');
    })
  }


};
const tousers = ref([]);
const loadClass = () => {
  var url = '/userClass/list?1=1';
  if (user.value.rid >= 3) {
    url += '&cid=' + user.value.userclassid;
  }

  let res = axios.get(url).then(res => {
    tousers.value = res.data;
  })

}
const loadactionsPage = (obj) => {
  form.value = obj;
  isEdit.value = form.value.isEdit;
}
defineExpose({
  loadactionsPage,
  loadClass
})



// onMounted( () => {
//   loadClass();
// })

</script>
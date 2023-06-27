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
                    班级名称
                    <span class="sm:ml-auto mt-1 sm:mt-0 text-xs text-slate-500"
                    >班级名称必须填</span
                    >
                  </label>
                  <input
                      id="validation-form-3"
                      v-model.trim="validate.name.$model"
                      :class="{ 'border-danger': validate.name.$error }"
                      class="form-control"
                      name="name"
                      placeholder=""
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
import {defineProps, onMounted, reactive, ref, toRef, toRefs, watch} from "vue";
import {email, integer, maxLength, minLength, required, url,} from "@vuelidate/validators";
import {useVuelidate} from "@vuelidate/core";
import {helpers} from "@vuelidate/validators";
import axios from "@/utils/request";
import qs from "qs";

const form = ref({});
const emit = defineEmits(["close"]);
const isEdit = ref(false);
const rules = {
  name: {required: helpers.withMessage("班级名称是必填项！", required),},
};

const validate = useVuelidate(rules, form);

const save = () => {

  validate.value.$touch();
  if (!validate.value.$invalid) {
    axios({
      method: form.value.isEdit ? 'put' : 'post',
      url: '/userClass',
      data: qs.stringify(form.value)
    }).then(() => {
      emit('close');
    })
  }


};


const loaduserClassPage = (obj) => {
  form.value = obj;
  isEdit.value = form.value.isEdit;
}
defineExpose({
  loaduserClassPage
})


</script>
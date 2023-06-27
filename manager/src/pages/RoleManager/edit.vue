<template>
  <div class="col-span-12 lg:col-span-8 2xl:col-span-9">
    <!-- BEGIN: Display Information -->
    <div class="intro-y box lg:mt-5">
      <div
          class="flex items-center p-5 border-b border-slate-200/60 dark:border-darkmode-400"
      >
        <h2 class="font-medium text-base mr-auto">权限分配</h2>
      </div>

      <div class="p-5">
        <div class="intro-y col-span-12 overflow-auto 2xl:overflow-visible">
          <table class="table table-report -mt-2">
            <thead>
            <tr>
              <th class="whitespace-nowrap">

              </th>
              <th class="whitespace-nowrap text-center">菜单</th>
            </tr>

            </thead>
            <tbody>
            <tr
                v-for="(m, fakerKey) in menus.value"
                :key="fakerKey"
                class="intro-x"
            >
              <td>
                <input class="form-check-input" type="checkbox" v-model="m.isck" :checked="m.isck" @change="ck(m)" />
              </td>
              <td class="text-center">


               {{m.name}}
              </td>
            </tr>
            </tbody>
          </table>
      </div>
    </div>
    <!-- END: Display Information -->
  </div>
  </div>
</template>
<script setup>
import {onMounted, reactive, ref, toRefs, watch} from "vue";
import {email, integer, maxLength, minLength, required, url,} from "@vuelidate/validators";
import {useVuelidate} from "@vuelidate/core";
import {helpers} from "@vuelidate/validators";
import axios from "axios";
import qs from "qs";

const props = defineProps(["oldData", "isEdit"]);
const emit = defineEmits(["close"]);
const formData = reactive({
  id: '',
  name: '',
});
const menus = reactive([]);
watch(props.oldData, (n, o) => {
  formData.id = n.value.id;
  formData.name = n.value.name;
  loadMenus(formData.id);
})
const ck=(v)=>{
  console.log(v.isck);
  var obj={
    menuid:v.id,
    roleid:formData.id,
    isck:v.isck==true?1:0
  }
  axios.post('/rolemenu',qs.stringify(obj)).then(()=>{
    loadMenus(formData.id);
  })

}
const loadMenus = (rid) => {
  menus.value=[];
  axios({
    method: 'get',
    url: '/menu/queryByRid?rid='+rid,
    data: {rid: rid}
  }).then(resp => {
    // emit('close');
    console.log(resp.data);
    menus.value = resp.data;

  })
}


</script>
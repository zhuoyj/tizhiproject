<template>
  <div class="col-span-12 lg:col-span-8 2xl:col-span-9">
    <!-- BEGIN: Display Information -->
    <div class="intro-y box lg:mt-5">
      <div
          class="flex items-center p-5 border-b border-slate-200/60 dark:border-darkmode-400"
      >
        <h2 class="font-medium text-base mr-auto">修改个人资料</h2>
      </div>
      <div class="p-5">
        <div class="flex flex-col-reverse xl:flex-row flex-col">
          <div class="flex-1 mt-6 xl:mt-0">
            <div class="grid grid-cols-12 gap-x-5">
              <div class="col-span-12 2xl:col-span-6">
                <div>
                  <label class="form-label" for="update-profile-form-1"
                  >姓名</label
                  >
                  <input
                      id="update-profile-form-1"
                      v-model="user.name"
                      class="form-control"
                      placeholder="Input text"
                      type="text"
                  />
                </div>

              </div>
              <div class="col-span-12 2xl:col-span-6">

                <div>
                  <label class="form-label" for="update-profile-form-1"
                  >联系电话</label
                  >
                  <input
                      id="update-profile-form-1"
                      v-model="user.phone"
                      class="form-control"
                      placeholder="输入联系电话"
                      type="text"
                  />
                </div>
              </div>
              <div class="col-span-12">
                <div class="mt-3">
                  <label class="form-label" for="update-profile-form-5"
                  >地址</label
                  >
                  <textarea
                      id="update-profile-form-5"
                      v-model="user.address"
                      class="form-control"
                      placeholder="Adress"
                  ></textarea
                  >
                </div>
              </div>
            </div>
            <button class="btn btn-primary w-20 mt-3" type="button" @click="save">
              保存
            </button>
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
                    :src="user.headpic"
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
                    id="imgfile"
                    class="w-full h-full top-0 left-0 absolute opacity-0"
                    type="file"
                    @change="changeImg"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- END: Display Information -->
  </div>
</template>
<script setup>
import {ref} from "vue";
import axios from "axios";
import qs from "qs";

const user = ref(JSON.parse(sessionStorage.getItem('admin')));

const emit = defineEmits(["close"])
const save = () => {

  axios.put('/user', qs.stringify(user.value)).then(() => {
    emit('close');
  })

}
const pic = ref();
const changeImg = () => {
  const formData = new FormData();
  if (document.getElementById("imgfile").files[0] != null) {
    formData.append('file', document.getElementById("imgfile").files[0]);
  }
  axios.post('/upload/image', formData, {headers: {'Content-type': 'multipart/form-data'}}).then(resp => {

    pic.value.src = resp.data;
    user.value.headpic = resp.data;

  })
}

</script>
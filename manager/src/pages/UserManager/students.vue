<template>
  <h2 class="intro-y text-lg font-medium mt-10">学生管理</h2>
  <div class="grid grid-cols-12 gap-6 mt-5">
    <div
        class="intro-y col-span-12 flex flex-wrap sm:flex-nowrap items-center mt-2"
    >
      <button class="btn btn-primary shadow-md mr-2" @click="add">增加</button>
      <div
          class="px-4 pb-4 mt-5 flex items-center justify-center cursor-pointer relative"
      >
        <ImageIcon class="w-4 h-4 mr-2"/>
        <span class="text-primary mr-1">批量导入</span> 点击这里
        <input
            id="horizontal-form-1"
            class="w-full h-full top-0 left-0 absolute opacity-0"
            type="file"
            @change="adds"
        />
      </div>

      <div class="hidden md:block mx-auto text-slate-500">
        第 {{ page }} 页，共 {{ totalPage }} 页 ，共 {{ totals }} 条数据
      </div>
      <div class="w-full sm:w-auto mt-3 sm:mt-0 sm:ml-auto md:ml-0">
        <div class="w-56 relative text-slate-500">
          <input
              v-model="key"
              class="form-control w-56 box pr-10"
              placeholder="Search..."
              type="text"
          />
          <SearchIcon class="w-4 h-4 absolute my-auto inset-y-0 mr-3 right-0" @click="doSearch"/>
        </div>
      </div>
    </div>
    <!-- BEGIN: Users Layout -->
    <div
        v-for="(faker, fakerKey) in users.value"
        :key="fakerKey"
        class="intro-y col-span-12 md:col-span-6"
    >

      <div class="box">
        <div class="flex flex-col lg:flex-row items-center p-5">
          <div class="w-24 h-24 lg:w-12 lg:h-12 image-fit lg:mr-1">
            <img
                :src="faker.headpic"
                alt="Midone Tailwind HTML Admin Template"
                class="rounded-full"
            />
          </div>
          <div class="lg:ml-2 lg:mr-auto text-center lg:text-left mt-3 lg:mt-0">
            <a class="font-medium" href="">{{ faker.name }}</a>
            <div class="text-slate-500 text-xs mt-0.5">
              <span v-if="faker.rid==1">超级管理员</span>
              <span v-if="faker.rid==2">学校负责人</span>
              <span v-if="faker.rid==3">老师</span>
              <span v-if="faker.rid==4">学生</span>
            </div>
            <div class="text-slate-500 text-xs mt-0.5">
              {{ faker.age }}
            </div>
          </div>
          <div class="flex mt-4 lg:mt-0">
            <button class="btn btn-primary py-1 px-2 mr-2" @click="edit(faker)">编辑</button>
            <button class="btn btn-outline-secondary py-1 px-2" @click="del(faker)">删除</button>
          </div>
        </div>
      </div>
    </div>
    <!-- BEGIN: Users Layout -->
    <!-- END: Pagination -->
    <div
        class="intro-y col-span-12 flex flex-wrap sm:flex-row sm:flex-nowrap items-center"
    >
      <nav class="w-full sm:w-auto sm:mr-auto">
        <ul class="pagination">

          <li class="page-item">
            <a class="page-link" href="#" @click="page>1?page=page-1:page=1">
              <ChevronLeftIcon class="w-4 h-4"/>
            </a>
          </li>

          <li v-for="(itemPage,i) in Math.min(5,totalPage) " :key="i"
              :class="{active:page===pageIndex(itemPage)}" class="page-item">
            <a class="page-link" href="#" @click="page=pageIndex(itemPage)">{{ pageIndex(itemPage) }}</a>
          </li>

          <li class="page-item">
            <a class="page-link" href="#" @click="page<totalPage?page=page+1:page=totalPage">
              <ChevronRightIcon class="w-4 h-4"/>
            </a>
          </li>

        </ul>
      </nav>

    </div>
    <!-- END: Pagination -->
  </div>
  <div>
    <Modal :show="editModal" backdrop="static" size="modal-xl" @hidden="editModal = false">
      <ModalBody class="p-30 text-center">
        <div>

          <XIcon style="float: right;" @click="editModalClose"></XIcon>
        </div>
        <div>
          <user-edit ref="editRef" :isEdit="isEdit" :oldData="user" @close="editModalClose"/>
        </div>

      </ModalBody>
    </Modal>
  </div>
  <!--删除-->
  <Modal v-if="deleteModalPreview" :show="deleteModalPreview" backdrop="static" @hidden="deleteModalPreview = false">
    <ModalBody class="p-0">
      <div>
        <XIcon style="float: right;" @click="deleteModalPreview=false"></XIcon>
      </div>
      <div class="p-5 text-center">
        <XCircleIcon class="w-16 h-16 text-danger mx-auto mt-3"/>
        <div class="text-3xl mt-5">您确定要删除吗?</div>
        <div class="text-slate-500 mt-2">
          您确定要删除吗? <br/>确定后您将删除这条数据.
        </div>
      </div>
      <div class="px-5 pb-8 text-center">
        <button class="btn btn-outline-secondary w-24 mr-1" type="button" @click="deleteModalPreview = false">
          取消
        </button>
        <button class="btn btn-danger w-24" type="button" @click="doDel=true">
          确定
        </button>
      </div>
    </ModalBody>
  </Modal>
</template>
<script setup>
import {onMounted, reactive, ref, toRefs, watch} from "vue";
import axios from "axios";
import UserEdit from "@/pages/userEdit.vue";
import {ElAlert, ElMessage, ElMessageBox} from "element-plus";
const adds=async ()=>{
  const formData = new FormData();
  if (document.getElementById("horizontal-form-1").files[0] != null) {
    formData.append('file', document.getElementById("horizontal-form-1").files[0]);
  }
   await axios.post('/excelUtils/import', formData, {headers: {'Content-type': 'multipart/form-data'}});
  ElMessage('操作成功')
  loadUser(1, 10, key.value);

}
const isEdit = reactive({});
const users = reactive([]);
const user = reactive({});
const totalPage = ref(0);
const page = ref(1);
const totals = ref(0);
const key = ref();
const delItem = ref();
let rid=4;
const loadUser = (page, row, keys) => {
  users.value = [];
  var url = '/user/page?page=' + page + '&rows=' + row+'&rid='+rid;
  if (keys != null) {
    url += '&key=' + keys;
  }
  axios.get(url).then(resp => {
    users.value = resp.data.items;
    totalPage.value = resp.data.totalPage;
    totals.value = resp.data.total;

  })

}
loadUser(1, 10, key.value);
const editModal = ref(false);
const editModalClose = () => {

  user.value = {};
  editModal.value = false;

  loadUser(page.value, 10, null);
}
const editRef = ref({});
const add = () => {
  editRef.value.loadPage(rid);
  user.value = {};
  user.rid = rid;
  isEdit.value = false;
  editModal.value = true;

}
const edit = (item) => {

  user.value = item;
  isEdit.value = true;

  editModal.value = true;
}
const pageIndex = (paging) => {
  if (page.value <= 3 || totalPage.value <= 5) {
    return paging;
  } else if (page.value >= totalPage.value - 2) {
    return totalPage.value - 5 + paging;
  } else {
    return page.value - 3 + paging;
  }
}
const doSearch = () => {
  loadUser(1, 10, key.value);
}
watch(page, (n, o) => {
  loadUser(n, 10, key.value);
})
let deleteModalPreview = ref(false);
const del = (item) => {
  delItem.value = item;
  deleteModalPreview.value = true;
}
const doDel = ref(false);
watch(doDel, (n, o) => {
  if (n == true) {
    if (delItem != null) {
      axios.delete('/user/id/' + delItem.value.id).then(() => {
        deleteModalPreview.value = false;
        loadUser(1, 10, key.value);
        page.value = 1;
        doDel.value = false;
        delItem.value = {};
      })
    }

  }
})
</script>

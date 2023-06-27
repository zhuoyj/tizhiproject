<template>
  <div class="grid grid-cols-12 gap-6">
    <div class="col-span-12 2xl:col-span-9">
      <h2 class="intro-y text-lg font-medium mt-10">成绩管理</h2>
      <div class="w-full sm:w-auto flex mt-4 sm:mt-0">
        <div v-if="user.rid<=3" class="w-full sm:w-auto flex mt-4 sm:mt-0">
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
          <span style="line-height: 65px;">及格率(60分以上)：{{ jigelv }}</span>
          <span style="line-height: 65px;">优秀率（80分以上）：{{ youxiulv }}</span>
        </div>

      </div>
      <!-- BEGIN: HTML Table Data -->
      <div class="intro-y box p-5 mt-5">
        <div class="flex flex-col sm:flex-row sm:items-end xl:items-start">
          <form id="tabulator-html-filter-form" class="xl:flex sm:mr-auto">
            <div class="sm:flex items-center sm:mr-4">

            </div>
            <div class="sm:flex items-center sm:mr-4 mt-2 xl:mt-0">

            </div>
            <div class="sm:flex items-center sm:mr-4 mt-2 xl:mt-0">

            </div>
            <div class="mt-2 xl:mt-0">

            </div>
          </form>
          <div class="flex mt-5 sm:mt-0">
            <button
                id="tabulator-print"
                class="btn btn-outline-secondary w-1/2 sm:w-auto mr-2"
                @click="onPrint"
            >
              <PrinterIcon class="w-4 h-4 mr-2"/>
              Print
            </button>
            <Dropdown class="w-1/2 sm:w-auto">
              <DropdownToggle class="btn btn-outline-secondary w-full sm:w-auto">
                <FileTextIcon class="w-4 h-4 mr-2"/>
                Export
                <ChevronDownIcon class="w-4 h-4 ml-auto sm:ml-2"/>
              </DropdownToggle>
              <DropdownMenu class="w-40">
                <DropdownContent>
                  <DropdownItem @click="onExportCsv">
                    <FileTextIcon class="w-4 h-4 mr-2"/>
                    Export CSV
                  </DropdownItem>
                  <DropdownItem @click="onExportJson">
                    <FileTextIcon class="w-4 h-4 mr-2"/>
                    Export JSON
                  </DropdownItem>
                  <DropdownItem @click="onExportXlsx">
                    <FileTextIcon class="w-4 h-4 mr-2"/>
                    Export XLSX
                  </DropdownItem>
                  <DropdownItem @click="onExportHtml">
                    <FileTextIcon class="w-4 h-4 mr-2"/>
                    Export HTML
                  </DropdownItem>
                </DropdownContent>
              </DropdownMenu>
            </Dropdown>
          </div>
        </div>
        <div class="overflow-x-auto scrollbar-hidden">
          <div
              id="tabulator"
              ref="tableRef"
              class="mt-5 table-report table-report--tabulator"
          ></div>
        </div>
      </div>
      <!-- END: HTML Table Data -->
      <!-- BEGIN: Users Layout -->
      <!-- END: Pagination -->

      <!-- END: Pagination -->
    </div>
    <div class="col-span-12 2xl:col-span-3">
      <div
          class="col-span-12 md:col-span-6 xl:col-span-4 2xl:col-span-12 mt-3 2xl:mt-8"
      >
        <div class="intro-x flex items-center h-10">
          <h2 class="text-lg font-medium truncate mr-5">排行榜</h2>
        </div>
        <div class="mt-5">
          <div
              v-for="(faker, fakerKey) in phs"
              :key="fakerKey"
              class="intro-x"
          >
            <div class="box px-5 py-3 mb-3 flex items-center zoom-in">
              <h3 style="width: 30px;text-align: left;color: tomato;">{{fakerKey+1}}</h3>
              <div
                  class="w-10 h-10 flex-none image-fit rounded-full overflow-hidden"
              >

                <img
                    :alt="faker.uname"
                    :src="faker.headpic"
                />
              </div>
              <div class="ml-4 mr-auto">
                <div class="font-medium">
                  {{ faker.uname }}
                </div>
                <div class="text-slate-500 text-xs mt-0.5">
                  {{ faker.score }}
                </div>
              </div>

            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
  <div>
    <Modal :show="editModal" backdrop="static" size="modal-xl" @hidden="editModal = false">
      <ModalBody class="p-30 text-center">
        <div>

          <XIcon style="float: right;" @click="editModalClose"></XIcon>
        </div>
        <div>
          <edit-form ref="actionScoreRef" @close="editModalClose"></edit-form>

        </div>

      </ModalBody>
    </Modal>

    <Modal :show="lookModal" backdrop="static" size="modal-xl" @hidden="lookModal = false">
      <ModalBody class="p-5 text-center">
        <div>

          <XIcon style="float: right;" @click="lookModal=false"></XIcon>
        </div>
        <div class="p-5">
          <video :src="selelctObj.videos" autoplay="autoplay" controls height="300" style="margin: 0 auto"
                 width="300"></video>
        </div>

      </ModalBody>
    </Modal>
  </div>

</template>
<script setup>
import {onMounted, reactive, ref, toRefs, watch} from "vue";
import axios from "axios";
import editForm from './edit.vue'
import xlsx from "xlsx";
import {createIcons, icons} from "lucide";
import Tabulator from "tabulator-tables";
import dom from "@left4code/tw-starter/dist/js/dom";
import {ElMessage, ElMessageBox} from "element-plus";

const lookModal = ref(false);
const page = ref(1);
const key = ref();
const user = ref(JSON.parse(sessionStorage.getItem('admin')));
const editModal = ref(false);
let aid = 0;
let selelctObj = ref({});
const adds = () => {

  const formData = new FormData();
  if (document.getElementById("horizontal-form-1").files[0] != null) {
    formData.append('file', document.getElementById("horizontal-form-1").files[0]);
  }
  axios.post('/excelUtils/importScore', formData, {headers: {'Content-type': 'multipart/form-data'}}).then(() => {
    ElMessage('操作成功')
    initPage();
  })


}
const lookVieo = (obj) => {
  selelctObj.value = obj;
  lookModal.value = true;
}

const loadAid = (id) => {
  aid = id;
  initPage();
  loadLv();
}
const editModalClose = () => {

  editModal.value = false;
  initPage();

}
const actionScoreRef = ref(null);
const add = () => {
  var obj = {
    aid: aid,
    isEdit: false
  }
  actionScoreRef.value.loadactionScorePage(obj);
  editModal.value = true;
}
const edit = (obj) => {
  axios.get('/actionScore/findOne/id/' + obj.id).then(res => {
    res.data.isEdit = true;
    actionScoreRef.value.loadactionScorePage(res.data);
    editModal.value = true;
  })
}

const initPage = () => {
  var url = "http://localhost:10086/actionScore/newpage?1=1&aid=" + aid;
  if (user.value.rid == 3) {
    url += "&cid=" + user.value.userclassid;
  }
  if (user.value.rid == 4) {
    url += "&uid=" + user.value.id;
  }
  tabulator.value.setData(url);
  loadPH(aid);
}

let deleteModalPreview = ref(false);
const del = (item) => {
  axios.delete('/actionScore/id/' + item.id).then(() => {
    initPage();

  })
}
const tableRef = ref();
const tabulator = ref();
const filter = reactive({
  field: "name",
  type: "like",
  value: "",
});
const jigelv = ref("");
const youxiulv = ref("");
const loadLv = async () => {
  let res = await axios.get('/actionScore/getLv?aid=' + aid + '&score=60');
  jigelv.value = res.data;
  let res1 = await axios.get('/actionScore/getLv?aid=' + aid + '&score=80');
  youxiulv.value = res1.data;
}

const imageAssets = import.meta.globEager(
    `/src/assets/images/*.{jpg,jpeg,png,svg}`
);
const initTabulator = () => {
  tabulator.value = new Tabulator(tableRef.value, {

    printAsHtml: true,
    printStyled: true,
    pagination: "remote",

    paginationSize: 10,
    paginationSizeSelector: [10, 20, 30, 40],
    layout: "fitColumns",
    responsiveLayout: "collapse",
    placeholder: "暂时没有数据",
    columns: [
      {
        formatter: "responsiveCollapse",
        width: 40,
        minWidth: 30,
        hozAlign: "center",
        resizable: false,
        headerSort: false,
      },
      // For HTML table
      {
        title: "项目",
        minWidth: 200,
        responsive: 0,
        field: "aname",
        hozAlign: "center",
        vertAlign: "middle",
        print: false,
        download: false

      },
      {
        title: "学生",
        minWidth: 200,
        responsive: 0,
        field: "uname",
        hozAlign: "center",
        vertAlign: "middle",
        print: false,
        download: false
      },
      {
        title: "分数",
        minWidth: 200,
        responsive: 0,
        field: "score",
        hozAlign: "center",
        vertAlign: "middle",
        print: false,
        download: false,
        formatter(cell) {
          return `<div><div class="font-medium whitespace-nowrap">${cell.getData().score}</div></div>`;
        },
      },
      {
        title: "操作",
        minWidth: 200,
        field: "actions",
        responsive: 1,
        hozAlign: "center",
        vertAlign: "middle",
        print: false,
        download: false,
        formatter(cell) {
          if (user.value.rid <= 3) {
            var str = '<div class="flex lg:justify-center items-center">' +
                '<a  class="flex items-center mr-3" href="javascript:;"> <i data-lucide="check-square" class="w-4 h-4 mr-1"></i> 编辑 </a>' +
                '<a class="flex items-center text-danger" href="javascript:;"> <i data-lucide="trash-2" className="w-4 h-4 mr-1"></i> 删除</a>' +
                '<a  class="flex items-center mr-3" href="javascript:;"><i data-lucide="eye" class="w-4 h-4 mr-1"></i> 查看 </a>' +
                '</div>';
            const a = dom(str);
            dom(a[0].childNodes[0]).on("click", function () {

              edit(cell.getData())
            });
            dom(a[0].childNodes[1]).on("click", function () {
              del(cell.getData())
            });
            dom(a[0].childNodes[2]).on("click", function () {
              lookVieo(cell.getData())
            });
            return a[0];
          } else {
            var str = '<div class="flex lg:justify-center items-center">' +
                '<a  class="flex items-center mr-3" href="javascript:;"><i data-lucide="eye" class="w-4 h-4 mr-1"></i> 查看 </a>' +
                '</div>';
            const a = dom(str);
            dom(a[0].childNodes[0]).on("click", function () {
              lookVieo(cell.getData())
            });
            return a[0];
          }

        },
      },

      // For print format
      {
        title: "",
        field: "id",
        visible: false,
        print: true,
        download: true,
      },
      {
        title: "",
        field: "aid",
        visible: false,
        print: true,
        download: true,
      },
      {
        title: "",
        field: "uid",
        visible: false,
        print: true,
        download: true,
      },
      {
        title: "分数",
        field: "score",
        visible: false,
        print: true,
        download: true,
      },
      {
        title: "视频文件地址",
        field: "videos",
        visible: false,
        print: true,
        download: true,
      },
    ],
    renderComplete() {
      createIcons({
        icons,
        "stroke-width": 1.5,
        nameAttr: "data-lucide",
      });

    },
  });
};

// Redraw table onresize
const reInitOnResizeWindow = () => {
  window.addEventListener("resize", () => {
    tabulator.value.redraw();
    createIcons({
      icons,
      "stroke-width": 1.5,
      nameAttr: "data-lucide",
    });

  });
};

// Filter function
const onFilter = () => {
  tabulator.value.setFilter(filter.field, filter.type, filter.value);
};

// On reset filter
const onResetFilter = () => {
  filter.field = "name";
  filter.type = "like";
  filter.value = "";
  onFilter();
};

// Export
const onExportCsv = () => {
  tabulator.value.download("csv", "data.csv");
};

const onExportJson = () => {
  tabulator.value.download("json", "data.json");
};

const onExportXlsx = () => {
  const win = window;
  win.XLSX = xlsx;
  tabulator.value.download("xlsx", "data.xlsx", {
    sheetName: "Products",
  });
};

const onExportHtml = () => {
  tabulator.value.download("html", "data.html", {
    style: true,
  });
};

// Print
const onPrint = () => {
  tabulator.value.print();
};

onMounted(() => {

  initTabulator();
  reInitOnResizeWindow();
  initPage();
});

defineExpose({
  loadAid
})

const phs=ref([]);
const loadPH=async (aid)=>{
  let res=await axios.get('/actionScore/limtDatas?aid='+aid);
  phs.value=res.data;
  console.log(res.data)
}

</script>

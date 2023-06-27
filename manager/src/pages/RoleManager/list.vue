<template>
  <h2 class="intro-y text-lg font-medium mt-10"> 管理</h2>
  <div class="w-full sm:w-auto flex mt-4 sm:mt-0">
<!--    <button class="btn btn-primary shadow-md mr-2" @click="add">增加</button>-->
  </div>
  <!-- BEGIN: HTML Table Data -->
  <div class="intro-y box p-5 mt-5">
    <div class="flex flex-col sm:flex-row sm:items-end xl:items-start">
      <form id="tabulator-html-filter-form" class="xl:flex sm:mr-auto">
        <div class="sm:flex items-center sm:mr-4">
          <label class="w-12 flex-none xl:w-auto xl:flex-initial mr-2"
          >type</label
          >
          <select
              id="tabulator-html-filter-field"
              v-model="filter.field"
              class="form-select w-full sm:w-32 2xl:w-full mt-2 sm:mt-0 sm:w-auto"
          >

            <option value="name">名称</option>
          </select>
        </div>
        <div class="sm:flex items-center sm:mr-4 mt-2 xl:mt-0">
          <label class="w-12 flex-none xl:w-auto xl:flex-initial mr-2"
          >Type</label
          >
          <select
              id="tabulator-html-filter-type"
              v-model="filter.type"
              class="form-select w-full mt-2 sm:mt-0 sm:w-auto"
          >
            <option selected value="like">like</option>
            <option value="=">=</option>
            <option value="<">&lt;</option>
            <option value="<=">&lt;=</option>
            <option value=">">></option>
            <option value=">=">>=</option>
            <option value="!=">!=</option>
          </select>
        </div>
        <div class="sm:flex items-center sm:mr-4 mt-2 xl:mt-0">
          <label class="w-12 flex-none xl:w-auto xl:flex-initial mr-2"
          >Value</label
          >
          <input
              id="tabulator-html-filter-value"
              v-model="filter.value"
              class="form-control sm:w-40 2xl:w-full mt-2 sm:mt-0"
              placeholder="Search..."
              type="text"
          />
        </div>
        <div class="mt-2 xl:mt-0">
          <button
              id="tabulator-html-filter-go"
              class="btn btn-primary w-full sm:w-16"
              type="button"
              @click="onFilter"
          >
            查询
          </button>
          <button
              id="tabulator-html-filter-reset"
              class="btn btn-secondary w-full sm:w-16 mt-2 sm:mt-0 sm:ml-1"
              type="button"
              @click="onResetFilter"
          >
            Reset
          </button>
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

  <div>
    <Modal :show="editModal" backdrop="static" size="modal-xl" @hidden="editModal = false">
      <ModalBody class="p-30 text-center">
        <div>

          <XIcon style="float: right;" @click="editModalClose"></XIcon>
        </div>
        <div>
          <edit-form :isEdit="isEdit" :oldData="item" @close="editModalClose"></edit-form>

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
import editForm from './edit.vue'
import xlsx from "xlsx";
import {createIcons, icons} from "lucide";
import Tabulator from "tabulator-tables";
import dom from "@left4code/tw-starter/dist/js/dom";

const isEdit = reactive({});
const item = reactive({});
const page = ref(1);
const key = ref();

const editModal = ref(false);
const editModalClose = () => {

  editModal.value = false;
   // tabulator.value.setData("http://localhost:10086/role/newpage");

}
const add = () => {
  item.value = {};
  isEdit.value = false;
  editModal.value = true;
}
const edit = (obj) => {

  item.value = obj;
  isEdit.value = true;

  editModal.value = true;
}
watch(page, (n, o) => {

})
let deleteModalPreview = ref(false);
const del = (item) => {

  axios.delete('/role/id/' + item.id).then(() => {
    tabulator.value.setData("http://localhost:10086/role/newpage");

  })
}
const tableRef = ref();
const tabulator = ref();
const filter = reactive({
  field: "name",
  type: "like",
  value: "",
});

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
        title: "",
        minWidth: 200,
        responsive: 0,
        field: "id",
         hozAlign: "center",
        vertAlign: "middle",
        print: false,
        download: false,
        formatter(cell) {
          return `<div><div class="font-medium whitespace-nowrap">${cell.getData().id}</div></div>`;
        },
    },
    {
        title: "",
        minWidth: 200,
        responsive: 0,
        field: "name",
         hozAlign: "center",
        vertAlign: "middle",
        print: false,
        download: false,
        formatter(cell) {
          return `<div><div class="font-medium whitespace-nowrap">${cell.getData().name}</div></div>`;
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
          const a = dom('<div class="flex lg:justify-center items-center"> <a id="edit' + cell.getData().id + '"  class="flex items-center mr-3" href="javascript:;"> <i data-lucide="check-square" class="w-4 h-4 mr-1"></i> 权限分配 </a> </div>');
          dom(a[0].childNodes[1]).on("click", function () {

            edit(cell.getData())
          });

          return a[0];
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
        field: "name",
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
  tabulator.value.setData("http://localhost:10086/role/newpage");
});

</script>

<template>
  <div class="intro-y flex items-center mt-8">
    <h2 class="text-lg font-medium mr-auto">
      <span >主页</span>
    </h2>
  </div>
  <TabGroup>
    <!-- BEGIN: Profile Info -->
    <div class="intro-y box px-5 pt-5 mt-5">
      <div
          class="flex flex-col lg:flex-row border-b border-slate-200/60 dark:border-darkmode-400 pb-5 -mx-5"
      >
        <div
            class="flex flex-1 px-5 items-center justify-center lg:justify-start"
        >
          <div
              class="w-20 h-20 sm:w-24 sm:h-24 flex-none lg:w-32 lg:h-32 image-fit relative"
          >
            <img
                ref="pic"
                :alt="user.name"
                :src="user.headpic"
                class="rounded-full"
            />
            <div
                class="absolute mb-1 mr-1 flex items-center justify-center bottom-0 right-0 bg-primary rounded-full p-2"
            >
              <CameraIcon class="w-4 h-4 text-white"/>
              <input
                  id="imgfile1"
                  class="w-full h-full top-0 left-0 absolute opacity-0"
                  type="file"
                  @change="changeImg"
              />
            </div>
          </div>
          <div class="ml-5">
            <div
                class="w-24 sm:w-40 truncate sm:whitespace-normal font-medium text-lg"
            >
              姓名：{{ user.name }}
            </div>
            <div class="text-slate-500">
              身份：
              <span v-if="user.rid==1">超级管理员</span>
              <span v-if="user.rid==2">学校管理员</span>
              <span v-if="user.rid==3">老师</span>
              <span v-if="user.rid==4">学生</span>
            </div>
          </div>
        </div>
        <div
            class="mt-6 lg:mt-0 flex-1 px-5 border-l border-r border-slate-200/60 dark:border-darkmode-400 border-t lg:border-t-0 pt-5 lg:pt-0"
        >
          <div class="font-medium text-center lg:text-left lg:mt-3">
            详细信息
          </div>
          <div
              class="flex flex-col justify-center items-center lg:items-start mt-4"
          >
            <div class="truncate sm:whitespace-normal flex items-center">
              <PhoneIcon class="w-4 h-4 mr-2"/>
              {{ user.phone }}
            </div>
            <div class="truncate sm:whitespace-normal flex items-center mt-3">
              <BatteryIcon class="w-4 h-4 mr-2"/>
              {{ user.age }}
            </div>
            <div class="truncate sm:whitespace-normal flex items-center mt-3">
              <TwitterIcon class="w-4 h-4 mr-2"/>
              {{ user.address }}
            </div>
          </div>
        </div>
<!--        <div-->
<!--            class="mt-6 lg:mt-0 flex-1 px-5 border-t lg:border-0 border-slate-200/60 dark:border-darkmode-400 pt-5 lg:pt-0"-->
<!--        >-->
<!--          <div class="font-medium text-center lg:text-left lg:mt-5" v-if="admin.rid>1">-->
<!--            <span v-if="admin.rid==3">慢性病</span>-->
<!--            <span v-if="admin.rid==2">擅长领域</span>-->

<!--          </div>-->
<!--          <div class="flex items-center justify-center lg:justify-start mt-2">-->
<!--            <div class="mr-2 w-full " v-if="admin.rid>1">-->
<!--              <p v-for="(item,index) in cats" :key="item.id">  {{ item.name }}</p>-->

<!--            </div>-->

<!--          </div>-->

<!--        </div>-->
      </div>
<!--      <TabList-->
<!--          class="nav-link-tabs flex-col sm:flex-row justify-center lg:justify-start text-center"-->
<!--      >-->
<!--        <Tab :fullWidth="false" class="py-4 cursor-pointer" v-if="admin.rid>1">-->
<!--          <span v-if="admin.rid==3">慢性病历</span>-->
<!--          <span v-if="admin.rid==2">简介</span>-->

<!--        </Tab>-->

<!--      </TabList>-->
    </div>
    <!-- END: Profile Info -->
<!--    <TabPanels class="intro-y mt-5"  v-if="admin.rid>1">-->
<!--      <TabPanel>-->
<!--        <div class="grid grid-cols-12 gap-12">-->
<!--          &lt;!&ndash; BEGIN: Top Categories &ndash;&gt;-->
<!--          <div class="intro-y box col-span-12 lg:col-span-12">-->
<!--            <div-->
<!--                class="flex items-center p-5 border-b border-slate-200/60 dark:border-darkmode-400"-->
<!--            >-->
<!--              <h2 class="font-medium text-base mr-auto">-->
<!--                <span v-if="admin.rid==3">我的病历</span>-->
<!--                <span v-if="admin.rid==2">我的简介</span>-->

<!--              </h2>-->

<!--            </div>-->
<!--            <div class="p-5">-->
<!--              <div class="mt-2" v-html="user.horthlog">-->

<!--              </div>-->
<!--            </div>-->
<!--          </div>-->
<!--          &lt;!&ndash; END: Top Categories &ndash;&gt;-->
<!--          &lt;!&ndash; BEGIN: Work In Progress &ndash;&gt;-->
<!--          &lt;!&ndash; END: Work In Progress &ndash;&gt;-->
<!--          &lt;!&ndash; BEGIN: Daily Sales &ndash;&gt;-->

<!--          &lt;!&ndash; END: Daily Sales &ndash;&gt;-->
<!--          &lt;!&ndash; BEGIN: Latest Tasks &ndash;&gt;-->

<!--          &lt;!&ndash; END: Latest Tasks &ndash;&gt;-->
<!--          &lt;!&ndash; BEGIN: General Statistic &ndash;&gt;-->

<!--          &lt;!&ndash; END: General Statistic &ndash;&gt;-->
<!--        </div>-->
<!--      </TabPanel>-->
<!--    </TabPanels>-->
<!--    <div class="box w-full intro-y mt-5" v-else>-->
<!--      <div-->
<!--          class=" flex-1 px-5"-->
<!--      >-->
<!--        <div id="myEChartsBar" style="height: 500px;width: 100%;"></div>-->
<!--      </div>-->
<!--    </div>-->
  </TabGroup>

</template>
<script setup>
let echarts = inject("echarts");
import {inject, onMounted, reactive, ref, toRefs, watch} from "vue";
import axios from "@/utils/request";
import qs from "qs";
import {ElMessage} from "element-plus";

const admin = JSON.parse(sessionStorage.getItem('admin'));
const editorConfig = {
  toolbar: {
    items: ["bold", "italic", "link"],
  },
};
const editorData = ref("");
const user = ref({});
const pic = ref();
const changeImg = () => {

  const fd = new FormData();
  if (document.getElementById("imgfile1").files[0] != null) {
    fd.append('file', document.getElementById("imgfile1").files[0]);
  }
  axios.post('/upload/image', fd, {headers: {'Content-type': 'multipart/form-data'}}).then(resp => {
    user.headpic = resp.data;
    pic.value.src = resp.data;


  })
}
const loadUser = async () => {

  let res = await axios.get('/user/findOne/id/' + admin.id);
  if (res.data.categories != null) {
    if (res.data.categories.trim().length > 0) {

      res.data.categories = res.data.categories.split(',');
      res.data.categories.map(c => {
        return parseInt(c);
      })

    }
  }
  user.value = res.data;

}
const cats = ref([]);

onMounted(() => {
  loadUser();

  loadChart();
})
const loadChart=async ()=>{
  // var pieurl = '/category/tj';
  //
  // let pieRes = await axios.get(pieurl);
  // change(pieRes.data);
}
const change = (datas) => {
  const chartBox = echarts.init(document.getElementById("myEChartsBar")); // 主要

  const option = {
    title: {
      text: '年龄段统计',
      subtext: '年龄段统计',
      left: 'center'
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      orient: 'vertical',
      left: 'left'
    },
    series: [
      {
        name: 'Access From',
        type: 'pie',
        radius: '80%',
        data: datas,

      }
    ]
  };
  chartBox.setOption(option);
  // 根据页面大小自动响应图表大小
  window.addEventListener("resize", function () {
    chartBox.resize();
  });
};

</script>
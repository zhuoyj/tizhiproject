<template>
  <!-- BEGIN: Top Bar -->
  <div class="top-bar">
    <!-- BEGIN: Breadcrumb -->
    <nav aria-label="breadcrumb" class="-intro-x mr-auto hidden sm:flex">
<!--      <ol class="breadcrumb">-->
<!--        <li class="breadcrumb-item"><a href="#">Application</a></li>-->
<!--        <li aria-current="page" class="breadcrumb-item active">Dashboard</li>-->
<!--      </ol>-->
    </nav>
    <!-- END: Breadcrumb -->
    <!-- BEGIN: Search -->
    <div class="intro-x relative mr-3 sm:mr-6">
      <!--      <div class="search hidden sm:block">-->
      <!--        <input-->
      <!--          type="text"-->
      <!--          class="search__input form-control border-transparent"-->
      <!--          placeholder="请输入搜索内容"-->
      <!--          @focus="showSearchDropdown"-->
      <!--          @blur="hideSearchDropdown"-->
      <!--        />-->
      <!--        <SearchIcon class="search__icon dark:text-slate-500" />-->
      <!--      </div>-->
      <!--      <a class="notification sm:hidden" href="">-->
      <!--        <SearchIcon class="notification__icon dark:text-slate-500" />-->
      <!--      </a>-->
      <!--      <div class="search-result" :class="{ show: searchDropdown }">-->
      <!--        <div class="search-result__content">-->
      <!--          <div class="search-result__content__title">Pages</div>-->
      <!--          <div class="mb-5">-->
      <!--            <a href="" class="flex items-center">-->
      <!--              <div-->
      <!--                class="w-8 h-8 bg-success/20 dark:bg-success/10 text-success flex items-center justify-center rounded-full"-->
      <!--              >-->
      <!--                <InboxIcon class="w-4 h-4" />-->
      <!--              </div>-->
      <!--              <div class="ml-3">Mail Settings</div>-->
      <!--            </a>-->
      <!--            <a href="" class="flex items-center mt-2">-->
      <!--              <div-->
      <!--                class="w-8 h-8 bg-pending/10 text-pending flex items-center justify-center rounded-full"-->
      <!--              >-->
      <!--                <UsersIcon class="w-4 h-4" />-->
      <!--              </div>-->
      <!--              <div class="ml-3">Users & Permissions</div>-->
      <!--            </a>-->
      <!--            <a href="" class="flex items-center mt-2">-->
      <!--              <div-->
      <!--                class="w-8 h-8 bg-primary/10 dark:bg-primary/20 text-primary/80 flex items-center justify-center rounded-full"-->
      <!--              >-->
      <!--                <CreditCardIcon class="w-4 h-4" />-->
      <!--              </div>-->
      <!--              <div class="ml-3">Transactions Report</div>-->
      <!--            </a>-->
      <!--          </div>-->
      <!--          <div class="search-result__content__title">Users</div>-->
      <!--          <div class="mb-5">-->
      <!--            <a-->
      <!--              v-for="(faker, fakerKey) in $_.take($f(), 4)"-->
      <!--              :key="fakerKey"-->
      <!--              href-->
      <!--              class="flex items-center mt-2"-->
      <!--            >-->
      <!--              <div class="w-8 h-8 image-fit">-->
      <!--                <img-->
      <!--                  alt="Tinker Tailwind HTML Admin Template"-->
      <!--                  class="rounded-full"-->
      <!--                  :src="faker.photos[0]"-->
      <!--                />-->
      <!--              </div>-->
      <!--              <div class="ml-3">{{ faker.users[0].name }}</div>-->
      <!--              <div-->
      <!--                class="ml-auto w-48 truncate text-slate-500 text-xs text-right"-->
      <!--              >-->
      <!--                {{ faker.users[0].email }}-->
      <!--              </div>-->
      <!--            </a>-->
      <!--          </div>-->
      <!--          <div class="search-result__content__title">Products</div>-->
      <!--          <a-->
      <!--            v-for="(faker, fakerKey) in $_.take($f(), 4)"-->
      <!--            :key="fakerKey"-->
      <!--            href-->
      <!--            class="flex items-center mt-2"-->
      <!--          >-->
      <!--            <div class="w-8 h-8 image-fit">-->
      <!--              <img-->
      <!--                alt="Tinker Tailwind HTML Admin Template"-->
      <!--                class="rounded-full"-->
      <!--                :src="faker.images[0]"-->
      <!--              />-->
      <!--            </div>-->
      <!--            <div class="ml-3">{{ faker.products[0].name }}</div>-->
      <!--            <div-->
      <!--              class="ml-auto w-48 truncate text-slate-500 text-xs text-right"-->
      <!--            >-->
      <!--              {{ faker.products[0].category }}-->
      <!--            </div>-->
      <!--          </a>-->
      <!--        </div>-->
      <!--      </div>-->
    </div>
    <!-- END: Search -->
    <!-- BEGIN: Notifications -->
    <!--    <Dropdown class="intro-x mr-auto sm:mr-6">-->
    <!--      <DropdownToggle-->
    <!--        tag="div"-->
    <!--        role="button"-->
    <!--        class="notification notification&#45;&#45;bullet cursor-pointer"-->
    <!--      >-->
    <!--        <BellIcon class="notification__icon dark:text-slate-500" />-->
    <!--      </DropdownToggle>-->
    <!--      <DropdownMenu class="notification-content pt-2">-->
    <!--        <DropdownContent tag="div" class="notification-content__box">-->
    <!--          <div class="notification-content__title">Notifications</div>-->
    <!--          <div-->
    <!--            v-for="(faker, fakerKey) in $_.take($f(), 5)"-->
    <!--            :key="fakerKey"-->
    <!--            class="cursor-pointer relative flex items-center"-->
    <!--            :class="{ 'mt-5': fakerKey }"-->
    <!--          >-->
    <!--            <div class="w-12 h-12 flex-none image-fit mr-1">-->
    <!--              <img-->
    <!--                alt="Tinker Tailwind HTML Admin Template"-->
    <!--                class="rounded-full"-->
    <!--                :src="faker.photos[0]"-->
    <!--              />-->
    <!--              <div-->
    <!--                class="w-3 h-3 bg-success absolute right-0 bottom-0 rounded-full border-2 border-white dark:border-darkmode-600"-->
    <!--              ></div>-->
    <!--            </div>-->
    <!--            <div class="ml-2 overflow-hidden">-->
    <!--              <div class="flex items-center">-->
    <!--                <a href="javascript:;" class="font-medium truncate mr-5">{{-->
    <!--                  faker.users[0].name-->
    <!--                }}</a>-->
    <!--                <div class="text-xs text-slate-400 ml-auto whitespace-nowrap">-->
    <!--                  {{ faker.times[0] }}-->
    <!--                </div>-->
    <!--              </div>-->
    <!--              <div class="w-full truncate text-slate-500 mt-0.5">-->
    <!--                {{ faker.news[0].shortContent }}-->
    <!--              </div>-->
    <!--            </div>-->
    <!--          </div>-->
    <!--        </DropdownContent>-->
    <!--      </DropdownMenu>-->
    <!--    </Dropdown>-->
    <!-- END: Notifications -->
    <!-- BEGIN: Account Menu -->
    <Dropdown class="intro-x w-8 h-8">
      <DropdownToggle
          class="dropdown-toggle w-8 h-8 rounded-full overflow-hidden shadow-lg image-fit zoom-in"
          role="button"
          tag="div"
      >
        <img
            :src="user.headpic"
            :alt="user.name"
        />
      </DropdownToggle>
      <DropdownMenu class="w-56">
        <DropdownContent class="bg-primary text-white">
          <DropdownHeader class="!font-normal" tag="div">
            <div class="font-medium">{{ user.name }}</div>
            <div class="text-xs text-white/70 mt-0.5 dark:text-slate-500">
              <span v-if="user.role_id==1">超级管理员</span>
              <span v-if="user.role_id==2">律师</span>
              <span v-if="user.role_id==3">用户</span>
            </div>
          </DropdownHeader>
          <DropdownDivider class="border-white/[0.08]"/>
          <DropdownItem class="dropdown-item hover:bg-white/5" @click="userInfoModal=true">
            <UserIcon class="w-4 h-4 mr-2"/>
            修改资料
          </DropdownItem>
          <!--          <DropdownItem class="dropdown-item hover:bg-white/5">-->
          <!--            <EditIcon class="w-4 h-4 mr-2" /> Add Account-->
          <!--          </DropdownItem>-->
          <DropdownItem class="dropdown-item hover:bg-white/5" @click="pwdModal=true">
            <LockIcon class="w-4 h-4 mr-2"/>
            修改密码
          </DropdownItem>
          <!--          <DropdownItem class="dropdown-item hover:bg-white/5">-->
          <!--            <HelpCircleIcon class="w-4 h-4 mr-2" /> Help-->
          <!--          </DropdownItem>-->
          <DropdownDivider class="border-white/[0.08]"/>
          <DropdownItem class="dropdown-item hover:bg-white/5" @click="exit">
            <ToggleRightIcon class="w-4 h-4 mr-2"/>
            退出登录
          </DropdownItem>
        </DropdownContent>
      </DropdownMenu>
    </Dropdown>
    <!-- END: Account Menu -->
    <!--退出登录-->
    <Modal backdrop="static" :show="deleteModalPreview" @hidden="deleteModalPreview = false">
      <ModalBody class="p-0">
        <div >
          <XIcon style="float: right;" @click="deleteModalPreview=false"></XIcon>
        </div>
        <div class="p-5 text-center">
          <XCircleIcon class="w-16 h-16 text-danger mx-auto mt-3"/>
          <div class="text-3xl mt-5">您确定要退出吗?</div>
          <div class="text-slate-500 mt-2">
            您确定要退出登录吗? <br/>确定后您将跳转到登录页面.
          </div>
        </div>
        <div class="px-5 pb-8 text-center">
          <button class="btn btn-outline-secondary w-24 mr-1" type="button" @click="deleteModalPreview = false">
            取消
          </button>
          <button class="btn btn-danger w-24" type="button" @click="doExit=true">
            确定
          </button>
        </div>
      </ModalBody>
    </Modal>
    <!--修改资料-->
    <Modal backdrop="static" :show="userInfoModal" @hidden="userInfoModal = false" size="modal-xl">
      <ModalBody class="p-30 text-center">
        <div >
          <XIcon style="float: right;" @click="userInfoModal=false"></XIcon>
        </div>
        <div>
          <user-info @close="userInfoModalClose"/>
        </div>

      </ModalBody>
    </Modal>
    <!--修改密码-->
    <Modal backdrop="static" :show="pwdModal" @hidden="pwdModal = false" size="modal-xl">
      <ModalBody class="p-30 text-center">
        <div >
          <XIcon style="float: right;" @click="pwdModal=false"></XIcon>
        </div>
        <div>
          <pwd-view @close="pwdModalClose"/>

        </div>

      </ModalBody>
    </Modal>
  </div>
  <!-- END: Top Bar -->
</template>

<script setup>
import {reactive, ref, watch} from "vue";
import {useRouter} from "vue-router";
import  UserInfo from '@/pages/UserInfo.vue'
import PwdView from '@/pages/changpwd.vue'
const $router = useRouter();
const searchDropdown = ref(false);
const showSearchDropdown = () => {
  searchDropdown.value = true;
};
const hideSearchDropdown = () => {
  searchDropdown.value = false;
};
let userInfoModal=ref(false);
const userInfoModalClose=()=>{
  userInfoModal.value=false;
}
let deleteModalPreview = ref(false);
const doExit = ref(false);
const pwdModal=ref(false);
const pwdModalClose=()=>{
  pwdModal.value=false;
}
const user = reactive(JSON.parse(sessionStorage.getItem('admin')));
const exit = () => {
  deleteModalPreview.value = true;
}


watch(doExit, (n, o) => {
  if (n == true) {
    sessionStorage.clear();
    deleteModalPreview.value = false;
    $router.push({path:'/'})
  }
})
</script>

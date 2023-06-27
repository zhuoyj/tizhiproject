<template>
  <!-- BEGIN: Mobile Menu -->
  <div
      :class="{
      'mobile-menu--active': activeMobileMenu,
    }"
      class="mobile-menu md:hidden"
  >
    <div class="mobile-menu-bar">
      <a class="flex mr-auto" href="">
        <img
            alt="Tinker Tailwind HTML Admin Template"
            class="w-6"
            src="@/assets/images/logo.svg"
        />
      </a>
      <a class="mobile-menu-toggler" href="javascript:;">
        <BarChart2Icon
            class="w-8 h-8 text-white transform -rotate-90"
            @click="toggleMobileMenu"
        />
      </a>
    </div>
    <div class="scrollable">
      <a class="mobile-menu-toggler" href="javascript:;">
        <XCircleIcon
            class="w-8 h-8 text-white transform -rotate-90"
            @click="toggleMobileMenu"
        />
      </a>
      <ul class="scrollable__content py-2">
        <!-- BEGIN: First Child -->
        <template v-for="(menu, menuKey) in formattedMenu">
          <li
              v-if="menu == 'devider'"
              :key="menu + menuKey"
              class="menu__devider my-6"
          ></li>
          <li v-else :key="menu + menuKey">
            <a
                :class="{
                'menu--active': menu.active,
                'menu--open': menu.activeDropdown,
              }"
                class="menu"
                href="javascript:;"
                @click="linkTo(menu, router)"
            >
              <div class="menu__icon">
                <component :is="menu.icon"/>
              </div>
              <div class="menu__title">
                {{ menu.title }}
                <div
                    v-if="menu.subMenu"
                    :class="{ 'transform rotate-180': menu.activeDropdown }"
                    class="menu__sub-icon"
                >
                  <ChevronDownIcon/>
                </div>
              </div>
            </a>
            <!-- BEGIN: Second Child -->
            <transition @enter="enter" @leave="leave">
              <ul v-if="menu.subMenu && menu.activeDropdown">
                <li
                    v-for="(subMenu, subMenuKey) in menu.subMenu"
                    :key="subMenuKey"
                >
                  <a
                      :class="{ 'menu--active': subMenu.active }"
                      class="menu"
                      href="javascript:;"
                      @click="linkTo(subMenu, router)"
                  >
                    <div class="menu__icon">
                      <ActivityIcon/>
                    </div>
                    <div class="menu__title">
                      {{ subMenu.title }}
                      <div
                          v-if="subMenu.subMenu"
                          :class="{
                          'transform rotate-180': subMenu.activeDropdown,
                        }"
                          class="menu__sub-icon"
                      >
                        <ChevronDownIcon/>
                      </div>
                    </div>
                  </a>
                  <!-- BEGIN: Third Child -->
                  <transition @enter="enter" @leave="leave">
                    <ul v-if="subMenu.subMenu && subMenu.activeDropdown">
                      <li
                          v-for="(lastSubMenu, lastSubMenuKey) in subMenu.subMenu"
                          :key="lastSubMenuKey"
                      >
                        <a
                            :class="{ 'menu--active': lastSubMenu.active }"
                            class="menu"
                            href="javascript:;"
                            @click="linkTo(lastSubMenu, router)"
                        >
                          <div class="menu__icon">
                            <ZapIcon/>
                          </div>
                          <div class="menu__title">
                            {{ lastSubMenu.title }}
                          </div>
                        </a>
                      </li>
                    </ul>
                  </transition>
                  <!-- END: Third Child -->
                </li>
              </ul>
            </transition>
            <!-- END: Second Child -->
          </li>
        </template>
        <!-- END: First Child -->
      </ul>
    </div>
  </div>
  <!-- END: Mobile Menu -->
</template>

<script setup>
import {computed, onMounted, provide, reactive, ref, watch} from "vue";
import {useRoute, useRouter} from "vue-router";
import {helper as $h} from "@/utils/helper";
// import {useSideMenuStore} from "@/stores/side-menu";
import {
  activeMobileMenu,
  toggleMobileMenu,
  linkTo,
  enter,
  leave,
} from "./index";
import {nestedMenu} from "@/layouts/side-menu";
import dom from "@left4code/tw-starter/dist/js/dom";
import SimpleBar from "simplebar";
import axios from "axios";

const route = useRoute();
const router = useRouter();
const formattedMenu = ref([]);
// const sideMenuStore = useSideMenuStore();
//  const sideMenuStore = useSideMenuStore();
const user = JSON.parse(sessionStorage.getItem('admin'));

let temp = ref([]);
onMounted(async () => {
  dom("body").removeClass("error-page").removeClass("login").addClass("main");
  let arr = [];
  let res = await axios.get('/menu/getMenus/' + user.rid);
  res.data.forEach(c => {
    if (c != null) {
      arr.push({
        icon: c.icon,
        pageName: c.pageName,
        title: c.title
      })
    }

  })
  arr.forEach((item, key) => {
    let menuItem = arr[key];
    menuItem.active = '';
    if (menuItem.pageName === route.name) {
      menuItem.active = route.name;
    }

  })

  temp.value = arr;

  formattedMenu.value = $h.toRaw(temp.value);


})
watch(route, (n, o) => {

  temp.value.forEach((item, key) => {
    let menuItem = temp.value[key];
    menuItem.active = '';
    if (menuItem.pageName === n.name) {
      menuItem.active = n.name;
    }

  })
}, {immediate: true})


//const sideMenu = computed(() => nestedMenu(sideMenuStore.menu, route));
provide("forceActiveMenu", (pageName) => {
  route.forceActiveMenu = pageName;
  formattedMenu.value = $h.toRaw(temp.value);
});

watch(
    computed(() => route.path),
    () => {
      delete route.forceActiveMenu;

      formattedMenu.value = $h.toRaw(temp.value);
    }
);

// const menus = reactive([
//   {
//     icon: "HomeIcon",
//     pageName: "top-menu-page-1",
//     title: "Page 1",
//   }
// ]);
// // const loadMenus = (rid) => {
// //   menus.value = [];
// //   axios({
// //     method: 'get',
// //     url: '/menu/getMenus/' + rid,
// //     data: {rid: rid}
// //   }).then(resp => {
// //
// //     menus.value = resp.data;
// //
// //   })
// // }
// // loadMenus(user.role_id);
//
//
// const mobileMenu = computed(() => nestedMenu(menus, route));
// watch(
//     computed(() => route.path),
//     () => {
//       formattedMenu.value = $h.toRaw(mobileMenu.value);
//     }
// );
//
// onMounted(() => {
//   new SimpleBar(dom(".mobile-menu .scrollable")[0]);
//   formattedMenu.value = $h.toRaw(mobileMenu.value);
// });
</script>

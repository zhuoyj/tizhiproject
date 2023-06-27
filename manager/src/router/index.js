import {createRouter, createWebHistory} from "vue-router";
import SideMenu from "../layouts/side-menu/Main.vue";
import Page1 from "../views/page-1/Main.vue";
import Page2 from "../views/page-2/Main.vue";
import login from "../pages/login.vue";
import userFrom from "../pages/users.vue";
import roles from "../pages/RoleManager/list.vue";
import index from '@/pages/index.vue'
import reg from '@/pages/reg.vue'
import teachers from '@/pages/UserManager/teachers.vue'
import students from '@/pages/UserManager/students.vue'
import tices from '@/pages/ActionsManager/list.vue'
import userClasses from '@/pages/UserClassManager/list.vue'
import jingsais from '@/pages/ActionsManager/jingsais.vue'

const routes = [
    {
        path: "/",
        component: login,
        name: "login"
    },
    {
        path: "/regView",
        name: "regView",
        component: reg
    },
    {
        path: "/main",
        component: SideMenu,
        children: [
            {
                path: "/main",
                name: "side-menu-page-1",
                component: Page1,
            },
            {
                path: "page-2",
                name: "side-menu-page-2",
                component: Page2,
            },
            {
                path: "/userView",
                name: "userView",
                component: userFrom,
            },
            {
                path: "/roleView",
                name: "roleView",
                component: roles,
            },

            {
                path: "/indexView",
                name: "indexView",
                component: index
            },
            {
                path: "/teacherView",
                name: "teacherView",
                component: teachers
            },
            {
                path: "/studentView",
                name: "studentView",
                component: students
            },
            {
                path: "/ticeView",
                name: "ticeView",
                component: tices
            },
            {
                path: "/classView",
                name: "classView",
                component: userClasses
            },
            {
                path: "/jingsaiView",
                name: "jingsaiView",
                component: jingsais
            },
        ],

    },

];

const router = createRouter({
    history: createWebHistory(),
    routes,
    scrollBehavior(to, from, savedPosition) {
        return savedPosition || {left: 0, top: 0};
    },
});

export default router;

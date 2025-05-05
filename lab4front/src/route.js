import { createRouter, createWebHistory } from 'vue-router';
import { useAuthStore } from '@/auth';
import StartPage from "@/components/startPage.vue";
import MainPage from "@/components/mainPage.vue";

const routes = [
    { path: '/', component: StartPage },
    { path: '/main', component: MainPage, meta: { requiresAuth: false } }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

router.beforeEach((to, from, next) => {
    const authStore = useAuthStore();
    if (to.meta.requiresAuth && !authStore.user) {
        return next('/');
    }
    next();
});

export default router;

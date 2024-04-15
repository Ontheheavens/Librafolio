import './assets/main.css'
import 'mdb-vue-ui-kit/css/mdb.min.css';

import { createApp } from 'vue'
import App from './App.vue'
import VueSidebarMenu from 'vue-sidebar-menu'
import 'vue-sidebar-menu/dist/vue-sidebar-menu.css'
import { createRouter, createWebHistory } from 'vue-router'
import HWView from "./components/helloworld/HWView.vue";
import Reader from "./components/Reader.vue";
import LibraryGrid from "./components/LibraryGrid.vue";
import {createPinia} from 'pinia'

const routes = [
    { path: '/', name: 'Hello World', component: HWView },
    { path: '/reader', name: 'Reader', component: Reader },
    { path: '/library', name: 'Library', component: LibraryGrid },
]

const router = createRouter({
    history: createWebHistory(), routes
})

const pinia = createPinia()

const app = createApp(App)
app.use(router)
app.use(pinia)
app.use(VueSidebarMenu)
app.mount('#app')

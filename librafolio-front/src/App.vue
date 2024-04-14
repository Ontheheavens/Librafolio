<script setup>
import Sidebar from './components/Sidebar.vue'
import {reactive} from "vue";

const sidebarState = reactive({
  sidebarIsCollapsed: true,
  isMobileDevice: false
})

const sidebarCollapse = (collapseState, mobileState) => {
  sidebarState.sidebarIsCollapsed = collapseState
  sidebarState.isMobileDevice = mobileState
};

</script>

<template>
  <Sidebar @collapseChange="sidebarCollapse"/>
  <div
      v-if="sidebarState.isMobileDevice && !sidebarState.sidebarIsCollapsed"
      class="sidebar-overlay"
  />
  <div id="contentContainer" :class="[
    { collapsedStyling: sidebarState.sidebarIsCollapsed },
    { mobileStyling: sidebarState.isMobileDevice }
  ]">
    <router-view />
  </div>
</template>

<style lang="scss">
#contentContainer {
  transition: padding-left 0.3s ease;
  justify-content: stretch;
  display: flex;
  padding-left: 290px;
}

#contentContainer.collapsedStyling {
  padding-left: 65px;
}

#contentContainer.mobileStyling {
  padding-left: 65px;
  display: grid;
}

.sidebar-overlay {
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  background-color: #000;
  opacity: 0.5;
  z-index: 900;
}
</style>

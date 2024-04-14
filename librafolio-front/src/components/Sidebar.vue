<template>
  <sidebar-menu
      v-model:collapsed="collapsed"
      :menu="menu"
      :theme="selectedTheme"
      :show-one-child="true"
      @update:collapsed="onToggleCollapse"
  />
</template>

<script>
import 'vue-sidebar-menu/dist/vue-sidebar-menu.css'
import { h } from 'vue'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faHand } from '@fortawesome/free-solid-svg-icons'
import { faBookOpenReader } from '@fortawesome/free-solid-svg-icons'
import { faBook } from '@fortawesome/free-solid-svg-icons'

const faIcon = (props) => {
  return {
    element: h('div', [h(FontAwesomeIcon, { size: 'lg', ...props })]),
  }
}

export default {
  name: "Sidebar",
  data() {
    return {
      menu: [
        {
          header: 'Navigation',
          hiddenOnCollapse: true,
        },
        {
          href: '/',
          title: 'Hello World',
          icon: faIcon({ icon: faHand }),
        },
        {
          href: '/reader',
          title: 'Reader',
          icon: faIcon({ icon: faBookOpenReader }),
        },
        {
          href: '/library',
          title: 'Library',
          icon: faIcon({ icon: faBook }),
        },
      ],
      collapsed: false,
      themes: [
        {
          name: 'Default theme',
          input: '',
        },
        {
          name: 'White theme',
          input: 'white-theme',
        },
      ],
      selectedTheme: '',
      isOnMobile: false,
    }
  },
  mounted() {
    this.onResize()
    window.addEventListener('resize', this.onResize)
  },
  methods: {
    onToggleCollapse(collapsed) {
      this.$emit('collapseChange', this.collapsed, this.isOnMobile)
    },
    onResize() {
      if (window.innerWidth <= 767) {
        this.isOnMobile = true
        this.collapsed = true
      } else {
        this.isOnMobile = false
        this.collapsed = false
      }
      this.$emit('collapseChange', this.collapsed, this.isOnMobile)
    },
  }
}
</script>

<style scoped>

</style>
import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

/**
 * @type {import('vite').UserConfig}
 */

// https://vitejs.dev/config/
export default defineConfig({
  server: {
    proxy: {
      "/api": {
        target: 'http://localhost:8098',
        changeOrigin: true,
        secure: false,
        ws: true,
      },
    },
  },
  plugins: [
    vue(),
  ],
  build: {
    sourcemap: true,
  },
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})

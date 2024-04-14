<template>
  <div class="readerContainer">
    <VuePdfEmbed annotation-layer
                 text-layer
                 :source="document"
                 width="1100"
                 @rendering-failed="handleRenderingFailed"/>
  </div>
</template>

<script setup lang="ts">
import VuePdfEmbed from 'vue-pdf-embed'
import 'vue-pdf-embed/dist/style/index.css'
import 'vue-pdf-embed/dist/style/annotationLayer.css'
import 'vue-pdf-embed/dist/style/textLayer.css'
import { useDocumentStore } from '@/store/currentDoc.js'

const documentStore = useDocumentStore()
let document = documentStore.getCurrentDocument()

const handleRenderingFailed = (value) => {
  console.log('Rendering failed:', value)
}

// console.log(document)
</script>

<style scoped>
.readerContainer {
  display: flex;
  justify-content: stretch;
  align-items: stretch;
}
</style>
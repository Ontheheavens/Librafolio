import { defineStore } from 'pinia'
import { useVuePdfEmbed } from 'vue-pdf-embed'

export const useDocumentStore = defineStore({
    id: 'document',
    state: () => ({
        documentCache: new Map(),
        currentDoc: 'https://raw.githubusercontent.com/mozilla/pdf.js/ba2edeae/web/compressed.tracemonkey-pldi-09.pdf',
    }),
    actions: {
        getDocumentByUrl(url) {
            let result = null
            if (this.documentCache.has(url)) {
                result = this.documentCache.get(url)
            } else {
                const {doc} = useVuePdfEmbed({source: url})
                this.documentCache.set(url, doc)
                result = doc
            }
            return result
        },
        getCurrentDocument() {
            const url = this.currentDoc
            let result = null
            if (url) {
                result = this.getDocumentByUrl(url)
            }
            return result
        },
        setCurrentDoc(url) {
            this.currentDoc = url
        },
    },
})
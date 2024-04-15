import { defineStore } from 'pinia'
import { useVuePdfEmbed } from 'vue-pdf-embed'

export const useDocumentStore = defineStore({
    id: 'document',
    state: () => ({
        documentCache: new Map(),
        currentDoc: 'https://raw.githubusercontent.com/mozilla/pdf.js/ba2edeae/web/compressed.tracemonkey-pldi-09.pdf',
    }),
    actions: {
        // Caching does not work here, reasons unknown so far. Leave as is for now.
        getDocumentByUrl(url) {
            const {doc} = useVuePdfEmbed({source: url})
            return doc
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
import { defineStore } from 'pinia'
import { useVuePdfEmbed } from 'vue-pdf-embed'

export const useDocumentStore = defineStore({
    id: 'document',
    state: () => ({
        documents: [
            {
                title: "Trace-based Just-in-Time Type Specialization for Dynamic\n" +
                    "Languages",
                description: "Example Javascript paper.",
                thumbnail: null,
                pdfLink: "https://raw.githubusercontent.com/mozilla/pdf.js/ba2edeae/web/compressed.tracemonkey-pldi-09.pdf"
            },
            {
                title: "Document 2",
                description: "This is the second document",
                thumbnail: "https://mdbootstrap.com/img/new/standard/nature/182.webp",
                pdfLink: "https://example.com/document2.pdf"
            },
            {
                title: "Document 3",
                description: "This is the third document",
                thumbnail: "https://example.com/thumbnail3.jpg",
                pdfLink: "https://example.com/document3.pdf"
            }
        ],
        currentDoc: 0,
    }),
    actions: {
        // Caching does not work here, reasons unknown so far. Leave as is for now.
        getDocumentByUrl(url) {
            const {doc} = useVuePdfEmbed({source: url})
            return doc
        },
        getCurrentDocument() {
            const url = this.documents[this.currentDoc].pdfLink
            let result = null
            if (url) {
                result = this.getDocumentByUrl(url)
            }
            return result
        },
        setCurrentDoc(index) {
            this.currentDoc = index
        },
    },
})
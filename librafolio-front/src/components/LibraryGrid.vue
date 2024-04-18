<template>
  <div class="d-flex justify-content-between align-items-center mb-4">
    <h2>Document List</h2>
    <MDBBtn @click="showCreateForm = true">Create Document</MDBBtn>

    <MDBModal v-model="showCreateForm" title="Create Document">
      <form @submit.prevent="createDocument(newDocument)">
        <MDBInput label="Title" v-model="newDocument.title" required />
        <MDBInput label="Description" v-model="newDocument.description" required />
        <MDBInput label="Thumbnail URL" v-model="newDocument.thumbnail" required />
        <MDBInput label="PDF Link" v-model="newDocument.pdfLink" required />
        <MDBBtn type="submit">Create</MDBBtn>
      </form>
    </MDBModal>
  </div>

  <MDBContainer class="my-5">
    <MDBRow :cols="['1','md-2']" class="g-4">
      <MDBCol v-for="(document, index) in documents" :key="index">
        <MDBCard>
          <!-- Show the image if the thumbnail is not null and the request is successful -->
          <template v-if="document.thumbnail && thumbnailStatus[index] === 'success'">
            <MDBCardImg :src="document.thumbnail" top alt="Thumbnail"/>
          </template>
          <MDBCardBody>
            <MDBCardTitle>{{ document.title }}</MDBCardTitle>
            <MDBCardText>{{ document.description }}</MDBCardText>
            <MDBCardLink :href="document.pdfLink" target="_blank">View PDF</MDBCardLink>
          </MDBCardBody>
        </MDBCard>
      </MDBCol>
    </MDBRow>
  </MDBContainer>
</template>

<script setup lang="ts">
import { useDocumentStore } from '../store/currentDoc.js';
import { ref, onMounted } from 'vue';
import { MDBContainer, MDBModal, MDBInput, MDBBtn, MDBCardLink,
  MDBCol, MDBRow, MDBCard, MDBCardBody, MDBCardTitle, MDBCardText, MDBCardImg } from "mdb-vue-ui-kit";

const documentStore = useDocumentStore();
const documents = ref([]);
const thumbnailStatus = ref([]);

const showCreateForm = ref(false);

const newDocument = ref({
  title: '',
  description: '',
  thumbnail: '',
  pdfLink: ''
});

const createDocument = (newDocument) => {
  console.log('Creating document:', newDocument);
  // Add the new document to the document list
  documentStore.addDocument(newDocument);

  newDocument.value = {
    title: '',
    description: '',
    thumbnail: '',
    pdfLink: ''
  };

  // Reset the form
  showCreateForm.value = false;
};

onMounted(() => {
  documents.value = documentStore.documents;
  // Initialize thumbnail status array
  thumbnailStatus.value = Array(documents.value.length).fill(null);

  // Check thumbnail status for each document
  documents.value.forEach((document, index) => {
    const img = new Image();
    img.src = document.thumbnail;
    img.onload = () => {
      thumbnailStatus.value[index] = 'success';
    };
    img.onerror = () => {
      thumbnailStatus.value[index] = 'error';
    };
  });
});
</script>

<style scoped>

</style>
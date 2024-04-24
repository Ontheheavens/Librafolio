<template>
  <div>
    <div class="d-flex justify-content-between align-items-center mb-4 mt-4">
      <div class="justify-content-center m-auto">
        <h2>Document List</h2>
      </div>
      <MDBBtn @click="showCreateForm = true">Create Document</MDBBtn>
      <MDBModal v-model="showCreateForm" title="Create Document">
        <MDBModalHeader>
          <MDBModalTitle id="exampleModalLabel"> Create Document </MDBModalTitle>
        </MDBModalHeader>
        <MDBModalBody class="p-3">
          <form @submit.prevent="createDocument(newDocument)">
            <MDBInput class="m-2" label="Title" v-model="newDocument.title" required />
            <MDBTextarea class="my-2" label="Description" v-model="newDocument.description" required />
            <MDBInput class="m-2" label="Thumbnail URL" v-model="newDocument.thumbnail" required />
            <MDBInput class="m-2" label="PDF Link" v-model="newDocument.pdfLink" required />
            <MDBBtn class="mt-2" type="submit">Create</MDBBtn>
          </form>
        </MDBModalBody>
      </MDBModal>
    </div>

    <MDBContainer class="my-5">
      <MDBRow :cols="['1','md-2']" class="g-4">
        <MDBCol v-for="(document, index) in documents" :key="index">
          <MDBCard>
            <MDBCardHeader class="text-center">
              <MDBCardTitle>{{ document.title }}</MDBCardTitle>
              <!-- Show the image if the thumbnail is not null and the request is successful -->
              <template v-if="document.thumbnail">
                <MDBCardImg class="justify-content-center m-auto" :src="document.thumbnail" top alt="Thumbnail"/>
              </template>
            </MDBCardHeader>
            <MDBCardBody>
              <MDBCardText>{{ document.description }}</MDBCardText>
            </MDBCardBody>
            <MDBCardFooter class="text-muted d-flex justify-content-between align-middle">
              <MDBCardLink class="mt-2" :href="document.pdfLink" target="_blank">View PDF</MDBCardLink>
              <MDBBtn @click="removeDocument(document)">Remove</MDBBtn>
            </MDBCardFooter>
          </MDBCard>
        </MDBCol>
      </MDBRow>
    </MDBContainer>
  </div>
</template>

<script setup>
import { useDocumentStore } from '../store/currentDoc.js';
import { ref, onMounted } from 'vue';
import { MDBContainer, MDBCardHeader, MDBCardFooter, MDBModalHeader, MDBTextarea, MDBModalBody,
  MDBModalTitle, MDBModal, MDBInput, MDBBtn, MDBCardLink,
  MDBCol, MDBRow, MDBCard, MDBCardBody, MDBCardTitle, MDBCardText, MDBCardImg } from "mdb-vue-ui-kit";

const documentStore = useDocumentStore();
const documents = ref([]);
const showCreateForm = ref(false);

const newDocument = ref({
  title: '',
  description: '',
  thumbnail: '',
  pdfLink: ''
});

const removeDocument = (document) => {
  documentStore.removeDocument(document);
};

const createDocument = (newDocument) => {
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
});
</script>

<style scoped>

</style>
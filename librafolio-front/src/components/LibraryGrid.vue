<template>
    <MDBContainer class="my-5">
      <MDBRow :cols="['1','md-2']" class="g-4">
        <MDBCol>
          <MDBCard>
            <MDBCardImg src="https://mdbootstrap.com/img/new/standard/city/041.webp" top alt="..."/>
            <MDBCardBody>
              <MDBCardTitle>Card title</MDBCardTitle>
              <MDBCardText>
                This is a longer card with supporting text below as a natural lead-in to
                additional content. This content is a little bit longer.
              </MDBCardText>
            </MDBCardBody>
          </MDBCard>
        </MDBCol>
        <MDBCol>
          <MDBCard>
            <MDBCardImg src="https://mdbootstrap.com/img/new/standard/city/042.webp" top alt="..."/>
            <MDBCardBody>
              <MDBCardTitle>Card title</MDBCardTitle>
              <MDBCardText>
                This is a longer card with supporting text below as a natural lead-in to
                additional content. This content is a little bit longer.
              </MDBCardText>
            </MDBCardBody>
          </MDBCard>
        </MDBCol>
        <MDBCol>
          <MDBCard>
            <MDBCardImg src="https://mdbootstrap.com/img/new/standard/city/043.webp" top alt="..."/>
            <MDBCardBody>
              <MDBCardTitle>Card title</MDBCardTitle>
              <MDBCardText>
                This is a longer card with supporting text below as a natural lead-in to
                additional content.
              </MDBCardText>
            </MDBCardBody>
          </MDBCard>
        </MDBCol>
        <MDBCol>
          <MDBCard>
            <MDBCardImg src="https://mdbootstrap.com/img/new/standard/city/044.webp" top alt="..."/>
            <MDBCardBody>
              <MDBCardTitle>Card title</MDBCardTitle>
              <MDBCardText>
                This is a longer card with supporting text below as a natural lead-in to
                additional content. This content is a little bit longer.
              </MDBCardText>
            </MDBCardBody>
          </MDBCard>
        </MDBCol>
      </MDBRow>
    </MDBContainer>
</template>

<script setup lang="ts">
import { MDBContainer, MDBCol, MDBRow, MDBCard, MDBCardBody, MDBCardTitle, MDBCardText, MDBCardImg } from "mdb-vue-ui-kit";
</script>

<style scoped>

</style>
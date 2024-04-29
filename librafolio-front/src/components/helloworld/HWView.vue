<template>
  <header>
    <img alt="Vue logo" class="logo" src="../../assets/logo.svg" width="125" height="125" />
    <div class="wrapper">
      <HelloWorld msg="You did it!" />
      <p>{{ newMsg }}</p>
    </div>
  </header>
  <div class="readerContainer">
    <TheWelcome />
  </div>
</template>

<script setup lang="js">
import HelloWorld from './HelloWorld.vue'
import TheWelcome from './TheWelcome.vue'
import api from "../../api/backend.ts";
import {onMounted, ref} from "vue";

const newMsg = ref("PENDING");

onMounted(() => {
  // axios.get("http://localhost:8080/api/hello").then(response => {
  //   newMsg.value = response.data.content
  // })
  api.hello().then(response => {
    console.log("response")
    console.log(response.data)
    newMsg.value = response.data.text
  }, error => {
    console.log(error.toString())
  }).catch(error => {
    console.log(error.toString())
  })
})

</script>

<style scoped>
header {
  line-height: 1.5;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}

.readerContainer {
  margin-top: auto;
  margin-bottom: auto;
  display: block;
}

</style>
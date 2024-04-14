<script setup lang="ts">
defineProps({
  msg: {
    type: String,
    required: true
  }
});
function testFunc(text: string) {
  console.log(text);
}
</script>

<div></div>

<template>
  <div class="greetings">
    <h1 class="green">{{ msg }}</h1>
    <h3>
      You’ve successfully created a project with
      <a href="https://vitejs.dev/" target="_blank" rel="noopener">Vite</a> +
      <a href="https://vuejs.org/" target="_blank" rel="noopener">Vue 3</a>.
    </h3>
<!--    <div class="test-container">-->
<!--      <section>-->
<!--        <button-->
<!--            class="bg-green-500 text-white px-4 py-2 rounded-3xl hover:bg-blue-500 hover:rounded transition-all duration-300"-->
<!--            @click="testFunc('test')"-->
<!--        >-->
<!--          test-->
<!--        </button>-->
<!--        <button class="bg-amber-400 p-3 transition-all duration-300 ease-in-out hover:rounded-lg">-->
<!--          Your Button-->
<!--        </button>-->
<!--      </section>-->
<!--    </div>-->
  </div>
</template>

<style scoped>
h1 {
  white-space: nowrap;
  font-weight: 500;
  font-size: 2.6rem;
  position: relative;
  top: -10px;
}

h3 {
  font-size: 1.2rem;
}

div.test-container {
  display: flex;
  justify-content: center;
  color: red;
  background-color: aquamarine;
}

.greetings h1,
.greetings h3 {
  text-align: center;
}

@media (min-width: 1024px) {
  .greetings h1,
  .greetings h3 {
    text-align: left;
  }
}
</style>

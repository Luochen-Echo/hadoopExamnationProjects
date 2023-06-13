<template>
  <NavBar />
  <router-view />
</template>

<script>
import NavBar from './components/NavBar.vue'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"
// import $ from 'jquery'

export default {
  components: {
    NavBar
  },


}

</script>

<style>
body {
  background-image: url("https://pic4.zhimg.com/v2-847c59a73b525acf850a3acc2b6244cf_r.jpg");
  background-size: cover;
  background-repeat: no-repeat;
}
</style>

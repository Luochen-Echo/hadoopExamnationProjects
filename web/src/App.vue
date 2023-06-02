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
  background-image: url("https://s1.ax1x.com/2022/10/16/xBcp6S.jpg");
  background-size: cover;
  background-repeat: no-repeat;
}
</style>

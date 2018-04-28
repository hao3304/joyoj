<template>
  <div id="app">
    <img src="./assets/logo.png">
    <router-view/>


    <div>
     username: <input v-model="form.username " />
      <br>
      password: <input v-model="form.password" />

      <Button @click="onLogin">登录</Button>

    </div>
  </div>
</template>

<script>
  import axios from 'axios'
export default {
  name: 'App',
  data() {
    return {
      form: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    onLogin() {
      debugger
      axios.post('/admin/login', this.form).then(rep=>{
        debugger
      })
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

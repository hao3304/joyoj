import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    app: {
       state: {
         token: ''
       },
      mutations : {
         setToken (state, token) {
           state.token = token;
         }
      }
    }
  }
})

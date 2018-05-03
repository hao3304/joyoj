<template>
  <div id="app" v-if="isLogin">
    <navigation></navigation>
    <router-view/>
  </div>
  <div id="app" v-else>
    <Login></Login>
  </div>
</template>

<script>
  import navigation from '@/components/navigation'
  import Login from '@/views/login'
  import Cookies from 'js-cookie'
  export default {
    name: 'App',
    data() {
      return {

      }
    },
    computed: {
      isLogin () {
        return this.$store.state.app.token?true:false
      }
    },
    components:{
      navigation,
      Login
    }
  }
</script>
<style lang="less">
  @import "//at.alicdn.com/t/font_568396_abo8m578w3r3sor.css";

  html,body {
    padding: 0;
    margin: 0;
    overflow: hidden;
    height: 100%;
    min-width: 1200px;

  }
  #app {
    height: 100%;
    font-family: 'Helvetica Neue',Helvetica,'PingFang SC','Hiragino Sans GB','Microsoft YaHei',Arial,sans-serif ;
    background-color: #f6f6f6;
    color: rgb(85, 85, 85);
    font-weight: 400;
    display: flex;
    flex-direction: column;

    .slider-menu {
      .ivu-menu-vertical.ivu-menu-light:after {
        content: none;
      }

      .ivu-layout-sider-children{
        border-right: 1px solid #dddee1;
        box-shadow: 2px 0 2px rgba(0,0,0,.1);
      }

    }

    .collapsed-bar {
      text-align: center;
      display: none;
      font-size: 18px;
      line-height: 50px;
      height: 50px;
      background-color: #fafafa;
      cursor: pointer;
      border-bottom: 1px solid #ddd;
      color: #999;
    }

    .ivu-menu-light.ivu-menu-vertical .ivu-menu-item-active:not(.ivu-menu-submenu) {
      background-color: #f0f8ff;
    }

  }
</style>

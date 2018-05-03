<template>
  <header class="navigation">
    <span class="user-menu" @mouseenter="onMouseEnter" @mouseleave="onMouseLeave">
      <div class="user-avatar">
        <Avatar icon="person" />
      </div>
      <div class="user-info">
        <h5>李大嘴 <Icon type="ios-arrow-down"></Icon></h5>
        <p>上海外国语大学附中编程班</p>
      </div>
      <div id="dropdown-user" class="dropdown-user">
            <p class="dropdown-remark">
              在此您可以切换班级，还可以加入班级
            </p>
          <ul class="dropdown-list">
            <li>
              <a href="#"><Icon type="checkmark"></Icon>  上海外国语大学附中编程班</a>
            </li>
          </ul>
          <div class="dropdown-btn">
            <a href="#">
                <Icon type="plus"></Icon>
              加入班级</a>
          </div>
        </div>
    </span>
    <ul class="nav">
      <li :class="{active: active == m.url}" v-for="(m, index) in menus" :key="index">
        <a href="javascript:;" @click="onPath(m.url)">
          <Icon :type="m.icon"></Icon> {{m.name}}
        </a>
      </li>
      <li>
        <a href="javascript:;">更多应用 <Icon type="ios-arrow-down"></Icon></a>
      </li>
    </ul>

    <ul class="info">
      <li>
        <Icon type="ios-bell"></Icon>
      </li>
      <li>
        <Poptip placement="bottom" >
          <div class="dropdown" slot="content">
            <ul>
              <li @click="onLogout">
                <Icon type="log-out"></Icon>
                退出
              </li>
            </ul>
          </div>
          <a href="javascript:;">
            &nbsp;在线编程平台
          </a>
        </Poptip>
      </li>
    </ul>
  </header>
</template>
<script>
  import Cookies from 'js-cookie'

  export default {
    name:'navigation',
    data() {
        return {
          dropUserHeight: 0,
          active: 'home',
          menus:[
            {
              name: '首页',
              icon: 'android-home',
              url: 'home'
            },
            {
              name: '课程',
              icon: 'ios-book',
              url: 'lesson'
            }
          ]
        }
    },
    methods: {
      onLogout() {
        Cookies.remove('token');
        this.$store.commit('setToken','')
        this.$router.push('/login')
      },
      onMouseEnter() {
        this.$dropUser.height(0).show();
          setTimeout(()=>{
            this.$dropUser.animate({height:this.dropUserHeight},'fast','swing')
          },400)
      },
      onMouseLeave() {
        this.$dropUser.animate({height:0},'fast','swing', ()=> {
          this.$dropUser.hide().height(this.dropUserHeight);
        })
      },
      onPath(url) {
        if(this.active != url) {
          this.$router.push({name: url});
          this.active = url;
        }
      }
    },
    created() {
      this.active = window.location.hash.split("/")[1] || 'home';
    },
    mounted() {
      this.$dropUser =  $("#dropdown-user");
      this.dropUserHeight = this.$dropUser.height();
    }
  }
</script>
<style lang="less">
  .navigation{
    height: 52px;
    background-color: #065389;
    box-shadow: 0 2px 3px rgba(0,0,0,.3);
    padding: 0 40px 0 0;
    z-index: 1000;

    .ivu-poptip-body{
      padding: 0;
      .dropdown{
        ul{
          li{
            display: block;
            text-align: center;
            padding: 0;
            font-size: 14px;
            line-height: 36px;
            cursor: pointer;
            color: #666;
            .ivu-icon{
              font-size: 16px;
            }
            &:hover{
              background-color: #f1f1f1;
            }
          }
        }
      }
    }

    .user-menu {
      float: left;
      width: 220px;
      position: relative;
      transition: background-color .4s;
      &:hover{
        background-color: #3a3a96;
      }
      .user-avatar {
        float: left;
        width: 60px;
        height: 52px;
        text-align: center;
        padding-top: 10px;
      }
      .user-info {
        float: left;
        width: 160px;
        text-align: left;
        color: #fff;
        cursor: pointer;
        h5 {
          font-size: 14px;
          font-weight: normal;
          padding-top: 6px;
        }
        p {
          color: rgba(255,255,255,.8);
        }


      }

      .dropdown-user{
        position: absolute;
        z-index: 1000;
        top: 52px;
        background-color: #fff;
        width: 309px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.3);
        display: none;
        overflow: hidden;

        .dropdown-remark{
          background-color: #fafafa;
          padding: 15px;
          color: #999;
        }

        .dropdown-list {
          padding: 15px 0;
          border-top: 1px solid #f2f2f2;
          border-bottom: 1px solid #eaeaea;
          li{
            list-style: none;

            a{
              display: block;
              font-size: 14px;
              padding-left: 20px;
              line-height: 38px;
              color: #666;
            }
            &:hover {
              background-color: #f2f2f2;
            }
          }
        }
        .dropdown-btn{
          padding: 12px 0;
          font-size: 14px;
          &:hover{
            background-color: #f2f2f2;
          }
          a{
            display: block;
            padding-left: 40px;
          }
        }
      }
    }

    .logo{
      float: left;
      line-height: 52px;
      color: #fff;
      font-size: 22px;
      font-weight: bold;
    }

    .nav{
      float: left;
      margin: 0;
      font-size: 0;
      li{
        display: inline-block;
        list-style: none;
        a{
          font-size: 16px;
          display: block;
          color: #fff;
          padding: 0 30px;
          text-decoration: none;
          line-height: 52px;
          position: relative;
          transition: all .4s;
          &:hover{
            background-color: #0973bd;
          }
        }

        &.active{
          a{
            background-color: #0973bd;
          }
        }
      }
    }

    .info{
      float: right;
      li{
        display: inline-block;
        line-height: 52px;
        text-align: center;
        padding: 0 10px;
        font-size: 16px;
        .ivu-icon-ios-bell{
          font-size: 28px;
          vertical-align: middle;
          cursor: pointer;
          color: #fff;
        }
        a{
          display: block;
          color: #fff;
        }
      }
    }
  }
</style>

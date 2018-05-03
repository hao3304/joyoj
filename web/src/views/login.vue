<template>
  <div class="login">
    <section class="login-block " :class="{'register-block': tab == 'register'}">
      <h5>爱学习，爱编程</h5>
      <ul class="tab">
        <li :class="{active:tab == 'login'}" @click="tab = 'login'">
          <a href="javascript:;">登录</a>
        </li>
        <li :class="{active: tab == 'register'}" @click="tab = 'register'" >
          <a  href="javascript:;">注册</a>
        </li>
      </ul>
      <div class="form" v-show="tab == 'login'">
        <input v-model="form.username" required type="text" placeholder="请输入您的用户名">
        <input v-model="form.password" required type="password" placeholder="请输入您的密码">
      </div>

      <Form ref="registerForm" :model="info" :label-width="60" v-show="tab == 'register'" :rules="rules">
        <FormItem label="姓名" prop="name">
          <Input v-model="info.name" placeholder="请输入姓名"></Input>
        </FormItem>
        <!--<FormItem label="生日"  prop="birthday">-->
        <!--<DatePicker  type="date" style="width: 100%" placeholder="请选择生日" v-model="info.birthday"></DatePicker>-->
        <!--</FormItem>-->
        <FormItem label="性别" >
          <RadioGroup v-model="info.sex">
            <Radio :label="1">男</Radio>
            <Radio :label="0">女</Radio>
          </RadioGroup>
        </FormItem>

        <FormItem label="邮箱" prop="username">
          <Input v-model="info.username" placeholder="请输入邮箱"></Input>
        </FormItem>
        <FormItem class="ivu-form-item-required" label="密码" prop="password">
          <Input v-model="info.password" type="password" placeholder="请输入密码"></Input>
        </FormItem>
        <FormItem label="确认密码" prop="passwordCheck">
          <Input v-model="info.passwordCheck" type="password" placeholder="请确认密码"></Input>
        </FormItem>
      </Form>

      <Button @click="onLogin" v-show="tab == 'login'" :loading="loading" class="submit">
        登录
      </Button>

      <Button @click="onRegister" v-show="tab == 'register'" type="success" long  :loading="loading">
        注册
      </Button>
      <p class="error">{{errmsg}}</p>

    </section>
    <vue-particles color="#fff"></vue-particles>
  </div>
</template>
<script>
  import { Login,Register,Check } from '@/services/login'
  import Cookies from 'js-cookie'

  const Model = ()=>{
    return {
      name: '',
      sex: 1,
      username: '',
      password: '',
      passwordCheck: '',
      type: 0 //学生 1 老师
    }
  }

  export default {
    name: 'login',
    data(){
      const validatePass = (rule, value, callback) => {
        if (value === '' || value.length <6 || value.length>20) {
          callback(new Error('密码不少于6位且不能超过20位'));
        } else {
          if (this.info.passwordCheck !== '') {
            // 对第二个密码框单独验证
            this.$refs.registerForm.validateField('passwordCheck');
          }
          callback();
        }
      };
      const validatePassCheck = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请确认您的密码'));
        } else if (value !== this.info.password) {
          callback(new Error('两次密码不一致!'));
        } else {
          callback();
        }
      };

      const validateUsername = (rule, value, callback) => {
        Check(value).then(rep=>{
          if(rep.status == 0) {
            callback();
          }else{
            callback(new Error(rep.message));
          }
        })
      }

      return {
        form:{
          username:'',
          password:''
        },
        info: Model(),
        loading: false,
        errmsg: '',
        tab: "login",
        rules: {
          name: [
            {required:true, message:'必填',trigger: 'blur'}
          ],
          birthday: [
            {required:true, message:'必填',type:'date', trigger: 'blur'}
          ],
          username: [
            {required:true, message:'必填',trigger: 'blur'},
            {type: 'email', message:'邮箱格式错误', trigger: 'blur'},
            { validator: validateUsername, trigger: 'blur' }
          ],
          password: [
            { validator: validatePass, trigger: 'blur' }
          ],
          passwordCheck: [
            { validator: validatePassCheck, trigger: 'blur' }
          ],
        }
      }
    },
    methods: {
      onLogin () {
        this.loading =true;
        Login(this.form).then(rep=>{
          this.loading = false;
          if(rep.status == 0 ) {
            this.$Message.success('登录成功！');
            this.errmsg = "";
            Cookies.set('token', rep.result , { expires: 1 });
            this.$store.commit('setToken', rep.result)
            this.$router.push('/home')
          }else{
            this.errmsg = rep.message;
          }
        }).catch(e=> {
          this.loading = false;
          this.errmsg = '服务错误！';
        })
      },
      onRegister() {
        this.$refs['registerForm'].validate(valid=> {
          if(valid) {
            let p = {...this.info};
            delete p['passwordCheck'];
            this.loading = true;
            Register(p).then(rep=> {
              if(rep.status == 0) {
                this.$Message.success('注册成功！',{duration: 4});
                this.info = Model();
                this.tab = 'login';
              }else{
                this.$Message.alert(rep.message);
              }
              this.loading = false;
            }).catch(err=>{
              this.loading = false;
            })
          }
        })
      }
    },
    watch: {
      tab () {
        this.$refs['registerForm'].resetFields();
      }
    }
  }
</script>
<style lang="less">
  .login {
    height: 100%;
    position: relative;
    background: url("~/static/images/bg.jpg") no-repeat;

    .login-block{
      text-align: center;
      background-color: rgba(255,255,255,.9);
      position: absolute;
      width: 300px;
      border-radius: 5px;
      left: 50%;
      transform: translateX(-50%) translateY(-50%);
      box-sizing: border-box;
      padding: 0 10px 10px 10px;
      top: 50%;
      transition: all .4s;

      &.register-block {
        width: 400px;

        .ivu-form-item-content{
          text-align: left;
        }
      }

      h5{
        font-size: 18px;
        font-weight: 400;
        color: #555;
        margin: 30px 0 20px 0;
      }

      ul.tab {
        padding: 0;
        margin-bottom: 15px;
        li {
          list-style: none;
          display: inline-block;
          width: 75px;
          a{
            display: block;
            text-decoration: none;
            text-align: center;
            color: #555;
            opacity: .7;
            line-height: 35px;
            transition:opacity .15s,color .15s;
            position: relative;
            font-size: 16px;
          }

          &.active {
            a{
              color: #0f88eb;
              opacity: 1;
              &:after{
                content: ' ';
                position: absolute;
                bottom:0;
                left: 50%;
                width:40px;
                height:2px;
                background-color: #0f88eb;
                margin-left: -20px;
              }
            }
          }
        }
      }

      .form{
        border:1px solid #d5d5d5;
        border-radius: 3px;
        background: rgba(255,255,255,0.5);
        input {
          display: block;
          width: 100%;
          border:none;
          color: #666;
          font-family: 'Microsoft Yahei';
          padding: 1em .8em;
          box-sizing: border-box;

          &:first-child{
            border-bottom: 1px solid #ddd;
          }
        }
      }

      .submit{
        width: 100%;
        border:none;
        margin: 20px 0 0 0;
        background-color: #0f88eb;
        border-radius: 3px;
        line-height: 41px;
        color: #fff;
        box-shadow: none;
        font-family: 'Microsoft Yahei';
        font-size: 15px;
        padding: 0;
        cursor: pointer;

        &:hover {
          background-color: #2d96ec;
        }
      }

      .error {
        color: red;
        padding-top: 10px;
      }
    }
  }
</style>

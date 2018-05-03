<template>
  <Layout >
    <Sider width="220" class="slider-menu"  hide-trigger :style="{background: '#fff'}" >

      <div class="collapsed-bar">
        <Icon type="navicon-round"></Icon>
      </div>

      <Menu active-name="1-1" theme="light" width="auto" :open-names="['1']">
        <MenuItem name="1-1"><Icon type="heart-broken"></Icon>课程列表</MenuItem>
        <MenuItem name="1-2"><Icon type="heart-broken"></Icon>学情查看</MenuItem>
      </Menu>
    </Sider>
    <Content :style="{minHeight: '260px'}"> <router-view/> </Content>
  </Layout>

</template>

<script>
  export default {
    name: "index",
    beforeRouteEnter (to, from, next) {
      next(vm=>{
        vm.$router.push({name: 'lesson-mine'})
      })
    }
  }
</script>

<style scoped>

</style>

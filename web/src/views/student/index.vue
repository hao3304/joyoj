<template>
  <div>
    <navigation></navigation>
    <router-view/>
  </div>
</template>

<script>
  import navigation from '@/components/navigation'

  export default {
    name: 'index',
    components: {
      navigation
    }
  }
</script>

<style scoped>

</style>

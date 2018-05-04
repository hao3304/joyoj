<template>
    <div>
        <div class="y-toolbar">
            <div class="y-query">
                <Form :label-width="80" :model="query" ref="queryForm"  inline>
                    <FormItem label="用户名">
                        <Input placeholder="请输入用户名" v-model="query.username" />
                    </FormItem>
                </Form>
            </div>
            <div class="y-query-btn">
                <Button type="success" icon="search" @click="onQuery" >查询</Button>
                <Button  icon="refresh" @click="onReset">重置</Button>
            </div>
            <!--<div class="y-add-btn">-->
                <!--<Button  type="primary" icon="plus" @click="onAdd">新增用户</Button>-->
            <!--</div>-->
        </div>
        <div class="y-table">
            <Table  :loading="loading" :height="600" :columns="columns" :data="data" ></Table>
            <div class="page-block">
                <Page :total="total" size="small" :page-size="query.size" @on-page-size-change="onPageSizeChange" @on-change="onPageChange" show-elevator show-total show-sizer></Page>
            </div>
        </div>
    </div>
</template>

<script>

    import studentService from '@/services/student';

    const UserModel =()=>{
        return {
            id:'',
            username:'',
            roles:[],
            password:'',
            emp_id:'',
            emp_name:'',
            remark:''
        }
    }

    export default {
        name: "student_index",
        data(){
            return {
                columns:[
                    {
                        title:"#",
                        render:(h,params)=>{
                            let current = this.query.size*(this.query.page-1)+params.index +1;
                            return h('div',{},current);
                        },
                        width:60
                    },
                    {
                        title: '姓名',
                        key: 'name'
                    },
                    {
                        title: '帐号',
                        key: 'username'
                    },
                    {
                        title: '性别',
                        key: 'sex',
                        render: (h, {row}) =>{
                            return h('div',{}, row.sex == 1? '男':'女')
                        }
                    },
                    {
                        title: '注册时间',
                        key: 'createTime',
                        render: (h, {row}) =>{
                            return h('div',{}, new Date(row.createTime).format('yyyy-MM-dd hh:mm'))
                        }
                    }
                ],
                data:[],
                query:{
                    size:10,
                    page:1,
                    username:''
                },
                total:0,
                rules: {}

            }
        },
        methods:{
            render() {
                this.loading = true;
                studentService.findAll({page:1,size:10}).then(rep=>{
                    if(rep.status == 0) {
                        this.total = rep.result.total;
                        this.data = rep.result.data;
                    }
                    this.loading = false;
                })
            },
            onPageSizeChange(size) {
                this.query.size = size;
                this.render();
            },
            onPageChange(page) {
                this.query.page = page;
                this.render();
            },
            onQuery() {
                this.render();
            },
            onReset() {

            }
        },
         mounted(){
            this.render();
        }
    }
</script>

<style lang="less" scoped>

</style>
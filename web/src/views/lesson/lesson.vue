<template>
    <div>
      <div class="toolbar">
        <Form ref="formInline"  inline>
          <FormItem >
            <Input type="text"  placeholder="课程名称">
            </Input>
          </FormItem>
          <FormItem >
            <Input type="text"  placeholder="班级名称">
            </Input>
          </FormItem>
          <FormItem>
            <Button type="primary" icon="search">查询</Button>
          </FormItem>
        </Form>
      </div>
      <div class="content">
        <Table :columns="columns">
        </Table>
        <div class="page-block"><Page :total="100" show-elevator></Page> </div>
      </div>
    </div>
</template>

<script>
    export default {
        name: "lesson",
        data() {
          return {
            columns: [
              {
                title: '序号'
              },
              {
                title: '课程名称'
              },
              {
                title: '封面'
              },
              {
                title: '班级'
              },
              {
                title: '创建时间'
              },
              {
                title: '操作'
              }
            ]
          }
        }
    }
</script>

<style lang="less" scoped>
  .content{
    padding: 20px;
    .page-block {
      text-align: right;
      padding: 10px 0;
    }
  }
  .toolbar{
    height: 50px;
    line-height: 50px;
    background-color: #fff;
    border-bottom: 1px solid #ddd;
    padding:8px 20px;
    .ivu-form-item{
      margin-bottom: 0;
    }
  }
</style>

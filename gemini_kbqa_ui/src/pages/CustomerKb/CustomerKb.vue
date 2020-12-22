<template>
  <el-container style="height: 700px; border: 1px solid #eee">
  <el-aside width="200px"  style="background-color: rgb(238, 241, 246)">
    <el-menu :default-openeds="['1']">
      <el-submenu index="1">
        <template slot="title"><i class="el-icon-message"></i>全部分类</template>
          <el-menu-item index="1-1">选项1</el-menu-item>
          <el-menu-item index="1-2">选项2</el-menu-item>
          <el-menu-item index="1-3">选项3</el-menu-item>
          <el-menu-item index="1-5">选项4</el-menu-item>
      </el-submenu>
      <el-menu-item index="2">管理分类</el-menu-item>
    </el-menu>
  </el-aside>
  
  <el-container>
    <el-header style="text-align: right; font-size: 12px">
      
      <span>王小虎</span>
    </el-header>
    
    <el-main>
      <el-table :data="tableData">
        <el-table-column prop="date" label="资料名称" width="140">
        </el-table-column>
        <el-table-column prop="name" label="内容摘要" width="120">
        </el-table-column>
         <el-table-column prop="name" label="类型" width="120">
        </el-table-column>
        <el-table-column prop="address" label="创建人">
        </el-table-column>
        <el-table-column prop="date" label="创建日期">
        </el-table-column>
        <el-table-column prop="date" label="操作">
        </el-table-column>
      </el-table>
    </el-main>
  </el-container>
</el-container>
</template>

<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  
  .el-aside {
    color: #333;
  }
</style>

<script>
  export default {
    data() {
      const item = {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      };
      return {
        tableData: Array(20).fill(item),
        menuDataList: null
      }
    },
    mounted: function(){
      

    },
    methods: {
      
    }
  };
</script>
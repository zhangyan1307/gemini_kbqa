<template>
<div class="CustomerKb">
  <el-container style="height: 700px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '3']" @select="select">
        <el-submenu index="1" style="background: rgb(24, 143, 255)">
          <template slot="title"
            ><i class="el-icon-message"></i>全部分类</template
          >
          <el-menu-item-group>
            <el-menu-item index="tool">tool</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item index="机器人技术问题">机器人技术问题</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item index="机器人商务问题">机器人商务问题</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item index="公司通用问题">公司通用问题</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item index="客服云">客服云</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item index="用户情绪">用户情绪</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group>
            <el-menu-item index="咨询">咨询</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-menu-item index="2">管理分类</el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <el-main>
        <el-button type="primary">新增知识规则</el-button>
        <el-dropdown placement="bottom" trigger="click" @command="batchOperate">
          <el-button type="primary">
            导入/导出<i class="el-icon-arrow-down el-icon--right"></i>
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="batch_remarks">导入</el-dropdown-item>
            <el-dropdown-item command="export_excel">导出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <el-button type="primary">操作日志</el-button>
        <el-dropdown placement="bottom" trigger="click" @command="batchOperate">
          <el-button type="primary">
            模板下载<i class="el-icon-arrow-down el-icon--right"></i>
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="batch_remarks">导入</el-dropdown-item>
            <el-dropdown-item command="export_excel">导出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-main>
    </el-container>
  </el-container>
  </div>
</template>

<style lang="scss" scoped>
.CustomerKb{
  width: 100%;
  height: 100%;
  .el-main{
    .el-dropdown{
      padding: 0 20px;
    }
  }
}
</style>

<script>
export default {
  data() {
    const item = {
      date: "2016-05-02",
      name: "王小虎",
      address: "上海市普陀区金沙江路 1518 弄",
    };
    return {
      tableData: Array(20).fill(item),
    };
  },
  mounted: function () {},
  methods: {
    batchOperate(command) {
      switch (command) {
        case "batch_remarks":
          this.dialogRemarks();
          break;
        case "export_excel":
          this.exportExcel();
          break;
      }
    },
    // 导出本页Excel表
    exportExcel() {
      console.log(111);
    },
    dialogRemarks() {
      console.log(222);
    },
    //打开的index
    select(index){
      console.log(index)
    }
  },
};
</script>
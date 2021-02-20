<template>
<div class="CustomerKb">
  <el-container style="height: 700px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1']">
        <el-submenu index="1" style="background: rgb(24, 143, 255)">
          <template slot="title"><i class="el-icon-message"></i>全部分类</template>
          <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.categoryName" @click="getDataList(item.categoryId, pageNum, pageSize)">{{ item.categoryName }}</el-menu-item>
        </el-submenu>
        <el-menu-item index="2" @click="dialogFormVisible = true">管理分类</el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <el-main v-if="navList != null || navList.length > 0">
        <el-button type="primary" @click="dialogUpload = true">添加文档</el-button>
        <el-table :data="articleList">
          <el-table-column prop="articleName" label="文章名" width="140" align="center">
        </el-table-column>
        <el-table-column prop="articleTitle" label="文章标题" width="120" align="center">
        </el-table-column>
        <el-table-column prop="articleType" label="上传类型" width="110" align="center">
        </el-table-column>
        <el-table-column prop="articleCategory" label="文章分类" width="180" align="center">
        </el-table-column>
        <el-table-column prop="articleDesc" label="文章描述" width="100" align="center">
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini">
            编辑
          </el-button>
          <el-button v-if="row.status!='deleted'" size="mini" type="danger" @click="deleteArticle(row.id)">
            删除
          </el-button>
        </template>
        </el-table-column>
        </el-table>
        
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[10, 20, 30, 50, 100]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="articleList.length">
      </el-pagination>
      </el-main>
    </el-container>

    <el-dialog title="新增分类" :visible.sync="dialogFormVisible">
      <el-form ref="form" :model="form">
        <el-form-item label="分类名称" :label-width="formLabelWidth" prop="categoryName">
          <el-input type="text" v-model="form.categoryName" autocomplete="off" maxlength="10"></el-input>
        </el-form-item>
        <el-form-item label="分类等级" :label-width="formLabelWidth" prop="level">
          <el-select v-model="form.level" placeholder="请选择分类等级">
            <el-option label="二级目录" value="2"></el-option>
          </el-select>
        </el-form-item>
        
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click='saveCustomerCategory("form")'>确 定</el-button>
      </div>
    </el-dialog>

     <el-dialog title="添加文档" :visible.sync="dialogUpload">
      <el-form :model="articleForm" ref="articleForm" :rules="articleRules">
        <el-form-item label="文档标题" :label-width="formLabelWidth" prop="articleTitle">
          <el-input v-model="articleForm.articleTitle" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="文档名称" :label-width="formLabelWidth" prop="articleName">
          <el-input v-model="articleForm.articleName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="文档描述" :label-width="formLabelWidth" prop="articleDesc">
          <el-input v-model="articleForm.articleDesc" autocomplete="off"></el-input>
        </el-form-item>
        <!-- <el-form-item label="文章分类" :label-width="formLabelWidth" prop="articleCategoryId">
          <el-select v-model="articleForm.articleCategoryId" placeholder="请选择分类">
            <el-option v-for="item in navList" :key="item.categoryId" :label="item.categoryName" :value="item.categoryId"></el-option>
          </el-select>
        </el-form-item> -->
      </el-form>
      <el-upload
          ref="articleUpload"
          class="upload-article"
          :auto-upload=false
          action="/api/customerKb/file/upload"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          :data="articleForm"
          multiple
          :limit="1"
          :on-exceed="handleExceed"
          :on-success="saveArticleSuccess"
          :file-list="fileList">
          <el-button type="primary">上传文章</el-button>
       </el-upload>

        
      
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogUpload = false">取 消</el-button>
        <el-button type="primary" @click='saveArticle("articleForm")'>确 定</el-button>
      </div>
    </el-dialog>

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
import {getCategoryList,saveCategory,getArticleByCategoryId} from '../../api/customerKb.js'
export default {
  data() {
    return {
      navList: null,
      dialogFormVisible: false,
      dialogUpload: false,
      fileList: [],
      selectCategoryId: 0,
      form: {
          categoryName: '',
          level: ''
      },
      articleForm: {
        articleTitle: '',
        articleName: '',
        articleCategoryId: '',
        articleDesc: ''
      },
      articleRules: {
        articleTitle: [
          { required: true, message: '请输入文档标题', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        articleName: [
          { required: true, message: '请输入文档名称', trigger: 'blur' }
        ],
        articleCategoryId: [
           { required: true, message: '请选择文档分类', trigger: 'change' }
        ]
      },
      formLabelWidth: '120px',
      articleList:[],
      pageNum:1,
      pageSize:10
    };
  },
  mounted: function () {
    this.getCategoryDataList(0);
  },
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
    saveArticle(){
      this.articleForm.articleCategoryId = this.selectCategoryId
      this.$refs.articleUpload.submit();
    },
    saveArticleSuccess(){
      this.$refs.articleUpload.clearFiles();
      this.$refs['articleForm'].resetFields();
      this.dialogUpload = false;
      this.getDataList(this.selectCategoryId, this.pageNum, this.pageSize)
    },
    saveCustomerCategory(form){
      let formData = this[form];
      console.log(formData);
      saveCategory(formData).then(res => {
         this.$message({
            message: '新增成功',
            type: 'success'
         })
         this.$refs['form'].resetFields()
         this.dialogFormVisible = false
         this.getCategoryDataList(0)
      })
    },
    
    deleteArticle(id){
      alert(id)
    },

    getDataList(categoryId, pageNum, pageSize){
      this.selectCategoryId = categoryId
      let data = {
        categoryId:categoryId,
        pageNum:pageNum,
        pageSize:pageSize
      }
      getArticleByCategoryId(data).then(res => {
        this.articleList = res.data.data
      }).catch(err => {
        this.$message({
            message: '获取文章列表失败',
            type: 'error'
         })
      })
    },
    getCategoryDataList(categoryId){
      getCategoryList(categoryId).then(res => {
        this.navList = res.data
      }).catch(err => {
          this.$message({
            type: 'error',
            message: '拉取分类列表失败'
          });   
      })
    },
    handleRemove(file, fileList) {
      console.log("移除文件操作");
    },
      handlePreview(file) {
        console.log(file);
      },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
      },
       // 每页多少条
      handleSizeChange(val) {
          this.pageSize = val;
      },
      // 当前页
      handleCurrentChange(val) {
          this.currentPage = val;
      }

  },
};
</script>
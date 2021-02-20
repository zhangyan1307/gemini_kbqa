<template>
<div class="CustomerKb">
  <el-container style="height: 700px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '3']">
        <el-submenu index="1" style="background: rgb(24, 143, 255)">
          <template slot="title">
            <i class="el-icon-message"></i>全部分类</template>
          <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.categoryName" 
            @click="getDataList(item.categoryId, pageNum, pageSize)">{{ item.categoryName }}</el-menu-item>
        </el-submenu>
        <el-menu-item index="2">管理分类</el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <el-main>
        <el-button type="primary" @click="dialogFormVisible = true">新增知识规则</el-button>
        
        <el-table :data="questionList">
           <el-table-column prop="question" label="问题" width="140" align="center">
          </el-table-column>
          <el-table-column prop="answer" label="答案" width="120" align="center">
          </el-table-column>
          <el-table-column prop="useCount" label="使用次数" width="110" align="center">
          </el-table-column>
          <el-table-column prop="keyWordCount" label="关键词个数" width="180" align="center">
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="{row,$index}">
              <el-button type="primary" size="mini">
                编辑
              </el-button>
              <el-button v-if="row.status!='deleted'" size="mini" type="danger" @click="deleteQuestion(row.id)">
                删除
              </el-button>
          </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
    <el-dialog title="新增知识规则" :visible.sync="dialogFormVisible">
      <el-form ref="addQuestionForm" :model="addQuestionForm">
        <el-form-item label="标准问题" :label-width="formLabelWidth" prop="question">
          <el-input type="text" v-model="addQuestionForm.question" autocomplete="off" style="width:500px"></el-input>
        </el-form-item>
        <el-form-item label="相似问题" :label-width="formLabelWidth" prop="similarityQestions[0]">
          <el-input type="text" v-model="addQuestionForm.similarityQestions[0]" autocomplete="off" style="width:500px"></el-input>
          <i class="el-icon-circle-plus-outline" style="font-size: 20px" @click="addSimiliartyQuestion()"></i>
        </el-form-item>
        <div v-for="(item, index) in addQuestionForm.similarityQestions" v-if="index >= 1" :key="index">
          <el-form-item :label-width="formLabelWidth">
            <el-input type="text" v-model="addQuestionForm.similarityQestions[index]" autocomplete="off" style="width:500px"></el-input>
            <i class="el-icon-remove-outline" style="font-size: 20px" @click="deleteSimiliartyQeustion(index, item)"></i>
          </el-form-item>
        </div>
        <el-form-item label="标准答案" :label-width="formLabelWidth"  prop="answer">
          <quill-editor
              class="ql-editor"
              ref="myTextEditor"
              style="width:500px;"
              :options="quillOption"
              v-model="addQuestionForm.answer"
            ></quill-editor>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click='saveQuestionAndAnswer("addQuestionForm")'>确 定</el-button>
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
import {getCategoryList} from '../../api/customerKb.js'
import { Quill, quillEditor } from 'vue-quill-editor'
import quillConfig from '../../components/common/quill-config'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
export default {
  data() {
    return {
      questionList: [],
      navList: [],
      addQuestionForm: {
        question: '',
        similarityQestions: [''],
        answer: '',
        relationQeustions: []
      },
      dialogFormVisible: false,
      formLabelWidth: '100px',
      editor: null, // 富文本编辑器对象
      editorOption: { //  富文本编辑器配置
        placeholder: '请输入',
        theme: 'snow', // or 'bubble'
        modules: {
          toolbar: {
            container: '#toolbar'
          }
        }
      },
      content: '<h2>hello quill-editor</h2>',
      quillOption: quillConfig
    };
  },
   mounted: function () {
    this.getCategoryDataList(1);
  },
  methods: {
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
    addSimiliartyQuestion(){
      this.addQuestionForm.similarityQestions.push('')
    },
    deleteSimiliartyQeustion(index, item){
      this.addQuestionForm.similarityQestions.splice(index, 1)
    },
    saveQuestionAndAnswer(addQuestionForm){
      
    }
  },
  components: {
    quillEditor
  }
};
</script>
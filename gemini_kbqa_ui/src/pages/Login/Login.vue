<template>
  <div class="Login">
    <div class="Login-Container">
      <h1>知识管理系统</h1>
      <el-form
        :model="ruleForm"
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="用户名:" prop="username">
          <el-input v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码:" prop="password">
          <el-input v-model="ruleForm.password"></el-input>
        </el-form-item>
        <el-form-item label="验证码:" prop="size"> </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')"
            >立即登入</el-button
          >
          <el-button type="danger" @click="resetForm('ruleForm')"
            >重置登入信息</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { setToken } from "@/utils/auth.js";
export default {
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (
            this.ruleForm.username == "admin" &&
            this.ruleForm.password == "admin"
          ) {
            this.$message({
              message: "恭喜你，登入成功",
              type: "success",
            });
            this.$router.push("/dashboard/analysis");
            setToken("123456");
          }else{
            this.$message({
              message: "非常遗憾，密码错误",
              type: "error",
            });  
          }
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
  data() {
    return {
      ruleForm: {
        name: "",
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        //size: [{ required: true, message: "请输入验证码", trigger: "blur" }],
      },
    };
  },
};
</script>

<style lang="scss" scoped>
.Login {
  width: 100%;
  height: 100%;
  background: url("http://kk.jzdcjt.com:8000/serverj/assets/css/img/bg_pc.jpg");
  background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  position: relative;
  height: 100%;
  overflow: hidden;
  font-size: 16px;
  display: flex;
  align-items: center;
  .Login-Container {
    width: 500px;
    height: 350px;
    background-color: white;
    border-radius: 20px;
    position: absolute;
    right: 100px;
    display: flex;
    flex-direction: column;
    padding: 0 50px 0 10px;
    h1 {
      text-align: center;
    }
  }
}
</style>
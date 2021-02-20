<template>
  <JwChat-index
    :taleList="list"
    @enter="bindEnter"
    v-model="inputMsg"
  />
</template>

<style lang="scss" scoped>

</style>
<script>
export default {
  data() {
    return {
     wsUrl:"ws://localhost:8005/robot/test",
     socket: "",
     inputMsg: '',
     list: [],
    };
  },
  mounted: function(){
    this.init()
  },
  methods: {
    init() {
      if(typeof(WebSocket) === "undefined"){
        this.$message({
            type: 'error',
            message: '您的浏览器不支持socket'
          })
      }else{
        debugger;
        //实例化socket
        this.socket = new WebSocket(this.wsUrl)
        this.socket.onopen = this.open
        this.socket.onerror = this.error
        this.socket.onmessage = this.getMessage
      }
    },
    open(){
      console.log("socket连接成功")
    },
    error(){
      console.log("连接错误")
    },
    getMessage(msg){
      console.log(msg.data)
    },
    send(params){
      this.socket.send(params)
    },
    close(){
      console.log("socket已经关闭")
    },
    bindEnter() {
      this.list.push({
        date: "2020/04/25 21:19:07",
        text: {"text": this.inputMsg},
        mine: true,
        name: "xxy"
      })
    }
  }
}
</script>
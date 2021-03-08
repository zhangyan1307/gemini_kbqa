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
     robotCardMessage: {
            title: '在接入人工前，智能助手将为您首次应答。',
            subtitle: '猜您想问:',
            content: []
          }
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
        //实例化socket
        this.socket = new WebSocket(this.wsUrl)
        this.socket.onopen = this.open
        this.socket.onerror = this.error
        this.socket.onmessage = this.getMessage
      }
      
    },
    open(){
      console.log("socket连接成功")
      this.send('{"openFlag":"true"}')
    },
    error(){
      console.log("连接错误")
    },
    getMessage(msg){
      console.log(msg.data)
      let data = JSON.parse(msg.data)
      if(data.messageType == 2){
        let messageData = JSON.parse(data.message)
        for (let index = 0; index < messageData.length; index++) {
          const element = messageData[index];
          this.robotCardMessage.content.push({
            id:element.questionId,
            text:element.question
          })
        }
        this.list.push({
          date: new Date(),
          text: {
            system:this.robotCardMessage
          },
          mine: false,
          name: data.messageSender
        })
      }
    },
    send(params){
      this.socket.send(params)
    },
    close(){
      console.log("socket已经关闭")
    },
    bindEnter() {
      if(this.inputMsg == null || this.inputMsg.length == 0){
        return
      }
      this.list.push({
        date: "2020/04/25 21:19:07",
        text: {"text": this.inputMsg},
        mine: true,
        name: "xxy"
      })
      let msgData = {
        message:this.inputMsg,
        messageType:1,
        messageSender:"test"
      }
      this.socket.send(JSON.stringify(msgData))
    }
  }
}
</script>
<template>
  <JwChat-index
    :config="config"
    :taleList="list"
    @enter="bindEnter"
    v-model="inputMsg"
    scrollType="scroll"
    @clickTalk="talkEvent"
    @input="inputListener"
  >
  </JwChat-index>
</template>

<style lang="scss" scoped>
 
</style>
<script>
import {getCustomerContentByCategoryId, getRecommendQuestions} from '../../api/RobotKb'
export default {
  data() {
    return {
     wsUrl:"ws://localhost:8005/robot/test",
     socket: "",
     inputMsg: '',
     list: [],
     recommandQuestionMap: new Map(),
     robotCardMessage: {
      title: '在接入人工前，智能助手将为您首次应答。',
      subtitle: '猜您想问:',
      content: [
        {
          id:1,
          text:"测试问题"
        }
      ]
     },
     rightConfig: {
        listTip: '当前在线',
        // notice: '【公告】这是一款高度自由的聊天组件，基于AVue、Vue、Element-ui开发。点个赞再走吧 ',
        list: [
          {
            name: 'JwChat',
            "img": "image/three.jpeg",
            id:1,
          },
           {
             id:2,
            name: 'JwChat',
            "img": "image/three.jpeg"
          },
          {
             id:3,
            name: 'JwChat',
            "img": "image/three.jpeg"
          },
          {
            id:4,
            name: '留恋人间不羡仙',
            "img": "image/one.jpeg"
          },
          {
            name: '只盼流星不盼雨',
            "img": "image/two.jpeg"
          }
        ]
      },
      quickConfig: {
        nav: ['快捷回复', '超级回复'],
        showAdd: true,
        maxlength: 200,
        showHeader: true,
        showDeleteBtn: true,
      },
      talk: [
        '快捷回复1',
        '快捷回复2',
        '快捷回复3',
        '快捷回复4',
        '快捷回复5',
        '快捷回复6',
      ],

     config: {
        img: '/image/cover.png',
        name: 'JwChat',
        dept: '最简单、最便捷',
        callback: this.bindCover,
        historyConfig:{
          show: true,
          tip: '加载更多',
          callback: this.bindLoadHistory,
        },
        quickList: [
          
        ]
      },
    }
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
      //this.send('{"openFlag":"true"}')
      //请求接口获取推荐问答
      this.getRecommendQuestions()
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
    bindEnter(value) {
      if(this.inputMsg == null || this.inputMsg.length == 0){
        return
      }
      this.list.push({
        date: "2020/04/25 21:19:07",
        text: {"text": this.inputMsg},
        mine: true,
        name: "xxy"
      })
      let answer = this.recommandQuestionMap.get(value)
      if(answer != null && answer.length > 0){
        this.list.push({
        date: "2020/04/25 21:19:07",
        text: {"text": answer},
        mine: false,
        name: "机器人小勇"
      })
      }
      let msgData = {
        message:this.inputMsg,
        messageType:1,
        messageSender:"test"
      }
      this.socket.send(JSON.stringify(msgData))
    },
     bindTalk (play) {
      console.log('talk', play)
    },
    toolEvent (type, plyload) {
      console.log('tools', type, plyload)
    },
    talkEvent(play){
      console.log(play.data.text)
    },
    getRecommendQuestions(){
      getRecommendQuestions().then(res => {
        debugger
        let messageData = res.data
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
          name: messageData.messageSender
        })

      })
    },
    inputListener(value){
      getCustomerContentByCategoryId(value).then(res => {
        if(res.data != null && res.data.length > 0){
          for (let index = 0; index < res.data.length; index++) {
            const element = res.data[index];
            console.log(element.question)
            this.config.quickList.push({
              text: element.question
            })
            this.recommandQuestionMap.set(element.question, element.answer)
          }
        }
        
      })
    }
  }
}
</script>
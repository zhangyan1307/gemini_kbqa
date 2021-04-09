package controller

import (
	"log"

	"github.com/gin-gonic/gin"
	"github.com/gorilla/websocket"
)

func init() {
	upgrader = websocket.Upgrader{
		ReadBufferSize:  1024,
		WriteBufferSize: 1024,
	}
}

//访客连接
//1、需要建立ws连接
//2、开始用户认证
func userConnect(c *gin.Context) {
	conn, err = upgrader.Upgrade(c.Writer, c.Request, nil)
	if err != nil {
		log.Print("Upgrade:", err)
		return
	}
	for {
		//接受消息
		var receive []byte
		var recevString string
		messageType, receive, err := conn.ReadMessage()
		if err != nil {
			log.Print("接受消息失败:", err)
			return
		}
		recevString = string(receive)

	}
}

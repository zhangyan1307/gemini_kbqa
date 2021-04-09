package bean

import "encoding/json"

type Message struct {
	Type interface{}     `json:"type"`
	Data json.RawMessage `json:"data"`
}

type HandShakeMessage struct {
	Name   string `json:"name"`
	UserId string `json:"uid"`
}

type NormalMessage struct {
	MessageId  string     `json:"messageId"`
	Content    string     `json:"conteng"`
	Cid        string     `json:"cid"`
	FromUser   User       `json:"fromUser"`
	ToUser     User       `json:"toUser"`
	ExtendData ExtendData `json:"extendData"`
}

type User struct {
	Uid      string `json:"uid"`
	UserName string `json:"userName"`
}

type ExtendData struct {
}

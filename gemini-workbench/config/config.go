package config

import (
	"encoding/json"
	"gemini-workbench/tools"
	"io/ioutil"
)

const MysqlConf = "../../gemini-workbench/config/mysql.json"

type Mysql struct {
	Server   string
	Port     string
	Database string
	Username string
	Password string
}

func CreateMysql() *Mysql {
	var mysql Mysql
	isExist, _ := tools.IsFileExist(MysqlConf)
	if !isExist {
		return &mysql
	}
	info, err := ioutil.ReadFile(MysqlConf)
	if err != nil {
		return &mysql
	}

	err = json.Unmarshal(info, &mysql)
	return &mysql
}

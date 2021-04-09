package test

import (
	"gemini-workbench/database"
	"testing"
)

func TestConn(t *testing.T) {
	err := database.NewMysql().Ping()
	if err != nil {

	}
}

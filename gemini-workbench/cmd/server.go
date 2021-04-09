package cmd

import (
	"fmt"

	"github.com/spf13/cobra"
)

var (
	Port   string
	daemon bool
)

var serverCmd = &cobra.Command{
	Use:     "server",
	Short:   "example:go-fly server -p 8081",
	Example: "go-fly server -c config/",
	Run: func(cmd *cobra.Command, args []string) {
		run()
	},
}

func init() {
	serverCmd.PersistentFlags().StringVarP(&Port, "port", "p", "8081", "监听端口号")
	serverCmd.PersistentFlags().BoolVarP(&daemon, "daemon", "d", false, "是否为守护进程模式")
}

func run() {
	fmt.Print("开始启动服务器")
}

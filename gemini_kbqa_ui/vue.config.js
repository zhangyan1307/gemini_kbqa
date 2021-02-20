const path = require('path');

module.exports = {
  publicPath: './',
  lintOnSave: false,
  devServer: {
    open: true,
    host: "localhost",
    port: '8048',
    https: false,
    proxy: {
      '/api': {
        target: 'http://localhost:8888', //API服务器地址
        changeOrigin: true, 
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  },
  configureWebpack: {
    resolve: {
      extensions: ['.js', '.json', '.vue'],
      alias: {
        '@': path.resolve(__dirname, 'src/'),
      },
    },
  },
};

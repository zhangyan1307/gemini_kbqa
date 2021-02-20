package com.gemini.admin.ws;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Author: XXY
 * @Date: 2021/2/19 19:36
 */
public class NettyServer {
    private final int port;

    public NettyServer(int port){
        this.port = port;
    }
    public void start() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.option(ChannelOption.SO_BACKLOG, 1024);
            sb.group(group, bossGroup).channel(NioServerSocketChannel.class)
                    .localAddress(this.port)//监听绑定端口
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            System.out.println("收到新连接");
                            //websocket本身是基于http协议的，所以这边也要用http解码器
                            socketChannel.pipeline().addLast(new HttpServerCodec());
                            //以块的方式来写的处理器
                            socketChannel.pipeline().addLast(new ChunkedWriteHandler());
                            socketChannel.pipeline().addLast(new HttpObjectAggregator(8192));
                            socketChannel.pipeline().addLast(new WebSocketServerProtocolHandler("/robot/test",
                                    null, true, 65536*10));
                            socketChannel.pipeline().addLast(new ChatWebSocketHandler());
                        }
                    });
            ChannelFuture cf = sb.bind().sync(); // 服务器异步创建绑定
            System.out.println(NettyServer.class + " 启动正在监听： " + cf.channel().localAddress());
            cf.channel().closeFuture().sync(); // 关闭服务器通道
        }finally {
            group.shutdownGracefully().sync();
            bossGroup.shutdownGracefully().sync();
        }
    }
}

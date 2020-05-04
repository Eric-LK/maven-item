package com.eric.hchat.netty_test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class WebSocketServer {
    public static void main(String[] args) {
        NioEventLoopGroup mainGroup = new NioEventLoopGroup();
        NioEventLoopGroup subGroup = new NioEventLoopGroup();

        try {
            // 创建服务器启动器
            ServerBootstrap b = new ServerBootstrap();
            // 指定使用主线程池和从线程池
            b.group(mainGroup,subGroup)
                    .channel(NioServerSocketChannel.class) // 指定使用nio通道类型
                    .childHandler(new WsServerInitializer());// 指定通道初始化器加载通道处理器
            // 绑定端口号启动服务器，并等待服务器启动
            // ChannelFuture 是 Netty 的回调消息
            ChannelFuture future = b.bind(9090).sync();
            // 等待服务器 socket 关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            // 优雅的关闭boos线程池和worker线程池
            mainGroup.shutdownGracefully();
            subGroup.shutdownGracefully();
        }


    }
}

package com.eric.hchat.netty_test;


import com.eric.hchat.netty.ChatHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WsServerInitializer extends ChannelInitializer<SocketChannel> {


    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        // websocket 基于http 协议，需要有http 的编解码器
        pipeline.addLast(new HttpServerCodec());

        // 对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());

        // 添加对 http 请求和响应的聚合器：只要使用Netty 进行http编程都需要使用
        // 对HttpMessage 进行聚合，聚合成FullHttpRequest 或者 FullHttpResponse
        // 在 netty 编程中都会使用到 Handler
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));


        // websocket 服务器处理的协议，用于指定给客户端链接访问的路由 ： /ws
        // 本 handler 会帮你主力一些握手动作： handshaking（ close，ping，pong）+ pong = 心跳
        // websocket 来讲，都是以frames 进行传输的。不同的数据类型对应的 frames 也不同
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        // 添加自定义的 handler
        pipeline.addLast(new ChatHandler());
    }
}

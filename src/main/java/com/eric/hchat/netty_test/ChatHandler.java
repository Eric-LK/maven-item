package com.eric.hchat.netty_test;

import io.netty.channel.Channel;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    AtomicInteger  a = new AtomicInteger();

    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        a.incrementAndGet();
        // 获取从客户端传输过来的消息
        String text = textWebSocketFrame.text();
        System.out.println("接受到的消息：" + text);

        // 将接收到消息发送到所有客户端
        for (Channel channel : clients){
            // 注意所有的 websocket 数据都应该以 TextWebSocketFrame 进行封装
            channel.writeAndFlush(new TextWebSocketFrame("[服务器接收到消息：]" + LocalDateTime.now() + ",消息为："+ text));

        }
    }

    /***
     * 当客户端链接服务端之后（打开链接）
     * 获取客户端的channel，并且放入到ChannelGroup中进行管理
     * @param ctx 链接
     * @throws Exception 异常
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
    }
}

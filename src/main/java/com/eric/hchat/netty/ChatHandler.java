package com.eric.hchat.netty;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * 处理消息的handler
 * TextWebSocketFrame: 在netty中，是用于为websocket专门处理文本的对象，frame是消息的载体
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        // 获取从客户端传输过来的消息
        String text = textWebSocketFrame.text();
        System.out.println("接受到的消息：" + text);

        Message message = JSON.parseObject(text,Message.class);

        switch (message.getType()){
            // 如果消息类型是 1 ，则建立 用户 和 通道 的关联
            case 0:
                String userId = message.getChatRecord().getUserid();
                UserChannelMap.put(userId,channelHandlerContext.channel());
                System.out.println("建立用户：" + userId + "与通道" + channelHandlerContext.channel().id() + "关联");
                UserChannelMap.print();
                break;
            //
        }


        // 将接收到消息发送到所有客户端
        /*for (Channel channel : clients){
            // 注意所有的 websocket 数据都应该以 TextWebSocketFrame 进行封装
            channel.writeAndFlush(new TextWebSocketFrame("[服务器接收到消息：]" + LocalDateTime.now() + ",消息为："+ text));

        }*/
    }

    /***
     * 当客户端链接服务端之后（打开链接）
     * 获取客户端的channel，并且放入到ChannelGroup中进行管理
     * @param ctx 链接
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        UserChannelMap.removeByChannelId(ctx.channel().id().asLongText());
        ctx.channel().close();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("关闭通道");
        UserChannelMap.removeByChannelId(ctx.channel().id().asLongText());
        UserChannelMap.print();
    }
}

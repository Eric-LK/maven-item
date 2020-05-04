package com.eric.hchat.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * 心跳检测机制
 */
public class HearBeatHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent)evt;

            if (idleStateEvent.state() == IdleState.READER_IDLE){
                System.out.println("读空闲事件触发");
            }
            else if(idleStateEvent.state() == IdleState.WRITER_IDLE){
                System.out.println("写空闲时间触发");
            }else if (idleStateEvent.state() == IdleState.ALL_IDLE){
                System.out.println("读写空闲触发");
                ctx.channel().close();
            }
        }
    }
}

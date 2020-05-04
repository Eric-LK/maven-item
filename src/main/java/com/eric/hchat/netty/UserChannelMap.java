package com.eric.hchat.netty;


import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class UserChannelMap {

    private static Map<String, Channel> userChannelMap;
    static {
        userChannelMap = new HashMap<String,Channel>();
    }

    /**
     * 添加用户id与channel的关联
     * @param userId 用户id
     * @param channel 链接
     */
    public static void put(String userId, Channel channel){
        userChannelMap.put(userId,channel);
    }

    /**
     * 移除用户id和channel的关联
     * @param userId 用户id
     */
    public static void remove(String userId){
        userChannelMap.remove(userId);
    }

    /**
     * 根据通道id移除用户与channel的关联
     * @param channelId 通道id
     */
    public static void removeByChannelId(String channelId){
        if (!StringUtils.isNoneBlank(channelId)){
            return;
        }
        for (String s:userChannelMap.keySet()){
            Channel channel = userChannelMap.get(s);
            if (channelId.equals(channel.id().asLongText())){
                System.out.println("客户端链接断开，取消用户" + s + "与通道" + channelId + "关联");
                userChannelMap.remove(s);
                break;
            }
        }
    }

    // 打印所有的用户与通道的关联数据
    public static void print() {
        for (String s : userChannelMap.keySet()) {
            System.out.println("用户id:" + s + " 通道:" + userChannelMap.get(s).id());
        }
    }
}

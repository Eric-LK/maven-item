package com.eric.hchat.netty;

import com.eric.hchat.pojo.TbChatRecord;
import lombok.Data;

@Data
public class Message {
    private Integer type; // 消息类型
    private TbChatRecord chatRecord; // 消息内容
    private Object ext; // 消息附件
}

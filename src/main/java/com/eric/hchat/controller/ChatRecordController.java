package com.eric.hchat.controller;

import com.eric.hchat.pojo.TbChatRecord;
import com.eric.hchat.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChatRecordController {

    @Autowired
    ChatRecordService chatRecordService;

    @RequestMapping("findUnreadByUserId")
    public List<TbChatRecord> findUnreadByUserId(String userId){
        try{
            return chatRecordService.findUnreadByUserId(userId);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<TbChatRecord>();
        }
    }
}

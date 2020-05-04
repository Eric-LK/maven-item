package com.eric.hchat.service;

import com.eric.hchat.pojo.TbChatRecord;

import java.util.List;

public interface ChatRecordService {

    List<TbChatRecord> findUnreadByUserId(String userId);
}

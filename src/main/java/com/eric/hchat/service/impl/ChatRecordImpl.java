package com.eric.hchat.service.impl;

import com.eric.hchat.pojo.TbChatRecord;
import com.eric.hchat.service.ChatRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ChatRecordImpl implements ChatRecordService {
    @Override
    public List<TbChatRecord> findUnreadByUserId(String userId) {

        return null;
    }
}

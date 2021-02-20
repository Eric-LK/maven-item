package com.eric.entity;

import lombok.Data;

/**
 * @author liuBing
 */
@Data
public class NimRecordVideoDto {
    /**
     * 表示G2的音视频文件存储信息，即汇报G2音视频文件的大小、下载地址等消息
     */
    public final static String EVENT_TYPE_RECORD_VIDEO = "22";
    /**
     * 消息类型
     */
    private String eventType;
    /**
     * 文件信息
     */
    private String fileinfo;
}

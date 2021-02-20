package com.eric.entity;

import lombok.Data;
import lombok.Getter;

/**
 * @author liuBing
 */
@Data
@Getter
public class Fileinfo {
    private Boolean caller; //是否是此通通话的发起者，若是则为true，若不是则没有此字段，可转为Boolean值
    private Long channelid; //通道号，可转为Long值
    private String filename; //文件名，直接存储，混合录制文件filename带有"-mix"标记
    private String md5;
    private Long size; // 文件大小
    private String type; // 文件的类型（扩展名），包括：实时音频录制文件(aac)、白板录制文件(gz)、实时视频录制文件(mp4)、互动直播视频录制文件(flv)
    private String url;
    private Long user; // 用户帐号，若该文件为混合录制文件，则该字段为"0"
    private Boolean mix; // 是否为混合录制文件，true：混合录制文件；false：单人录制文件
    private String vid; // 点播文件id，注意白板录制文件(gz)无此字段。通过该参数可以调用点播接口查询相关信息。
}

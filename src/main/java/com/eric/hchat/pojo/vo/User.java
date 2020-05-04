package com.eric.hchat.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String username;
    private String picSmall;
    private String picNormal;
    private String nickname;
    private String qrcode;
    private String clientId;
    private String sign;
    private Date createtime;
    private String phone;
}

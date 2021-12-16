package com.eric.param;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description：
 * @Author: liuBing
 * @DateTime: 2021/12/10 15:09
 */
@Data
public class FileUploadParam {
    private String id;
    private MultipartFile file;
}

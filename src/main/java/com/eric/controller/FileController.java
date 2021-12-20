package com.eric.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * @description： 文件上传controller
 * @Author: liuBing
 * @DateTime: 2021/12/10 14:37
 */
@RestController
@Api
@RequestMapping("file")
@Slf4j
public class FileController {

    @Autowired
    private HttpServletRequest request;

    /**
     * 文件上传
     * <p>
     * 参考博客
     * https://blog.csdn.net/Xidian2850/article/details/105909335
     *
     * @param file 文件
     * @return 上传结果
     * @throws IOException io异常
     */
    @ApiOperation("上传文件")
    @PostMapping("upload")
    public String upload(MultipartFile file) throws IOException {

        log.info("-------------------- content type :{} ", request.getContentType());

        // MultipartFile file = param.getFile();

        // 文件名 + 扩展名
        log.info("-------------------- original file name : {}", file.getOriginalFilename());

        // 文件路径
        String filePath = "D:\\test\\fileTest\\" + System.currentTimeMillis() + "\\" + file.getOriginalFilename();

        File fileNew = new File(filePath);

        if (!fileNew.isDirectory() && !fileNew.mkdirs()) {
            return "error";
        }

        file.transferTo(fileNew);

        // 获取文件大小
        getFileSize();

        return "success";
    }

    /**
     * 获取文件大小
     */
    public void getFileSize() throws IOException {
        String filePath = "";

        File file = new File(filePath);

        System.out.println(file.length());

        // available 返回 int ，最大 21亿
        FileInputStream fileInputStream = new FileInputStream(file);
        System.out.println(fileInputStream.available());
    }
}

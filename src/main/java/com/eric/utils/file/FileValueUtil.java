package com.eric.utils.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * @description：
 * @Author: liuBing
 * @DateTime: 2022/5/5 15:13
 */
@Slf4j
public class FileValueUtil {

    /**
     * 从指定路径下获取文件的字符串并转成 json
     *
     * @param filePath 文件路径
     * @return json
     */
    public static String readJsonFile(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return "";
        }

        try (Reader reader = new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8)) {
            StringBuilder sb = new StringBuilder();
            int value;
            while ((value = reader.read()) != -1) {
                sb.append((char) value);
            }

            log.info("读取 {} 文件结束!————", filePath);
            return sb.toString();
        } catch (Exception e) {
            log.error("读取 {} 文件出现异常，读取失败!————", filePath, e);
            return null;
        }
    }
}

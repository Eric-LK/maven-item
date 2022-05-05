package com.eric.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;

import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import java.util.Map;

/**
 * @description：
 * @Author: liuBing
 * @DateTime: 2022/5/5 14:48
 */
public class HTHttpUtil {

    @AllArgsConstructor
    @Getter
    public enum HttpEnum {
        SIMPLE("");

        private final String desc;
    }

    private final static Logger log = LoggerFactory.getLogger("HttpLog");

    /**
     * 默认超时时间 100000毫秒（100秒）
     */
    private static final Integer DEFAULT_TIME_OUT = 100000;


    /**
     * 发送 Http 请求，做日志记录
     *
     * @param httpEnum {@link HttpEnum}
     * @param method   {@link HttpMethod}
     * @param url      请求路径
     * @param header   请求头
     * @param form     请求表单
     * @param body     请求体
     * @param timeOut  超时时间,单位毫秒。若入参小于等于0，取默认超时时间
     * @return 请求结果
     */
    private static String doHttp(HttpEnum httpEnum, HttpMethod method, String url, Map<String, String> header, Map<String, Object> form, Object body, int timeOut) {
        String desc = httpEnum.getDesc();
        String bodyStr = JSONUtil.toJsonStr(body);
        log.info("[{}]请求路径: {}", desc, url);
        log.info("[{}]请求协议: {}", desc, method);
        if (MapUtil.isNotEmpty(header)) {
            log.info("[{}]请求头: {}", desc, JSONUtil.toJsonStr(header));
        }
        if (MapUtil.isNotEmpty(form)) {
            log.info("[{}]请求表单: {}", desc, JSONUtil.toJsonStr(form));
        }
        if (StrUtil.isNotBlank(bodyStr)) {
            log.info("[{}]请求体: {}", desc, bodyStr);
        }


        TimeInterval timer = DateUtil.timer();
        String result = "";
        // 设置请求超时时间
        timeOut = timeOut > 0 ? timeOut : DEFAULT_TIME_OUT;

        if (method.equals(HttpMethod.POST)) {
            if (MapUtil.isNotEmpty(form)) {
                result = HttpUtil.createPost(url)
                        .addHeaders(header)
                        .form(form)
                        .timeout(timeOut)
                        .execute()
                        .body();
            } else {
                result = HttpUtil.createPost(url)
                        .addHeaders(header)
                        .body(bodyStr)
                        .timeout(timeOut)
                        .execute()
                        .body();
            }
        } else if (method.equals(HttpMethod.GET)) {
            result = HttpUtil.createGet(url)
                    .addHeaders(header)
                    .form(form)
                    .timeout(timeOut)
                    .execute()
                    .body();
        }

        log.info("[{}]请求结果: {}", desc, result);
        log.info("[{}]请求耗时: {}", desc, timer.interval() + "毫秒");

        return result;
    }

    public static String doGet(HttpEnum httpEnum, String url) {
        return doHttp(httpEnum, HttpMethod.GET, url, null, null, null, -1);
    }

    public static String doGet(HttpEnum httpEnum, String url, Map<String, Object> form) {
        return doHttp(httpEnum, HttpMethod.GET, url, null, form, null, -1);
    }

    public static String doGet(HttpEnum httpEnum, String url, Map<String, String> header, Map<String, Object> form) {
        return doHttp(httpEnum, HttpMethod.GET, url, header, form, null, -1);
    }

    public static String doGet(HttpEnum httpEnum, String url, Map<String, String> header, Map<String, Object> form, int timeOut) {
        return doHttp(httpEnum, HttpMethod.GET, url, header, form, null, timeOut);
    }

    public static <T> T doGet(HttpEnum httpEnum, String url, Class<T> clazz) {
        String result = doHttp(httpEnum, HttpMethod.GET, url, null, null, null, -1);
        return JSONUtil.toBean(result, clazz);
    }

    public static <T> T doGet(HttpEnum httpEnum, String url, Map<String, Object> form, Class<T> clazz) {
        String result = doHttp(httpEnum, HttpMethod.GET, url, null, form, null, -1);
        return JSONUtil.toBean(result, clazz);
    }

    public static <T> T doGet(HttpEnum httpEnum, String url, Map<String, String> header, Map<String, Object> form, Class<T> clazz) {
        String result = doHttp(httpEnum, HttpMethod.GET, url, header, form, null, -1);
        return JSONUtil.toBean(result, clazz);
    }

    public static String doPost(HttpEnum httpEnum, String url, Map<String, String> header, Object body) {
        return doHttp(httpEnum, HttpMethod.POST, url, header, null, body, -1);
    }

    public static String doPost(HttpEnum httpEnum, String url, Map<String, Object> form) {
        return doHttp(httpEnum, HttpMethod.POST, url, null, form, null, -1);
    }

    public static String doPost(HttpEnum httpEnum, String url, Map<String, String> header, Map<String, Object> form, Object body) {
        return doHttp(httpEnum, HttpMethod.POST, url, header, form, body, -1);
    }

    public static <T> T doPost(HttpEnum httpEnum, String url, Map<String, String> header, Object body, Class<T> clazz) {
        String result = doHttp(httpEnum, HttpMethod.POST, url, header, null, body, -1);
        return JSONUtil.toBean(result, clazz);
    }

    public static <T> T doPost(HttpEnum httpEnum, String url, Map<String, Object> form, Class<T> clazz) {
        String result = doHttp(httpEnum, HttpMethod.POST, url, null, form, null, -1);
        return JSONUtil.toBean(result, clazz);
    }

    public static <T> T doPost(HttpEnum httpEnum, String url, Map<String, String> header, Map<String, Object> form, Object body, Class<T> clazz) {
        String result = doHttp(httpEnum, HttpMethod.POST, url, header, form, body, -1);
        return JSONUtil.toBean(result, clazz);
    }

    public static <T> T doPost(HttpEnum httpEnum, String url, Map<String, String> header, Map<String, Object> form, Object body, Class<T> clazz, int timeOut) {
        String result = doHttp(httpEnum, HttpMethod.POST, url, header, form, body, timeOut);
        return JSONUtil.toBean(result, clazz);
    }
}

package com.eric.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * @author quinn
 * @version 创建时间：2018/10/18 11:21
 */
public class JSONUtils {
    protected static final Logger logger = LoggerFactory.getLogger(JSONUtils.class);

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // 将类名称序列化到json串中
        // mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    }

    private JSONUtils() {
    }

    /**
     * 获取 对象映射对象
     *
     * @return
     */
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }


    public static String toJSONString(Object object) {
        return toJSON(object, false);
    }

    public static String toJSONPrettyString(Object object) {
        return toJSON(object, true);
    }

    private static String toJSON(Object object, boolean pretty) {
        try {
            ObjectMapper mapper = getObjectMapper();

            if (pretty) {
                mapper.writerWithDefaultPrettyPrinter();
            }

            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            String strObj = object == null ? "null" : object.toString();
            logger.error("to json 异常:" + strObj, e);
        }

        return null;
    }

    public static <T> T toObject(String jsonString, Class<T> cls) throws Exception {
        T t = null;
        try {
            if (isGoodJson(jsonString)) {
                Gson gson = new Gson();
                t = gson.fromJson(jsonString, cls);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return t;
    }

    public static boolean isGoodJson(String jsonStr) {
        if (StringUtils.isEmpty(jsonStr)) {
            return false;
        }
        try {
            new JsonParser().parse(jsonStr);
        } catch (JsonParseException e) {
            return false;
        }
        return true;
    }

    public static List<Map<String, Object>> toMapList(String jsonStr) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }

        JSONArray jsonArr = JSON.parseArray(jsonStr);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Iterator<Object> it = jsonArr.iterator();
        while (it.hasNext()) {
            list.add(toMap(it.next().toString()));
        }
        return list;
    }

    public static Map<String, Object> toMap(String jsonStr) {
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        return (Map<String, Object>) JSON.parse(jsonStr);
    }

    /**
     * 暴力解析:Alibaba fastjson
     *
     * @param test
     * @return
     */
    public final static boolean isJSONValid(String test) {
        try {
            JSONObject.parseObject(test);
        } catch (JSONException ex) {
            try {
                JSONObject.parseArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}

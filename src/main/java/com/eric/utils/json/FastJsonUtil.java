package com.eric.utils.json;

import com.alibaba.fastjson.*;

import java.util.List;
import java.util.Map;


/**
 * @author lk
 * @version 创建时间：2018/10/18 11:21
 */
public class FastJsonUtil {

    /**
     *  1、字段为 boolean 类型的时候，如果反序列化的时候，这个字段没有，默认是 false
     *  2、字段为 int 类型的时候，则会为0
     *  就是 基本数据类型，会直接是默认值
     *
     *
     *  它的优势：快，比Jackson快20%左右，Google的Gson是最慢的
     *  它的bug：
     *      1、处理时间的时候，格式会有问题
     *      2、复杂字符串，它识别不了
     */



    private FastJsonUtil(){}

    public static Map<Object, Object> beanToMap(Object bean) {
        return bean == null ? null : JSON.parseObject(JSON.toJSONString(bean), new TypeReference<>() {});
    }


    public static JSONArray listToListJson(List<Object> list){
        return JSONObject.parseArray(JSON.toJSONString(list));
    }

}

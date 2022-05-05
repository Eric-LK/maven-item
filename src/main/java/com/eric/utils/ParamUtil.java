package com.eric.utils;

import com.eric.annotation.FieldCanEmpty;
import com.eric.exceptions.MyException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * @description： 参数处理工具类
 * @Author: liuBing
 * @DateTime: 2021/9/28 17:59
 */
@Slf4j
public class ParamUtil {

    private ParamUtil() {
    }

    /**
     * 返回结果日志打印
     *
     * @param result 返回结果
     */
    public static String paramEmptyResultHandle(String result) {
        if (StringUtils.isEmpty(result)) {
            return "";
        }
        return result.endsWith(",") ? result.substring(0, result.length() - 1) : result;
    }

    /**
     * 实体参数为空校验
     *
     * @param param     参数实体
     * @return 为空的属性名
     */
    @SneakyThrows
    public static String fieldStringParamCheck(Object param) {
        if (param == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Field[] fields = param.getClass().getDeclaredFields();

        fieldFor:
        for (Field field : fields) {
            field.setAccessible(true);

            // 带注解的可以为空
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof FieldCanEmpty) {
                    continue fieldFor;
                }
            }

            if (field.get(param) instanceof String) {
                String fieldValue = (String) field.get(param);
                /*
                1、属性为空
                2、为空属性字符串不包含这个属性名
                 */
                if (StringUtils.isEmpty(fieldValue) && !stringBuilder.toString().contains(field.getName())) {
                    stringBuilder.append(field.getName()).append(",");
                }
            } else if (field.get(param) instanceof List) {
                List fieldList = (List) field.get(param);
                if (CollectionUtils.isEmpty(fieldList) && !stringBuilder.toString().contains(field.getName())) {
                    stringBuilder.append(field.getName()).append(",");
                }
            } else {
                if (field.get(param) == null && !stringBuilder.toString().contains(field.getName())) {
                    stringBuilder.append(field.getName()).append(",");
                }
            }
        }
        return stringBuilder.toString();
    }


    /**
     * 实体参数为空校验（直接抛出异常的版本）
     *
     * @param param 参数实体
     */
    public static void paramCheckException(Object param) {
        String checkResult = fieldStringParamCheck(param);
        if (!StringUtils.isEmpty(checkResult)) {
            throw MyException.getParamEmptyException(checkResult);
        }
    }

    /**
     * 判断list是否包含指定字段之外的元素
     *
     * @param sourceList   源list
     * @param sourceString 可以包含的元素
     * @param paramName    list对应的参数名
     */
    public static void listHaveOther(List<String> sourceList, String sourceString, String paramName) {

        if (CollectionUtils.isEmpty(sourceList)
                || StringUtils.isEmpty(sourceList)
                || StringUtils.isEmpty(paramName)
                || !sourceList.contains(sourceString)
                || sourceList.size() > 1) {
            throw MyException.getParamErrorException(paramName);
        }
    }

    /**
     * 判断 list 是否包含指定 字段list 之外的元素
     *
     * @param sourceList       源list
     * @param sourceStringList 可以包含的元素list
     * @param paramName        list对应的参数名
     */
    public static void listHaveOther(List<String> sourceList, List<String> sourceStringList, String paramName) {
        if (CollectionUtils.isEmpty(sourceList) || StringUtils.isEmpty(sourceList) || StringUtils.isEmpty(paramName)) {
            return;
        }
        List<String> tempList = new ArrayList<>(sourceList);
        tempList.removeAll(sourceStringList);
        if (!CollectionUtils.isEmpty(tempList)) {
            throw MyException.getParamErrorException(paramName);
        }
    }
}

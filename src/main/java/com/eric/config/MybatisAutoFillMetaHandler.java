package com.eric.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import com.eric.utils.DateFormatUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @description： mybatis plus 自动拦截创建和更新时间
 * @Author: liuBing
 * @DateTime: 2021/11/29 17:39
 */
@Component
public class MybatisAutoFillMetaHandler implements MetaObjectHandler {


    static String CREATE_TIME_FIELD = "createTime";
    static String UPDATE_TIME_FIELD = "updateTime";


    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter(CREATE_TIME_FIELD)) {
            this.strictInsertFill(metaObject, CREATE_TIME_FIELD, String.class, DateFormatUtil.getNowTime());
        }
        injectUpdateTimeField(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        injectUpdateTimeField(metaObject);
    }

    private void injectUpdateTimeField(MetaObject metaObject) {
        if (metaObject.hasSetter(UPDATE_TIME_FIELD)) {
            this.strictUpdateFill(metaObject, UPDATE_TIME_FIELD, String.class, DateFormatUtil.getNowTime());
        }
    }
}

package com.eric.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.eric.utils.DateUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lk
 */
@Configuration
@MapperScan(basePackages = "com.eric.dao")
public class MybatisAutoConfiguration implements MetaObjectHandler {


    static String CREATE_TIME_FIELD = "createTime";
    static String UPDATE_TIME_FIELD = "updateTime";


    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter(CREATE_TIME_FIELD)) {
            this.strictInsertFill(metaObject, CREATE_TIME_FIELD, String.class, DateUtil.getNowTime());
        }
        injectUpdateTimeField(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        injectUpdateTimeField(metaObject);
    }

    private void injectUpdateTimeField(MetaObject metaObject) {
        if (metaObject.hasSetter(UPDATE_TIME_FIELD)) {
            this.strictUpdateFill(metaObject, UPDATE_TIME_FIELD, String.class, DateUtil.getNowTime());
        }
    }
}
package com.huzijun.yizunetwork.common.mybatis;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by navi on 2017/6/9.
 */
public class MyMetaObjectHandler extends MetaObjectHandler {

    private final static Logger logger = LoggerFactory.getLogger(MyMetaObjectHandler.class);



    @Override
    public void insertFill(MetaObject metaObject) {



        try {
            Object createBy = getFieldValByName("createBy", metaObject);
//            if (null == createBy) {
//                if (UserUtils.getCurrentUser() != null) {
//                    setFieldValByName("createBy", UserUtils.getCurrentUser().getId(), metaObject);
//                }
//            }
        } catch (Exception e) {
//            logger.error("自动更新createBy字段异常", e);
        }
        try {
            Object createTime = getFieldValByName("createTime", metaObject);
            if (null == createTime) {
                setFieldValByName("createTime", new Date(), metaObject);
            }
        } catch (Exception e) {
//            logger.error("自动更新createTime字段异常", e);
            //表中没有updateBy字段，不填充，catch异常
        }
        try {
            Object delFlag = getFieldValByName("delFlag", metaObject);
            if (null == delFlag) {
                setFieldValByName("delFlag", 0, metaObject);
            }
        } catch (Exception e) {
//            logger.error("自动更新createTime字段异常", e);
            //表中没有updateBy字段，不填充，catch异常
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            Object updateBy = getFieldValByName("updateBy", metaObject);

            //setFieldValByName("updateBy", UserUtils.getCurrentUser().getId(), metaObject);
        } catch (Exception e) {
//            logger.error("自动更新updateBy字段异常", e);
            //表中没有updateBy字段，不填充，catch异常
        }

        try {
            Object updateTime = getFieldValByName("updateTime", metaObject);

            setFieldValByName("updateTime", new Date(), metaObject);
        } catch (ReflectionException e) {
//            logger.error("自动更新updateTime字段异常", e);
            //表中没有updateBy字段，不填充，catch异常
        }
    }
}

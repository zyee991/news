package com.itcuc.common.utils;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * BeanUtils
 * 提供bean转map和map转bean方法
 * @author zy
 */
@Slf4j
public class BeanUtils {
    public static Map<String,?> bean2Map(Object object) {
        Map<String,Object> map = Maps.newHashMap();
        if(object == null){
            return null;
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!"class".equals(key)) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();

                    map.put(key, getter.invoke(object));
                }

            }
        } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
            log.error("错误",e);
        }
        return map;
    }

    public static <T> T map2Bean(Map<String,?> map,Class<T> c) {
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(c);
        } catch (IntrospectionException e) {
            log.error("获取BeanInfo错误",e);
        }
        T t = null;
        try {
            t = c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("获取实例错误",e);
        }
        PropertyDescriptor[] propertyDescriptors = beanInfo != null ? beanInfo.getPropertyDescriptors() : new PropertyDescriptor[0];
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            String propertyName = descriptor.getName();
            if (map.containsKey(propertyName)) {
                Object value = map.get(propertyName);
                Object[] args = new Object[1];
                args[0] = value;
                //这里捕获异常为了让不正常的值可以暂时跳过不影响正常字段的赋值
                try {
                    descriptor.getWriteMethod().invoke(t, args);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    log.error("异常参数{},{}",t,args);
                    log.error("调用方法错误", e);
                }
            }
        }
        return t;
    }
}

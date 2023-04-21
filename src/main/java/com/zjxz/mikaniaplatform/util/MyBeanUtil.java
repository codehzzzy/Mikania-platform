package com.zjxz.mikaniaplatform.util;

import org.springframework.beans.FatalBeanException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

import static org.springframework.beans.BeanUtils.getPropertyDescriptors;

/**
 * @author hzzzzzy
 * @date 2023/4/21
 * @description Bean工具类,以兼容Java17的Record类型
 */
public class MyBeanUtil {

    public static <T> T copyProperties(Object source, Class<T> target) {
        try {
            if (target.isRecord()) {
                var constructor = target.getDeclaredConstructors()[0];
                if (!Modifier.isPublic(constructor.getModifiers())) {
                    constructor.setAccessible(true);
                }
                Object res = constructor.newInstance(new Object[constructor.getParameterCount()]);
                return (T) copyPropertiesToRecord(source, (Record) res);
            } else {
                var targetDeclaredConstructors = target.getDeclaredConstructors();
                for (var constructor : targetDeclaredConstructors) {
                    if (constructor.getParameterCount() == 0) {
                        T res = (T) constructor.newInstance();
                        org.springframework.beans.BeanUtils.copyProperties(source, res);
                        return res;
                    }
                }
                return null;
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Record> T copyProperties(Object source, T target) {
        return copyPropertiesToRecord(source, target);
    }


    /**
     * 从普通类型的对象中拷贝属性值到Record类型的对象中
     *
     * @param source 普通对象
     * @param target Record类型的对象
     * @return 拷贝属性之后的新建实例的对象
     */
    public static <T extends Record> T copyPropertiesToRecord(Object source, T target) {
        // 先保存普通实体类的键值对
        var valuesMap = new HashMap<String, Object>(16);

        var targetClazz = target.getClass();
        var targetPds = getPropertyDescriptors(targetClazz);
        for (PropertyDescriptor targetPd : targetPds) {
            var readMethod = targetPd.getReadMethod();
            try {
                var value = readMethod.invoke(target);
                valuesMap.put(targetPd.getName(), value);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new FatalBeanException("can not read value.", e);
            }
        }
        var sourceClazz = source.getClass();
        var sourcePds = getPropertyDescriptors(sourceClazz);
        for (PropertyDescriptor sourcePd : sourcePds) {
            var readMethod = sourcePd.getReadMethod();
            try {
                var value = readMethod.invoke(source);
                if (value != null) {
                    valuesMap.put(sourcePd.getName(), value);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new FatalBeanException("can not read value.", e);
            }
        }

        // 检索记录类构造函数的参数列表
        var declaredConstructors = targetClazz.getDeclaredConstructors();
        int max = 0;
        int index = 0;
        for (int i = 0; i < declaredConstructors.length; i++) {
            if (declaredConstructors[i].getParameterCount() > max) {
                max = declaredConstructors[i].getParameterCount();
                index = i;
            }
        }
        var constructor = declaredConstructors[index];
        var params = new ArrayList<>();
        var parameterCount = constructor.getParameterCount();
        var parameters = constructor.getParameters();
        var parameterTypes = constructor.getParameterTypes();
        for (int i = 0; i < parameterCount; i++) {
            var parameter = parameters[i];
            params.add(valuesMap.get(parameter.getName()));
        }
        try {
            if (!Modifier.isPublic(constructor.getModifiers())) {
                constructor.setAccessible(true);
            }
            return (T) constructor.newInstance(params.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}

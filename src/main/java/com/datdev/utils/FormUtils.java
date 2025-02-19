package com.datdev.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class FormUtils {
    @SuppressWarnings("unchecked")
    public static <T> T toModel(Class<T> tClass, HttpServletRequest request) {
        T object = null;
        try {
            object = tClass.newInstance();
            BeanUtils.populate(object,request.getParameterMap());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }

}

package com.funtoys.service.interceptor;

import com.funtoys.service.annotation.Domain;
import com.funtoys.service.annotation.Nofixed;
import org.apache.commons.beanutils.MethodUtils;
import org.aspectj.lang.JoinPoint;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * Created by hejun on 2017/6/27.
 */
public class UpdateInterceptor {

    public void before(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            String createName = "admin";
            Class cla = arg.getClass();
            if (cla.isAnnotationPresent(Domain.class)) {
                try {
                    if (!cla.isAnnotationPresent(Nofixed.class)) {
                        if (MethodUtils.invokeMethod(arg, "getGmtModified", null) == null) {
                            MethodUtils.invokeMethod(arg, "setGmtModified", new Date());
                        }
                        if (MethodUtils.invokeMethod(arg, "getModifiedBy", null) == null) {
                            MethodUtils.invokeMethod(arg, "setModifiedBy", createName);
                        }
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

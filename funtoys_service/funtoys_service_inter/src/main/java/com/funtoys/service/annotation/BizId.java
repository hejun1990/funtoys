package com.funtoys.service.annotation;

import java.lang.annotation.*;

/**
 * Created by hejun on 2017/6/26.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BizId {
}

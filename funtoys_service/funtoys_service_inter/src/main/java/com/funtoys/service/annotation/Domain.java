package com.funtoys.service.annotation;

import java.lang.annotation.*;

/**
 * Created by hejun on 2017/6/26.
 *
 * 自动注入创建时间，修改时间，创建人，修改人。
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Domain {
}

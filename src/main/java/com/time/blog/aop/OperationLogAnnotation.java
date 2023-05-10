package com.time.blog.aop;

import java.lang.annotation.*;

/**
 * @author mjw
 * @date 2023/5/10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLogAnnotation {

    //操作模块
    String operModelCode() default "";

    //操作类型
    String operType() default "";

    //操作说明
    String operDesc() default "";
}

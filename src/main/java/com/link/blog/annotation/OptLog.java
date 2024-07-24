package com.link.blog.annotation;

import java.lang.annotation.*;

/**
 * @author LiuWenqi
 * @Description 操作日志注解
 * @date 2024-07-22 22:07
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptLog {

    /**
     * @return 操作类型
     */
    String optType() default "";

}

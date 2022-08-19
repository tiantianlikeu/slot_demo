package com.icode.common.annos;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 卡槽注解，以便扫描到处理器
 *
 * @Author: tiantianlikeU。
 * @Date: 2022/8/17 10:43
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Slot {

    /**
     * 顺序
     *
     * @return
     */
    int order() default 0;
}

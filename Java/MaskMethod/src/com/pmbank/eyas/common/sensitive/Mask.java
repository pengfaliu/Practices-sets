/**
 * Created by lfp on 2020/5/13.
 */

package com.pmbank.eyas.common.sensitive;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Mask {
    /**
     * 脱敏类型
     *
     * @return
     */
    MaskType value() default MaskType.DEFAULT;

    /**
     * 字符串左边保留
     *
     * @return
     */
    int leftKeep() default 0;

    /**
     * 字符串右边保留
     *
     * @return
     */
    int rightKeep() default 0;

    /**
     * 遮罩字符
     *
     * @return
     */
    char maskChar() default '*';
}


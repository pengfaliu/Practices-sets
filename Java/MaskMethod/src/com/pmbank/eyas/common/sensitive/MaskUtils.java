/**
 * Created by lfp on 2020/5/13.
 */

package com.pmbank.eyas.common.sensitive;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;



public class MaskUtils {
    private final static Logger logger = LoggerFactory.getLogger(MaskUtils.class);
    private final static MaskJsonValueFilter MASK_FILTER = new MaskJsonValueFilter();
    private final static ToStringStyle STRING_STYLE = new MaskStringValueBuilder.MaskRecursiveToStringStyle();

    /**
     * Map脱敏
     *
     * @param map 原始MAP
     * @return 脱敏MAP
     */
    public static Map<String, ?> toMask(Map<String, ?> map) {
        if (logger.isDebugEnabled() || logger.isTraceEnabled()) {
            return map;
        }

        return MaskRule.toMask(map);
    }

    /**
     * 输出对象脱敏字符串
     *
     * @param object 原始对象
     * @return 脱敏字符串
     */
    public static String toString(Object object) {
  /*
   * 兼容升级commons-lang到3.7之后脱敏空对象异常的问题
   */
        if (null == object) {
            return "<null>";
        }

        if (logger.isDebugEnabled() || logger.isTraceEnabled()) {
            return ReflectionToStringBuilder.toString(object, STRING_STYLE);
        }

        return MaskStringValueBuilder.toString(object);
    }

    /**
     * 输出对象脱敏JSON字符串
     *
     * @param object 原始对象
     * @return 脱敏JSON字符串
     */
    public static String toJson(Object object) {
        if (logger.isDebugEnabled() || logger.isTraceEnabled()) {
            return JSON.toJSONString(object);
        }
        return JSON.toJSONString(object, MASK_FILTER);
    }

    /**
     * 姓名脱敏
     *
     * @param value 原始字符串
     * @return 脱敏串
     */
    public static String maskName(String value) {
        return maskString(value, MaskRule.CHN_NAME);
    }

    /**
     * 身份证脱敏
     *
     * @param value 原始字符串
     * @return 脱敏串
     */
    public static String maskIdCard(String value) {
        return maskString(value, MaskRule.ID_CARD);
    }

    /**
     * 手机号脱敏
     *
     * @param value 原始字符串
     * @return 脱敏串
     */
    public static String maskMobile(String value) {
        return maskString(value, MaskRule.PHONE_MOBILE);
    }

    /**
     * 银行卡脱敏
     *
     * @param value 原始字符串
     * @return 脱敏串
     */
    public static String maskBankCard(String value) {
        return maskString(value, MaskRule.BANK_CARD);
    }

    /**
     * 字符串脱敏
     *
     * @param value    原始字符串
     * @param maskRule 脱敏规则
     * @return 脱敏串
     */
    public static String maskString(String value, MaskRule maskRule) {
        if (logger.isDebugEnabled() || logger.isTraceEnabled()) {
            return value;
        }

        return maskRule.toMask(value);
    }

    /**
     * 字符串脱敏
     *
     * @param value    原始字符串
     * @param maskType 脱敏类型
     * @return 脱敏串
     * @deprecated 请使用 maskString(String value, MaskRule maskRule)
     */
    public static String maskString(String value, MaskType maskType) {
        return maskString(value, maskType.getRule());
    }

    /**
     * 字符串脱敏
     *
     * @param value     原始字符串
     * @param leftKeep  左保留
     * @param rightKeep 右保留
     * @return 脱敏串
     */
    public static String maskString(String value, int leftKeep, int rightKeep) {
        if (logger.isDebugEnabled() || logger.isTraceEnabled()) {
            return value;
        }
        return MaskRule.toMaskString(value, leftKeep, rightKeep);
    }

    /**
     * 字符串脱敏
     *
     * @param value     原始字符串
     * @param maskRules 执行多个脱敏规则
     * @return 脱敏串
     */
    public static String maskStringEx(String value, MaskRule... maskRules) {
        if (logger.isDebugEnabled() || logger.isTraceEnabled()) {
            return value;
        }
        return MaskRule.toMaskString(value, maskRules);
    }
    /**
     * 字符串脱敏
     *
     * @param value     原始字符串
     * @param maskTypes 执行多个脱敏类型
     * @return 脱敏串
     * @deprecated 请使用 maskStringEx(String value, MaskRule... maskRules)
     */
    public static String maskStringEx(String value, MaskType... maskTypes) {
        if (logger.isDebugEnabled() || logger.isTraceEnabled()) {
            return value;
        }
        MaskRule[] maskRules = new MaskRule[maskTypes.length];
        for (int i = 0; i < maskRules.length; i++) {
            maskRules[i] = maskTypes[i].getRule();
        }

        return MaskRule.toMaskString(value, maskRules);
    }
}
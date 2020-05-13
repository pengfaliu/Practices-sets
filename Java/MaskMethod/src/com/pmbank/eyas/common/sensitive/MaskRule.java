/**
 * Created by lfp on 2020/5/13.
 */

package com.pmbank.eyas.common.sensitive;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MaskRule {
    // === 原MaskType逻辑 ===
    public static final char MASK_CHR = '*';
    public static final String MASK_HIDE = "****";
    public static final String MASK_PASSWORD = "********";

    public static MaskRule DEFAULT = new MaskRule() {
        @Override
        public String toMask(String value, Mask mask) {
            return toMaskString(value, mask.maskChar(), mask.leftKeep(), mask.rightKeep());
        }

        @Override
        public String toMask(String value) {
            throw new UnsupportedOperationException("MaskRule DEFAULT");
        }
    };

    // 隐藏
    public static MaskRule HIDDEN = new MaskRule() {
        @Override
        public String toMask(String value, Mask mask) {
            return value == null ? null : MASK_HIDE;
        }

        @Override
        public String toMask(String value) {
            return value == null ? null : MASK_HIDE;
        }
    };

    // 密码
    public static MaskRule PASSWORD = new MaskRule() {
        @Override
        public String toMask(String value, Mask mask) {
            return value == null ? null : MASK_PASSWORD;
        }

        @Override
        public String toMask(String value) {
            return value == null ? null : MASK_PASSWORD;
        }
    };

    // 中文名
    public static MaskRule CHN_NAME = new MaskRule(1, 0);

    // 电子邮件
    public static MaskRule EMAIL = new MaskRule(1, 0
            , Pattern.compile("(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)")) {

        @Override
        public String toMask(String value, Mask mask) {
            return doMask(value, mask.maskChar());
        }

        @Override
        public String toMask(String value) {
            return doMask(value, this.maskChar);
        }

        private String doMask(String value, char maskChar) {
            if (value == null) return null;

            int index = value.indexOf("@");
            if (index == -1) {
                return value;
            } else {
                return toMaskString(value, maskChar, this.leftKeep, value.length() - index);
            }
        }
    };

    // 身份证号
    public static MaskRule ID_CARD = new MaskRule(3, 4
            , Pattern.compile("[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([012]\\d)|3[0-1])\\d{3}([0-9]|X|x)|[1-9]\\d{7}((0\\d)|(1[0-2]))(([012]\\d)|3[0-1])\\d{3}"));

    // 座机号
    public static MaskRule PHONE_FIXED = new MaskRule(0, 4
            , Pattern.compile("(0[0-9]{2,3}\\-?)[0-9]{7,8}"));

    // 手机号
    public static MaskRule PHONE_MOBILE = new MaskRule(3, 4
            , Pattern.compile("(\\+?86)?(1[3578][0-9]{9})"));

    //银行卡
    public static MaskRule BANK_CARD = new MaskRule(6, 4
            , Pattern.compile("[0-9]{19}|[0-9]{16}"));

    // 地址
    public static MaskRule ADDRESS = new MaskRule(6, 0
            , Pattern.compile("(北京|天津|上海|重庆)市?[a-zA-Z0-9\\u4e00-\\u9fa5]*|(黑龙江|吉林|辽宁|河南|河北|山东|山西|陕西|甘肃|青海|四川|湖北|湖南|安徽|江苏|浙江|江西|贵州|云南|广东|福建|海南|台湾)省?[a-zA-Z0-9\\u4e00-\\u9fa5]*|(新疆|西藏|宁夏|内蒙古|广西)(自治区)?[a-zA-Z0-9\\u4e00-\\u9fa5]*"));

    protected char maskChar = MASK_CHR;
    protected int leftKeep = 0;
    protected int rightKeep = 0;
    protected Pattern pattern;

    public MaskRule() {
    }

    public MaskRule(int leftKeep, int rightKeep) {
        this.leftKeep = leftKeep;
        this.rightKeep = rightKeep;
    }

    public MaskRule(int leftKeep, int rightKeep, Pattern pattern) {
        this(MASK_CHR, leftKeep, rightKeep, pattern);
    }

    public MaskRule(char maskChar, int leftKeep, int rightKeep, Pattern pattern) {
        this.maskChar = maskChar;
        this.leftKeep = leftKeep;
        this.rightKeep = rightKeep;
        this.pattern = pattern;
    }

    public char getMaskChar() {
        return maskChar;
    }

    public void setMaskChar(char maskChar) {
        this.maskChar = maskChar;
    }

    public int getLeftKeep() {
        return leftKeep;
    }

    public void setLeftKeep(int leftKeep) {
        this.leftKeep = leftKeep;
    }

    public int getRightKeep() {
        return rightKeep;
    }

    public void setRightKeep(int rightKeep) {
        this.rightKeep = rightKeep;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public String toMask(String value) {
        return toMaskString(value, this.maskChar, this.leftKeep, this.rightKeep);
    }

    public String toMask(String value, Mask mask) {
        return toMaskString(value, mask.maskChar(), this.leftKeep, this.rightKeep);
    }

    /**
     * 通过正则表达式过滤
     *
     * @param value     原字符串
     * @param MaskRules 遮罩类型
     * @return 脱敏字符串
     */
    public static String toMaskString(String value, MaskRule... MaskRules) {
        if (value == null) return null;

        if (MaskRules.length > 0) {
            for (MaskRule MaskRule : MaskRules) {
                if (MaskRule.getPattern() != null) {
                    int idx = 0;
                    StringBuilder sb = new StringBuilder(value.length());
                    Matcher matcher = MaskRule.getPattern().matcher(value);
                    while (matcher.find()) {
                        sb.append(value.substring(idx, matcher.start())).append(MaskRule.toMask(matcher.group()));
                        idx = matcher.end();
                    }
                    if (idx > 0) {
                        sb.append(value.substring(idx)).toString();
                        value = sb.toString();
                    }
                }
            }
        }

        return value;
    }

    /**
     * 字符串脱敏
     *
     * @param value     原字符串
     * @param leftKeep  左保留
     * @param rightKeep 右保留
     * @return 脱敏字符串
     */
    public static String toMaskString(String value, int leftKeep, int rightKeep) {
        return toMaskString(value, MASK_CHR, leftKeep, rightKeep);
    }

    /**
     * 字符串脱敏
     *
     * @param value     原字符串
     * @param maskChar  遮罩字符
     * @param leftKeep  左保留
     * @param rightKeep 右保留
     * @return 脱敏字符串
     */
    public static String toMaskString(String value, char maskChar, int leftKeep, int rightKeep) {
        if (value == null) return null;

        StringBuilder sb = new StringBuilder();

        int length = value.length();
        int totalKeep = leftKeep + rightKeep;
        if (totalKeep == 0) {
            for (int i = 0; i < length; i++) {
                sb.append(maskChar);
            }
            return sb.toString();
        } else if (totalKeep < length) {
            for (int j = 0; j < length; j++) {
                if (j < leftKeep) {
                    sb.append(value.charAt(j));
                } else if (j > (length - rightKeep - 1)) {
                    sb.append(value.charAt(j));
                } else {
                    sb.append(maskChar);
                }
            }
            return sb.toString();
        } else {
            return value;
        }
    }

    // === 支持MAP脱敏 ===
    private static final MaskRule MASK_RULE = new MaskRule();
    private static Map<String, MaskRule> maskRules = new ConcurrentHashMap<>(16);

    static {
        MASK_RULE.addRule("name", MaskRule.CHN_NAME);
        MASK_RULE.addRule("password", MaskRule.PASSWORD);
        MASK_RULE.addRule("idCard", MaskRule.ID_CARD);
        MASK_RULE.addRule("bankCard", MaskRule.BANK_CARD);
        MASK_RULE.addRule("mobile", MaskRule.PHONE_MOBILE);
        MASK_RULE.addRule("phone", MaskRule.PHONE_FIXED);
        MASK_RULE.addRule("address", MaskRule.ADDRESS);
        MASK_RULE.addRule("email", MaskRule.EMAIL);
    }

    public static MaskRule addRule(String key, MaskRule MaskRule) {
        MASK_RULE.maskRules.put(key, MaskRule);
        return MASK_RULE;
    }

    public static MaskRule clearRules() {
        MASK_RULE.maskRules.clear();
        return MASK_RULE;
    }

    public static MaskRule removeRule(String key) {
        MASK_RULE.maskRules.remove(key);
        return MASK_RULE;
    }

    public static Map<String, ?> toMask(Map<String, ?> map) {
        Map<String, Object> clone = new HashMap<>(map);
        for (Map.Entry<String, MaskRule> entry : MASK_RULE.maskRules.entrySet()) {
            if (clone.containsKey(entry.getKey())) {
                Object value = clone.get(entry.getKey());
                if (value instanceof String) {
                    clone.put(entry.getKey(), entry.getValue().toMask((String) value));
                }
            }
        }

        return clone;
    }
}
package com.pmbank.eyas.common.sensitive;

/**
 * Created by lfp on 2020/5/13.
 */
public enum MaskType {
    DEFAULT(MaskRule.DEFAULT),
    HIDDEN(MaskRule.HIDDEN),
    CHN_NAME(MaskRule.CHN_NAME),
    PASSWORD(MaskRule.PASSWORD),
    PHONE_MOBILE(MaskRule.PHONE_MOBILE),
    PHONE_FIXED(MaskRule.PHONE_FIXED),
    BANK_CARD(MaskRule.BANK_CARD),
    ID_CARD(MaskRule.ID_CARD),
    EMAIL(MaskRule.EMAIL),
    ADDRESS(MaskRule.ADDRESS),
    //
    ;

    private MaskRule maskRule;

    private MaskType(MaskRule maskRule) {
        this.maskRule = maskRule;
    }

    public MaskRule getRule() {
        return maskRule;
    }

    public String toMask(String value, Mask mask) {
        return maskRule.toMask(value, mask);
    }
}

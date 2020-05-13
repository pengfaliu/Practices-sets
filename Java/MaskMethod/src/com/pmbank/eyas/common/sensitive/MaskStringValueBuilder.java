/**
 * Created by lfp on 2020/5/13.
 */
package com.pmbank.eyas.common.sensitive;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



public class MaskStringValueBuilder extends ReflectionToStringBuilder {
    private static final Field[] NO_FIELDS = new Field[0];
    private static final MaskRecursiveToStringStyle MASK_TO_STRING_STYLE = new MaskRecursiveToStringStyle();
    private static final Map<Class<?>, Field[]> declaredFieldsCache = new ConcurrentHashMap(256);

    public MaskStringValueBuilder(Object object) {
        super(object, MASK_TO_STRING_STYLE);
    }

    public static String toString(final Object object) {
        return new MaskStringValueBuilder(object).toString();
    }

    @Override
    protected void appendFieldsIn(Class<?> clazz) {
        if (clazz.isArray()) {
            this.reflectionAppendArray(this.getObject());
        } else {
            Field[] fields = getDeclaredFields(clazz);
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName();
                if (this.accept(field)) {
                    try {
                        Object fieldValue = this.getValue(field);

                        if (fieldValue != null) {
                            if (field.getType() == String.class) {
                                Mask mask = field.getAnnotation(Mask.class);
                                // 需要隐藏
                                if (mask != null) {
                                    fieldValue = mask.value().toMask((String) fieldValue, mask);
                                }
                            }
                        }

                        this.append(fieldName, fieldValue);
                    } catch (IllegalAccessException ex) {
                        throw new InternalError("Unexpected IllegalAccessException: " + ex.getMessage());
                    }
                }
            }
        }
    }

    private static Field[] getDeclaredFields(Class<?> clazz) {
        Field[] result = (Field[]) declaredFieldsCache.get(clazz);
        if (result == null) {
            result = clazz.getDeclaredFields();
            AccessibleObject.setAccessible(result, true);
            declaredFieldsCache.put(clazz, result.length == 0 ? NO_FIELDS : result);
        }

        return result;
    }

    public static class MaskRecursiveToStringStyle extends ToStringStyle {
        private static final long serialVersionUID = 1L;

        public MaskRecursiveToStringStyle() {
            this.setUseShortClassName(true);
            this.setUseIdentityHashCode(false);
        }

        public void appendDetail(StringBuffer buffer, String fieldName, Object value) {
            if (!ClassUtils.isPrimitiveWrapper(value.getClass()) && !String.class.equals(value.getClass()) && !Date.class.equals(value.getClass())) {
                buffer.append(MaskStringValueBuilder.toString(value));
            } else {
                super.appendDetail(buffer, fieldName, value);
            }
        }
    }

}

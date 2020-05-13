/**
 * Created by lfp on 2020/5/13.
 */
package com.pmbank.eyas.common.sensitive;

import com.alibaba.fastjson.serializer.ValueFilter;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MaskJsonValueFilter implements ValueFilter {
    private static final Map<String, Mask> EMPTY_MAP = Collections.emptyMap();
    private static final Map<Class<?>, Map<String, Mask>> declaredMaskFieldsCache = new ConcurrentHashMap(256);

    private static  Mask getClassMaskFields(Class<?> clazz, String fieldName) {
        Map<String, Mask> fields = declaredMaskFieldsCache.get(clazz);
        if (fields == null) {
            fields = new ConcurrentHashMap<>();
            while (clazz != null && !Object.class.equals(clazz)) {
                for (Field field : clazz.getDeclaredFields()) {
                    Mask mask = field.getAnnotation(Mask.class);
                    if (mask != null) {
                        fields.put(field.getName(), mask);
                    }
                }

                clazz = clazz.getSuperclass();
            }

            declaredMaskFieldsCache.put(clazz, fields.isEmpty() ? EMPTY_MAP : fields);
        }

        return fields.get(fieldName);
    }

    @Override
    public Object process(Object object, String name, Object value) {
        Mask mask = getClassMaskFields(object.getClass(), name);
        if (value != null && mask != null) {
            return mask.value().toMask((String) value, mask);
        } else {
            return value;
        }
    }
}
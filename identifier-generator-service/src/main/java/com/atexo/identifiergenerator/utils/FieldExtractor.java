package com.atexo.identifiergenerator.utils;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
@UtilityClass
public class FieldExtractor {
    public static <T> Object extractFieldByName(T object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }
}

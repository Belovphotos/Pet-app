package ru.common.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EnumUtils {

    public static <T extends Enum<T>> T getEnums(String value, T[] values) {
        T result = null;
        for (T val : values) {
            if (value.equals(val.name())) {
                result = val;
                break;
            }
        }
        if (result == null) {
            throw new IllegalStateException();
        }
        return result;
    }

    public static <T extends Enum<T>> T getEnumsByValue(String value, T[] values) {
        T result = null;
        for (T val : values) {
            if (value.equals(val.toString())) {
                result = val;
                break;
            }
        }
        if (result == null) {
            throw new IllegalStateException();
        }
        return result;
    }
}

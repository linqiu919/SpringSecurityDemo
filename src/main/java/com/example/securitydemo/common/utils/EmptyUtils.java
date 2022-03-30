package com.example.securitydemo.common.utils;

import java.util.*;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
/**
 * @author chenxinbao
 * @version 1.0
 * @date 2022/3/28 17:08
 */
public class EmptyUtils {
    public static final String EMPTY_STRING = "";
    public static final List EMPTY_LIST;
    public static final Set EMPTY_SET;
    public static final Map EMPTY_MAP;

    private EmptyUtils() {
    }

    public static boolean isBlank(String blank) {
        return StringUtils.isBlank(blank);
    }

    public static boolean isNotBlank(String blank) {
        return StringUtils.isNotBlank(blank);
    }

    public static boolean isEmpty(Collection collection) {
        return CollectionUtils.isEmpty(collection);
    }

    public static boolean isNotEmpty(Collection collection) {
        return CollectionUtils.isNotEmpty(collection);
    }

    public static boolean isEmpty(Map map) {
        return MapUtils.isEmpty(map);
    }

    public static boolean isNotEmpty(Map map) {
        return MapUtils.isNotEmpty(map);
    }

    public static <T> Boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static <T> Boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNotNull(Object object) {
        return object != null;
    }

    public static boolean isNotHaveEmpty(Object... objects) {
        if (isEmpty(objects)) {
            return true;
        } else {
            Object[] var1 = objects;
            int var2 = objects.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Object temp = var1[var3];
                if (isNull(temp)) {
                    return false;
                }

                if (temp instanceof String) {
                    if (isBlank(temp.toString())) {
                        return false;
                    }
                } else if (temp instanceof Collection && isEmpty((Collection)temp)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isHaveEmpty(Object... objects) {
        if (isEmpty(objects)) {
            return false;
        } else {
            Object[] var1 = objects;
            int var2 = objects.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Object temp = var1[var3];
                if (temp instanceof String) {
                    if (isBlank(temp.toString())) {
                        return true;
                    }
                } else if (temp instanceof Collection) {
                    if (isEmpty((Collection)temp)) {
                        return true;
                    }
                } else if (temp == null) {
                    return true;
                }
            }

            return false;
        }
    }

    static {
        EMPTY_LIST = Collections.EMPTY_LIST;
        EMPTY_SET = Collections.EMPTY_SET;
        EMPTY_MAP = Collections.EMPTY_MAP;
    }
}

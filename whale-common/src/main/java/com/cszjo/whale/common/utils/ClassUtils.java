package com.cszjo.whale.common.utils;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;

public class ClassUtils {

    public static boolean isAbstractClassOrInterface(Class clazz) {
        return Modifier.isAbstract(clazz.getModifiers()) || clazz.isInterface();
    }

    public static Set<Class> getSuperClasses(Class c) {
        HashSet<Class> superClazz = Sets.newHashSet();
        getSuperClasses(c, superClazz);
        return superClazz;
    }

    private static void getSuperClasses(Class c, Set<Class> superClazz) {
        if (c == null || c == Object.class) {
            return;
        }
        superClazz.add(c);
        getSuperClasses(c.getSuperclass(), superClazz);
    }
}

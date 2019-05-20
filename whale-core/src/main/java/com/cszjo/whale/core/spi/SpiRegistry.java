package com.cszjo.whale.core.spi;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cszjo.whale.common.utils.ClassUtils;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class SpiRegistry {

    private static final Logger LOG =
            LoggerFactory.getLogger(SpiRegistry.class);
    private static final String PACKAGE_PREFIX = "com.cszjo.whale";

    private Map<Class<?>, Set<Class<?>>> spi = Maps.newHashMap();
    private Map<Class<?>, Object> spiInstance = Maps.newConcurrentMap();

    private SpiRegistry() {
        Reflections reflections = new Reflections(PACKAGE_PREFIX);
        Set<Class<?>> spiClazz =
                reflections.getTypesAnnotatedWith(Spi.class);
        for (Class c : spiClazz) {
            if (ClassUtils.isAbstractClassOrInterface(c)) {
                spi.put(c, Sets.newHashSet());
            }
        }
        for (Class c : spiClazz) {
            if (ClassUtils.isAbstractClassOrInterface(c)) {
                continue;
            }
            Set<Class> superClasses = ClassUtils.getSuperClasses(c);
            registerSpiClazz(c, superClasses);
            Class[] interfaces = c.getInterfaces();
            if (ArrayUtils.isNotEmpty(interfaces)) {
                for (Class _interface : interfaces) {
                    registerSpiClazz(c, Sets.newHashSet(_interface));
                }
            }
        }
    }

    private static class SpiRegisryHolder {
        private static final SpiRegistry INSTANCE = new SpiRegistry();
    }

    public SpiRegistry instance() {
        return SpiRegisryHolder.INSTANCE;
    }

    private void registerSpiClazz(Class c, Set<Class> superClasses) {
        for (Class superClz : superClasses) {
            if (superClz.getAnnotation(Spi.class) != null && spi.containsKey(superClz)) {
                spi.get(superClz).add(c);
            }
        }
    }



}

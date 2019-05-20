package com.cszjo.whale.core.test;

import org.junit.Assert;
import org.junit.Test;

import com.cszjo.whale.core.spi.SpiRegistry;

public class SpiRegistryTest {

    @Test
    public void superClassTest() {
        Class<? super SpiRegistry> superclass =
                SpiRegistry.class.getSuperclass();
        Assert.assertEquals(superclass, Object.class);
        Assert.assertNull(superclass.getSuperclass());
    }
}

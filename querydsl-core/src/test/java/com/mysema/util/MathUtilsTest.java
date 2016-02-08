/*
 * Copyright 2015, The Querydsl Team (http://www.querydsl.com/team)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mysema.util;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MathUtilsTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void Sum() {
        assertEquals(Integer.valueOf(5), MathUtils.sum(2, 3.0));
    }

    @Test
    public void Difference() {
        assertEquals(Integer.valueOf(2), MathUtils.difference(5, 3.0));
    }

    @Test
    public void Cast_Returns_CorrectType() {
        checkCast(1, BigDecimal.class);
        checkCast(1, BigInteger.class);
        checkCast(1, Double.class);
        checkCast(1, Float.class);
        checkCast(1, Integer.class);
        checkCast(1, Long.class);
        checkCast(1, Short.class);
        checkCast(1, Byte.class);
    }

    @Test
    public void Cast_Returns_Argument_As_Is_When_Compatible() {
        checkSame(BigDecimal.ONE, BigDecimal.class);
        checkSame(BigInteger.ONE, BigInteger.class);
        checkSame((double) 1, Double.class);
        checkSame((float) 1, Float.class);
        checkSame(1, Integer.class);
        checkSame((long) 1, Long.class);
        checkSame((short) 1, Short.class);
        checkSame((byte) 1, Byte.class);
    }

    @Test
    public void Cast_Returns_Null_When_Input_Is_Null() {
        Integer result = MathUtils.cast(null, Integer.class);
        assertNull(result);
    }

    @Test
    public void Cast_Throws_On_Unsupported_Numbers() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Unsupported target type");

        checkCast(1, AtomicInteger.class);
    }

    private static void checkCast(Number value, Class<? extends Number> targetClass) {
        Number target = MathUtils.cast(value, targetClass);
        assertSame(targetClass, target.getClass());
    }

    private static <N extends Number> void checkSame(N value, Class<N> targetClass) {
        N target = MathUtils.cast(value, targetClass);
        assertSame(value, target);
    }

}

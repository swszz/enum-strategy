package com.github.swszz.enumstrategy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FruitStringTest {

    @Test
    fun `대문자_변환_테스트`() {
        val appleString = FruitString("APPLE")
        val bananaString = FruitString("BANANA")
        val orangeString = FruitString("ORANGE")

        assertEquals(Fruit.APPLE, appleString.toEnumOrNull())
        assertEquals(Fruit.BANANA, bananaString.toEnumOrNull())
        assertEquals(Fruit.ORANGE, orangeString.toEnumOrNull())
    }

    @Test
    fun `소문자_변환_테스트`() {
        val appleString = FruitString("apple")
        val bananaString = FruitString("banana")
        val orangeString = FruitString("orange")

        assertEquals(Fruit.APPLE, appleString.toEnumOrNull())
        assertEquals(Fruit.BANANA, bananaString.toEnumOrNull())
        assertEquals(Fruit.ORANGE, orangeString.toEnumOrNull())
    }

    @Test
    fun `혼합대소문자_변환_테스트`() {
        val appleString = FruitString("Apple")
        val bananaString = FruitString("BaNaNa")
        val orangeString = FruitString("oRaNgE")

        assertEquals(Fruit.APPLE, appleString.toEnumOrNull())
        assertEquals(Fruit.BANANA, bananaString.toEnumOrNull())
        assertEquals(Fruit.ORANGE, orangeString.toEnumOrNull())
    }

    @Test
    fun `없는값_테스트`() {
        val nonExistentFruit = FruitString("GRAPE")

        assertNull(nonExistentFruit.toEnumOrNull())
    }

    @Test
    fun `없는 값 Throw 테스트`() {
        val nonExistentFruit = FruitString("GRAPE")
        assertThrows<RuntimeException> {
            nonExistentFruit.toEnumOrThrow { RuntimeException("해당 과일은 존재하지 않습니다.") }
        }
    }
}
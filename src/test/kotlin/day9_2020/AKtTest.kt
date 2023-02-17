package day9_2020

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AKtTest {

    @Test
    fun isValid() {

        assertTrue(isValid(40, listOf(35, 20, 15, 25, 47)))

        assertTrue(isValid(182, listOf(65, 95, 102, 117, 150)))

        assertFalse(isValid(127, listOf(95, 102, 117, 150, 182)))

        assertEquals(findRange(127, listOf(35, 20, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576)), listOf(15L, 25L, 47L, 40L))

    }
}
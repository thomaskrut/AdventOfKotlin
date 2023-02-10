package day20_2022

import move
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AKtTest {

    @Test
    fun testMove() {

        var list : MutableList<Int> = mutableListOf(3, -4, -1, 2, 8, 9, 2)

        var expectedResult: MutableList<Int> = mutableListOf(-4, -1, 2, 3, 8, 9, 2)

        list.move(0)

        assertEquals(list, expectedResult)

        list.move(6)

        expectedResult = mutableListOf(-4, 2, -1, 2, 3, 8, 9)

        assertEquals(list, expectedResult)

        list.move(0)

        expectedResult = mutableListOf(2, -1, 2, -4, 3, 8, 9)

        assertEquals(list, expectedResult)

        list.move(6)

        expectedResult = mutableListOf(2, -1, 9, 2, -4, 3, 8)

        assertEquals(list, expectedResult)

    }
}
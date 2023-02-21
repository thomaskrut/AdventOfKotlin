package day9_2021

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class AKtTest {

    @Test
    fun testFindLowPoints() {

        val stringList = File("src/main/kotlin/day9_2021", "inputtest.txt").readLines()
        val result = findLowPoints(stringList).sumOf { it + 1 }
        assertEquals(result, 15)

    }

    @Test
    fun testFindSizeOfBasins() {
        val stringList = File("src/main/kotlin/day9_2021", "inputtest.txt").readLines()
        var result = 1
        findSizeOfBasins(stringList).sorted().takeLast(3).forEach {
            result *= it
        }
        assertEquals(result, 1134)
    }
}
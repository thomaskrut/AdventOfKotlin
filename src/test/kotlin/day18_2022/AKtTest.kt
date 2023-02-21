package day18_2022

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class AKtTest {

    @Test
    fun testCountNumberOfExposedSides() {

        val stringList = File("src/main/kotlin/day18_2022", "inputtest.txt").readLines()

        val droplets = stringList.map { s ->
            val (x, y, z) = s.split(",").map { it.toInt() }
            Point(x, y, z)
        }

        assertTrue(Point(1, 1, 1).isAdjacent(Point(1, 1, 0), droplets))


        val j = droplets.sumOf { d -> getAdjacentEmptyPoints(d, droplets).size }

        assertEquals(j, 64)


    }


}
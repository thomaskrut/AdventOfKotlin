package day20_2022

import getNthNumberAfterValue
import move
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class AKtTest {

    @Test
    fun testMove() {

        var longList: MutableList<Long> = mutableListOf(811589153, 1623178306, -2434767459, 2434767459, -1623178306, 0, 3246356612)
        var longExpectedResultsList: MutableList<Long> = mutableListOf(0, -2434767459, 3246356612, -1623178306, 2434767459, 1623178306, 811589153)

        var list: MutableList<Element> = MutableList(longList.size) { Element(it, longList[it]) }
        val expectedResult: MutableList<Element> = MutableList(longExpectedResultsList.size) { Element(it, longExpectedResultsList[it]) }

        list.sortedWith(compareBy {it.index}).forEach {e -> list.move(list.indexOf(e))}

        longList.clear()

        list.forEach{e -> longList.add(e.value)}

        assertEquals(longList, longExpectedResultsList)

        assertEquals(list.getNthNumberAfterValue(1000, 0), 811589153)

    }
}



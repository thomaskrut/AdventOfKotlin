package day20_2022

import getNthNumberAfterValue
import move
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class AKtTest {

    @Test
    fun testMoveAoCExample() {

        var longList: MutableList<Long> = mutableListOf(811589153L, 1623178306L, -2434767459L, 2434767459L, -1623178306L, 0L, 3246356612L)
        var longExpectedResultsList: MutableList<Long> = mutableListOf(0L, -2434767459L, 3246356612L, -1623178306L, 2434767459L, 1623178306L, 811589153L)

        var list: MutableList<Element> = MutableList(longList.size) { Element(it, longList[it]) }
        val expectedResult: MutableList<Element> = MutableList(longExpectedResultsList.size) { Element(it, longExpectedResultsList[it]) }

        list.sortedWith(compareBy {it.index}).forEach {e -> list.move(list.indexOf(e))}

        list.forEach{e -> print(e.value)}
        println()
        expectedResult.forEach{e -> print(e.value)}

        longList.clear()

        list.forEach{e -> longList.add(e.value)}

        assertEquals(longList, longExpectedResultsList)


        assertEquals(list.getNthNumberAfterValue(1000, 0), 4)

    }
}



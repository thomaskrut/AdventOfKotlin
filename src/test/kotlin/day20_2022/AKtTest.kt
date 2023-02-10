package day20_2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class AKtTest {

    @Test
    fun testMoveAoCExample() {

        var intList: MutableList<Int> = mutableListOf(1, 2, -3, 3, -2, 0, 4)
        var intResultList: MutableList<Int> = mutableListOf(1, 2, -3, 4, 0, 3, -2)

        var list: MutableList<Element> = MutableList(intList.size) { Element(it, intList[it]) }
        var expectedResult: MutableList<Element> = MutableList(intResultList.size) { Element(it, intResultList[it]) }

        list.sortedWith(compareBy {it.index}).forEach {e -> list.move(list.indexOf(e))}

        list.forEach{e -> print(e.value)}
        println()
        expectedResult.forEach{e -> print(e.value)}

        intList.clear()

        list.forEach{e -> intList.add(e.value)}

        assertEquals(intList, intResultList)



    }
}



package day20_2022

import getNthNumberAfterValue
import day18_2022.move
import move
import java.io.File

class Element(var index: Int, var value: Long)

fun main() {
    val stringList = File("src/day18_2022.main/kotlin/day20_2022", "input.txt").readLines()

    val intList = stringList.map { s -> s.toLong() }

    var input: MutableList<Element> = MutableList(intList.size) { Element(it, intList[it]) }

    input.sortedWith(compareBy { it.index }).forEach { e -> input.move(input.indexOf(e)) }


    println(input.getNthNumberAfterValue(1000, 0) + input.getNthNumberAfterValue(2000, 0) + input.getNthNumberAfterValue(3000, 0))


}





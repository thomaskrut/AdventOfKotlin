package day20_2022

import getNthNumberAfterValue
import move
import java.io.File
import kotlin.math.abs



fun main() {
    val stringList = File("src/main/kotlin/day20_2022", "input.txt").readLines()

    val intList = stringList.map { s -> s.toLong()}

    var input: MutableList<Element> = MutableList(intList.size) { Element(it, intList[it] * 811589153) }

    repeat(10) {
        input.sortedWith(compareBy { it.index }).forEach { e -> input.move(input.indexOf(e)) }
    }


    println(input.getNthNumberAfterValue(1000, 0))
    println(input.getNthNumberAfterValue(2000, 0))
    println(input.getNthNumberAfterValue(3000, 0))

    println(input.getNthNumberAfterValue(1000, 0) + input.getNthNumberAfterValue(2000, 0) + input.getNthNumberAfterValue(3000, 0))


}





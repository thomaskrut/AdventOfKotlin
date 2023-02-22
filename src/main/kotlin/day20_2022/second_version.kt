package day20_2022

import getNthNumberAfterValue
import java.io.File
import kotlin.math.abs

// Todd Ginsberg löser denna dag med väldigt lite kod, och på ett sätt som liknar min lösning, förutom
// att han inte flyttar varje tal ett steg i taget. Jag lånade mod()-funktionen han använder för att räkna
// ut hur många steg ett tal ska flyttas, utan att flytta det ett steg i taget. Resultatet är ett program
// som räknar ut svaret på millisekunder istället för minuter.
// https://todd.ginsberg.com/post/advent-of-code/2022/day20/
// Mer om % och mod(): https://medium.com/@alexvanyo/kotlin-functions-rem-and-mod-fa2e865304c3

fun MutableList<Element>.moveOptimized(index: Int) {

    var currentIndex = index
    val newIndex: Int
    val elementToMove: Element = this.removeAt(currentIndex)
    newIndex = (currentIndex + elementToMove.value).mod(size)
    this.add(newIndex, elementToMove)

}


fun main() {

    val longList = File("src/main/kotlin/day20_2022", "input.txt").readLines().map { s -> s.toLong() }

    val input: MutableList<Element> = MutableList(longList.size) { Element(it, longList[it]) }

    println("Solving part 1...")

    input.sortedWith(compareBy { it.index }).forEach { e -> input.moveOptimized(input.indexOf(e)) }

    println(input.getNthNumberAfterValue(1000, 0) + input.getNthNumberAfterValue(2000, 0) + input.getNthNumberAfterValue(3000, 0))

    val input2: MutableList<Element> = MutableList(longList.size) { Element(it, longList[it] * 811589153) }

    println("Solving part 2...")

    repeat(10) {
        input2.sortedWith(compareBy { it.index }).forEach { e -> input2.moveOptimized(input2.indexOf(e)) }
    }

    println(input2.getNthNumberAfterValue(1000, 0) + input2.getNthNumberAfterValue(2000, 0) + input2.getNthNumberAfterValue(3000, 0))

}





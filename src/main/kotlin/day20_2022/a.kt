package day20_2022

import java.io.File
import kotlin.math.abs

class Element(var index: Int, var value: Int)

fun main() {
    val stringList = File("src/main/kotlin/day20_2022", "input.txt").readLines()

    val intList = stringList.map { s -> s.toInt() }

    var input: MutableList<Element> = MutableList(intList.size) { Element(it, intList[it]) }

    input.sortedWith(compareBy {it.index}).forEach {e -> input.move(input.indexOf(e))}


    var index : Int

    index = input.indexOf(input.filter{ e -> e.value == 0}[0])

    repeat(1000) {
        index++
        if (index == input.size) index = 0
    }

    val valueAt1000 = input[index].value

    index = input.indexOf(input.filter{ e -> e.value == 0}[0])

    repeat(2000) {
        index++
        if (index == input.size) index = 0
    }

    val valueAt2000 = input[index].value

    index = input.indexOf(input.filter{ e -> e.value == 0}[0])

    repeat(3000) {
        index++
        if (index == input.size) index = 0
    }

    val valueAt3000 = input[index].value

    println(valueAt1000 + valueAt2000 + valueAt3000)


}

fun MutableList<Element>.move(index: Int) {


    var currentIndex = index
    var elementToMove : Element
    var newIndex: Int


    val moveOneStep = if (this[index].value > 0) 1 else -1

    repeat(abs(this[index].value.toDouble()).toInt()) {

        elementToMove  = this.removeAt(currentIndex)
        newIndex = currentIndex + moveOneStep



        if (moveOneStep == 1 && newIndex >= this.size) {

            newIndex = 0
        }

        if (moveOneStep == -1 && newIndex <= 0) {

            newIndex = this.size
        }

        this.add(newIndex, elementToMove)

        currentIndex = newIndex

    }


}




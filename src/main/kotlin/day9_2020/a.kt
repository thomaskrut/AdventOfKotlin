package day9_2020

import java.io.File

fun isValid(input: Long, list: List<Long>): Boolean {

    return list.any { x ->
        (list.filter { y -> x + y == input && x != y }).any()
    }

}

fun findRange(sumToFind: Long, list: List<Long>): List<Long> {

    list.forEachIndexed { index, _ ->

        for (i in index..list.size) {

            if (list.subList(index, i).sum() == sumToFind) return list.subList(index, i)

        }

    }
    return listOf(-1)
}


fun main() {
    val stringList = File("src/day22_2020.day5_2020.day9_2021.main/kotlin/day9_2020", "input.txt").readLines()

    stringList.map { it.toLong() }.windowed(26, 1).forEach {
        if (!isValid(it.takeLast(1)[0], it.dropLast(1))) println(it.takeLast(1)[0])
    }

    println(findRange(1309761972, stringList.map { it.toLong() }).max() + findRange(1309761972, stringList.map { it.toLong() }).min())

}
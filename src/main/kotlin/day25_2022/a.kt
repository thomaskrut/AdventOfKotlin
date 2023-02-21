package day25_2022

import java.io.File
import kotlin.math.pow

fun Char.toDecimal(indexFromTheRight: Int): Long {

    val factor = 5.0.pow(indexFromTheRight - 1.toDouble()).toLong()

    when (this) {
        '=' -> return factor * -2
        '-' -> return factor * -1
        '0' -> return 0
        '1' -> return factor
        '2' -> return factor * 2
    }
    return -1
}


fun String.toDecimal(): Long {

    var result: Long = 0

    for (i in this.length - 1 downTo 0) {

        result += this[i].toDecimal(this.length - i)

    }

    return result
}

fun Long.toSnafu(): String =
    generateSequence(this) { (it + 2) / 5 }
        .takeWhile { it != 0L }
        .map { "012=-"[(it % 5).toInt()] }
        .joinToString("")
        .reversed()



fun main() {

    val input = File("src/main/kotlin/day25_2022", "input.txt").readLines()

    var sum: Long = 0

    input.map { s -> s.toDecimal() }.forEach { i ->
        sum += i
        println(i)
    }
    println("Summa: $sum")

    println(sum.toSnafu())

}
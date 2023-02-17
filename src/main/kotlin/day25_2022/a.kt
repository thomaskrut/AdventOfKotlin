package day25_2022

import java.io.File
import kotlin.math.pow

fun randomSnafuDigit(): Char {

    when ((-2..2).random()) {
        -2 -> return '='
        -1 -> return '-'
        0 -> return '0'
        1 -> return '1'
        2 -> return '2'
    }
    return 'e'
}

fun randomSnafuNumber(length: Int): String {

    var result = ""

    repeat(length) {
        result += randomSnafuDigit()
    }

    return result

}


fun Long.toSnafu(): String {

    var testString: String
    var result: Long

    repeat(300000000) {
        testString = randomSnafuNumber(this.toString().length + 2)
        result = testString.toDecimal()
        if (result == this) return testString
    }
    return "not found"

}

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

fun Long.toSnafu2(): String =
    generateSequence(this) { (it + 2) / 5 }
        .takeWhile { it != 0L }
        .map { "012=-"[(it % 5).toInt()] }
        .joinToString("")
        .reversed()

fun testIntegerDivision(input: Long) {

    var result = (input + 2) / 5
    var remainder = input % 5
    var s = ""

    while (result > 0) {

        s += ("012=-"[remainder.toInt()])
        remainder = result % 5
        result = (result + 2) / 5

    }

println(s.reversed())

}

fun main() {

    val input = File("src/day18_2022.main/kotlin/day25_2022", "input.txt").readLines()

    var sum: Long = 0

    input.map { s -> s.toDecimal() }.forEach { i ->
        sum += i
        println(i)
    }
    println("Summa: $sum")
    println()
    println(testIntegerDivision(sum))
    println(sum.toSnafu2())

    //println(testIntegerDivision(4890))



}
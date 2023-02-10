package day25_2022

import java.io.File
import java.rmi.UnexpectedException
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
        testString = randomSnafuNumber(this.toString().length + 5)
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

fun testIntegerDivision(dec: Long) {

    var result = dec / 5
    var remainder = dec % 5

    while (result > 0) {
        println(result)
        println(remainder)
        remainder = result % 5
        result = result / 5

    }


}

fun main() {

    val input = File("src/main/kotlin/day25_2022", "input.txt").readLines()

    var sum: Long = 0

    input.map { s -> s.toDecimal() }.forEach { i ->
        sum += i
        println(i)
    }
    println("Summa: $sum")
    println()
    println(testIntegerDivision(sum))



}
import java.io.File

fun getRow(s: String): Int {

    var high = 127
    var low = 0
    println()

    s.filterIndexed { i, _ -> i < 7 }.forEach {
        when (it) {
            'F' -> high -= ((high - low) / 2) + 1
            'B' -> low += ((high - low) / 2) + 1

        }
        //println("$low - $high")
    }
    return high
}

fun getColumn(s: String): Int {

    var high = 7
    var low = 0
    println()

    s.filterIndexed { i, _ -> i > 6 }.forEach {
        when (it) {
            'L' -> high -= ((high - low) / 2) + 1
            'R' -> low += ((high - low) / 2) + 1

        }
        //println("$low - $high")
    }
    return high
}

fun getId(s: String): Int = (getRow(s) * 8) + getColumn(s)



fun main() {

    val stringList = File("src/main/kotlin/day5_2020", "input.txt").readLines()

    val result = stringList.map {
        getId(it)
    }.max()

    println(result)
}
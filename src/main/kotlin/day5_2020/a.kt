import java.io.File

fun getRow(s: String): Int {

    var high = 127
    var low = 0


    s.filterIndexed { i, _ -> i < 7 }.forEach {
        when (it) {
            'F' -> high -= ((high - low) / 2) + 1
            'B' -> low += ((high - low) / 2) + 1

        }

    }
    return high
}

fun getColumn(s: String): Int {

    var high = 7
    var low = 0


    s.filterIndexed { i, _ -> i > 6 }.forEach {
        when (it) {
            'L' -> high -= ((high - low) / 2) + 1
            'R' -> low += ((high - low) / 2) + 1

        }

    }
    return high
}

fun getId(s: String): Int = (getRow(s) * 8) + getColumn(s)



fun main() {

    val stringList = File("src/day9_2022.main/kotlin/day5_2020", "input.txt").readLines()

    val result = stringList.map {
        getId(it)
    }.max()

    stringList.map{getId(it)}.sorted().windowed(2, 1).filter { l -> (l[1] == (l[0] + 2)) }.forEach { println(it) }



}
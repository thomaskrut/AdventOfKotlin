import java.io.File

class Droplet(val x: Int, val y: Int, val z: Int) {


    override fun toString(): String {
        return "$x, $y, $z"
    }
}

fun main() {

    val stringList = File("src/main/kotlin/day18_2022", "inputtest.txt").readLines()

    val droplets = stringList.map { s ->
        s.split(",").map {
          s -> s.toInt()

        }.toList()


    }.toList()

    println(droplets)
}
import java.io.File

data class Droplet(val x: Int, val y: Int, val z: Int) {


    fun checkForAir(step: Int, dirx : Int = 0, diry: Int = 0, dirz: Int = 0, list: List<Droplet>): Int {

        if (step == 50) return step

        val point = Droplet(this.x + dirx, this.y + diry, this.z + dirz)
        if (list.contains(point)) return step

        return checkForAir(step + 1, dirx, diry, dirz, list)

    }

    fun isAdjacent(input: Droplet, list: List<Droplet>): Boolean {

        if ((input.x == this.x) && (input.y == this.y)) {
            if ((input.z == this.z + 1) || (input.z == this.z - 1)) return true
        }

        if ((input.x == this.x) && (input.z == this.z)) {
            if ((input.y == this.y + 1) || (input.y == this.y - 1)) return true
        }

        if ((input.z == this.z) && (input.y == this.y)) {
            if ((input.x == this.x + 1) || (input.x == this.x - 1)) return true
        }

        return false

    }

}

fun counterNumberOfExposedSides(droplet: Droplet, list: List<Droplet>): Int {

    var result = 0

    list.forEach {
        if (droplet.isAdjacent(it, list)) result++

    }

    return 6 - result

}



fun main() {

    val stringList = File("src/main/kotlin/day18_2022", "input.txt").readLines()

    val droplets = stringList.map { s ->
        val (x, y, z) = s.split(",").map { it.toInt() }
        Droplet(x, y, z)
    }

    val totalNumberOfExposedSides = droplets.sumOf { d -> counterNumberOfExposedSides(d, droplets) }

    println(totalNumberOfExposedSides)


}
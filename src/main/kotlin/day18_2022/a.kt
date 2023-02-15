import java.io.File


var totalListOfEmptyPoints = mutableListOf<Point>()

data class Point(val x: Int, val y: Int, val z: Int) {

    fun isAdjacent(input: Point, list: List<Point>): Boolean {

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

fun countNumberOfExposedSides(droplet: Point, listOfDroplets: List<Point>): Int {

    var result = 0

    listOfDroplets.forEach {
        if (droplet.isAdjacent(it, listOfDroplets)) result++

    }

    return 6 - result

}

fun getAdjacentDroplets(droplet: Point, listOfDroplets: List<Point>): List<Point> {

    return listOfDroplets.filter { d ->
        d.isAdjacent(droplet, listOfDroplets)
    }.toList()

}

fun getAdjacentEmptyPoints(droplet: Point, listOfDroplets: List<Point>): List<Point> {

    var listOfEmptyPoints = mutableListOf<Point>()

    if (!listOfDroplets.contains(Point(droplet.x + 1, droplet.y, droplet.z))) listOfEmptyPoints.add(
        Point(
            droplet.x + 1,
            droplet.y,
            droplet.z
        )
    )
    if (!listOfDroplets.contains(Point(droplet.x - 1, droplet.y, droplet.z))) listOfEmptyPoints.add(
        Point(
            droplet.x - 1,
            droplet.y,
            droplet.z
        )
    )
    if (!listOfDroplets.contains(Point(droplet.x, droplet.y + 1, droplet.z))) listOfEmptyPoints.add(
        Point(
            droplet.x,
            droplet.y + 1,
            droplet.z
        )
    )
    if (!listOfDroplets.contains(Point(droplet.x, droplet.y - 1, droplet.z))) listOfEmptyPoints.add(
        Point(
            droplet.x,
            droplet.y - 1,
            droplet.z
        )
    )
    if (!listOfDroplets.contains(Point(droplet.x, droplet.y, droplet.z + 1))) listOfEmptyPoints.add(
        Point(
            droplet.x,
            droplet.y,
            droplet.z + 1
        )
    )
    if (!listOfDroplets.contains(Point(droplet.x, droplet.y, droplet.z - 1))) listOfEmptyPoints.add(
        Point(
            droplet.x,
            droplet.y,
            droplet.z - 1
        )
    )
    println(droplet)

    val i = listOfEmptyPoints.filter { p -> checkIfOnSurface(p, listOfDroplets) }

    return i

}

fun checkIfOnSurface(point: Point, listOfDroplets: List<Point>): Boolean {


    totalListOfEmptyPoints.clear()
    val result = expand(1, 50, point, listOfDroplets)
    println(result)
    return (result == 50)

}

tailrec fun expand(step: Int, maxStep: Int, point: Point, listOfDroplets: List<Point>): Int {

    if (step == maxStep) {

        return step
    }
    var dirx: Int
    var diry: Int
    var dirz: Int

    var listOfNewPoints = mutableListOf<Point>()

    for (x in 1..6) {
        dirx = 0; diry = 0; dirz = 0
        when (x) {
            1 -> dirx = 1
            2 -> dirx = -1
            3 -> diry = 1
            4 -> diry = -1
            5 -> dirz = 1
            6 -> dirz = -1
        }

        val newPoint = Point(point.x + dirx, point.y + diry, point.z + dirz)

        if (!(listOfDroplets.contains(newPoint)) && !(totalListOfEmptyPoints.contains(newPoint))) listOfNewPoints.add(newPoint)

    }

    if (listOfNewPoints.isNotEmpty()) {
        totalListOfEmptyPoints.addAll(listOfNewPoints)

        for (p in listOfNewPoints) {
            expand(step + 1, maxStep, p, listOfDroplets)
        }
    }

    return step

}


fun main() {

    val stringList = File("src/main/kotlin/day18_2022", "inputtest.txt").readLines()

    val droplets = stringList.map { s ->
        val (x, y, z) = s.split(",").map { it.toInt() }
        Point(x, y, z)
    }

    val totalNumberOfExposedSides = droplets.sumOf { d -> getAdjacentEmptyPoints(d, droplets).size }

    println(totalNumberOfExposedSides)


}


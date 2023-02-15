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
    return listOfEmptyPoints.filter { p -> checkIfOnSurface(p, listOfDroplets)}

}

fun checkIfOnSurface(point: Point, listOfDroplets: List<Point>): Boolean {

    return (expand(1, point, listOfDroplets) == 500)

}

fun expand(step: Int, point: Point, listOfDroplets: List<Point>, ): Int {


    if (step == 2) return step
    val listOfEmptyPoints = getAdjacentEmptyPoints(point, listOfDroplets).filter { p -> !totalListOfEmptyPoints.contains(p) }
    if (listOfEmptyPoints.isEmpty()) return step
    listOfEmptyPoints.forEach {
        totalListOfEmptyPoints.add(it)
    }
    listOfEmptyPoints.forEach {
        return expand(step + 1, it, listOfDroplets)
    }

   return step

}


fun main() {

    val stringList = File("src/main/kotlin/day18_2022", "input.txt").readLines()

    val droplets = stringList.map { s ->
        val (x, y, z) = s.split(",").map { it.toInt() }
        Point(x, y, z)
    }

    val totalNumberOfExposedSides = droplets.sumOf { d -> getAdjacentEmptyPoints(d, droplets).size }

    println(totalNumberOfExposedSides)


}


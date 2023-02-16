import java.io.File

var filledPoints = mutableListOf<Point>()

var outerSurfaces = 0

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

    return listOfEmptyPoints

}

fun fillWithWater(currentPoint: Point, z: Int, listOfDroplets: List<Point>) {

    if (listOfDroplets.contains(currentPoint)) {

        outerSurfaces++
        return
    }

    //if (listOfDroplets.contains(Point(currentPoint.x, currentPoint.y, currentPoint.z + 1))) outerSurfaces++
    //if (listOfDroplets.contains(Point(currentPoint.x, currentPoint.y, currentPoint.z - 1))) outerSurfaces++

    filledPoints.add(currentPoint)

    if ((currentPoint.x > -4) && (!filledPoints.contains(Point(currentPoint.x - 1, currentPoint.y, currentPoint.z)))) fillWithWater(Point(currentPoint.x - 1, currentPoint.y, currentPoint.z), z, listOfDroplets)
    if (currentPoint.x < 24 && (!filledPoints.contains(Point(currentPoint.x + 1, currentPoint.y, currentPoint.z)))) fillWithWater(Point(currentPoint.x + 1, currentPoint.y, currentPoint.z), z, listOfDroplets)
    if (currentPoint.y > -4 && (!filledPoints.contains(Point(currentPoint.x, currentPoint.y - 1, currentPoint.z)))) fillWithWater(Point(currentPoint.x, currentPoint.y - 1, currentPoint.z), z, listOfDroplets)
    if (currentPoint.y < 24 && (!filledPoints.contains(Point(currentPoint.x, currentPoint.y + 1, currentPoint.z)))) fillWithWater(Point(currentPoint.x, currentPoint.y + 1, currentPoint.z), z, listOfDroplets)
    if (currentPoint.z > z -3 && (!filledPoints.contains(Point(currentPoint.x, currentPoint.y, currentPoint.z - 1)))) fillWithWater(Point(currentPoint.x, currentPoint.y,currentPoint.z - 1), z, listOfDroplets)
    if (currentPoint.z < z + 3 && (!filledPoints.contains(Point(currentPoint.x, currentPoint.y, currentPoint.z + 1)))) fillWithWater(Point(currentPoint.x, currentPoint.y, currentPoint.z + 1), z, listOfDroplets)



}

fun main() {

    val stringList = File("src/main/kotlin/day18_2022", "input.txt").readLines()

    val droplets = stringList.map { s ->
        val (x, y, z) = s.split(",").map {
            it.toInt() }
        Point(x, y, z)
    }

    val totalNumberOfExposedSides = droplets.sumOf { d -> getAdjacentEmptyPoints(d, droplets).size }
    println()
    println(totalNumberOfExposedSides)

    println()

    for (z in -5..35) {

        fillWithWater(Point(-5, -5, z), z, droplets)
        println(outerSurfaces)
    }




    println(outerSurfaces)


}


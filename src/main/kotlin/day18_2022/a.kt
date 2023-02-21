package day18_2022

import java.io.File

var visitedPoints = mutableListOf<Point>()

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

    val listOfEmptyPoints = mutableListOf<Point>()

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

    return listOfEmptyPoints.filter { p -> checkIfExposedToAir(p, listOfDroplets) }

}

fun checkIfExposedToAir(p: Point, listOfDroplets: List<Point>): Boolean {

    visitedPoints.clear()
    val result = move(p, listOfDroplets, 1)
    println(result)
    return result > 9000

}

fun move(p: Point, listOfDroplets: List<Point>, maxStep: Int): Int {


    if (maxStep > 1500) return 9999
    if (maxOf(p.x, p.y, p.z) > 25) return 9999
    if (minOf(p.x, p.y, p.z) < -1) return 9999

    visitedPoints.add(p)

    val pointsToVisit = mutableListOf<Point>()

    if (!visitedPoints.contains(Point(p.x + 1, p.y, p.z)) && !listOfDroplets.contains(
            Point(
                p.x + 1,
                p.y,
                p.z
            )
        )
    ) pointsToVisit.add(Point(p.x + 1, p.y, p.z))
    if (!visitedPoints.contains(Point(p.x - 1, p.y, p.z)) && !listOfDroplets.contains(
            Point(
                p.x - 1,
                p.y,
                p.z
            )
        )
    ) pointsToVisit.add(Point(p.x - 1, p.y, p.z))
    if (!visitedPoints.contains(Point(p.x, p.y + 1, p.z)) && !listOfDroplets.contains(
            Point(
                p.x,
                p.y + 1,
                p.z
            )
        )
    ) pointsToVisit.add(Point(p.x, p.y + 1, p.z))
    if (!visitedPoints.contains(Point(p.x, p.y - 1, p.z)) && !listOfDroplets.contains(
            Point(
                p.x,
                p.y - 1,
                p.z
            )
        )
    ) pointsToVisit.add(Point(p.x, p.y - 1, p.z))
    if (!visitedPoints.contains(Point(p.x, p.y, p.z + 1)) && !listOfDroplets.contains(
            Point(
                p.x,
                p.y,
                p.z + 1
            )
        )
    ) pointsToVisit.add(Point(p.x, p.y, p.z + 1))
    if (!visitedPoints.contains(Point(p.x, p.y, p.z - 1)) && !listOfDroplets.contains(
            Point(
                p.x,
                p.y,
                p.z - 1
            )
        )
    ) pointsToVisit.add(Point(p.x, p.y, p.z - 1))

    if (pointsToVisit.isNotEmpty()) {
        return pointsToVisit.maxOf { point ->
            move(point, listOfDroplets, maxStep + 1)
        }
    } else {
        return maxStep
    }

}


fun main() {

    val stringList = File("src/main/kotlin/day18_2022", "input.txt").readLines()

    val droplets = stringList.map { s ->
        val (x, y, z) = s.split(",").map {
            it.toInt()
        }
        Point(x, y, z)
    }

    val totalNumberOfExposedSides = droplets.sumOf { d -> getAdjacentEmptyPoints(d, droplets).size }
    println()
    println(totalNumberOfExposedSides)

    println()


}


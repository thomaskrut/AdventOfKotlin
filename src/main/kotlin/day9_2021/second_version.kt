/************************** --- Day 9: Smoke Basin --- **************************
 *
 * Detta pussel har en tvådimensionell karta över höjdskillnader som input, och del
 * ett går ut på att hitta alla lägsta punkter, samt utföra en beräkning av 'risknivå'
 * för dessa. Summan blir lösningen för del 1.
 *
 * För del två ska man hitta storleken på
 * alla 'sänkor', dvs alla områden där samtliga höjdnivåer är lägre än 9. Storleken
 * på de tre största sänkorna ska multipliceras för svaret på del 2.
 *
 *******************************************************************************/

package day9_2021

import java.io.File




fun findSizeOfBasinsOptimized(list: List<String>): List<Int> {

    val tempList = mutableListOf<Int>()
    val visitedPoints = mutableListOf<Point>()

    list.forEachIndexed { index1, s ->
        s.forEachIndexed { index2, c ->
            if (!visitedPoints.contains(Point(index1, index2))) tempList.add(
                checkAdjacentOptimized(
                    list,
                    visitedPoints,
                    index1,
                    index2,
                    1
                )
            )

        }
    }
    return tempList

}


fun Point.getAdjacentPoints(): List<Point> = listOf(

    Point(this.x - 1, this.y),
    Point(this.x + 1, this.y),
    Point(this.x, this.y - 1),
    Point(this.x, this.y + 1)

)

// Jag har här skrivit om metoden checkAdjacent() för att bli av med högen av if-satser och all
// kodupprepning de förde med sig. Jag har flyttat beräkningen för angränsande koordinater till Point-
// klassen med hjälp av en extension function - Todd Ginsberg gör på ett liknande sätt, även om han
// sedan använder metoden på andra sätt i sina lambdas. Hursomhelst tillät denna implementation att
// jag istället hämtade varje punkts angränsande punkter som en lista inför varje rekursion, och sedan
// filtrerade bort de som hade en 9:a och de som låg OutOfBounds. Sedan kunde
// rekursionen utföras som tidigare och antalet steg ackumuleras.

fun checkAdjacentOptimized(list: List<String>, visitedPoints: MutableList<Point>, index1: Int, index2: Int, steps: Int): Int {

    if (visitedPoints.contains(Point(index1, index2))) return 0
    if (list[index1][index2] == '9') return 0
    visitedPoints.add(Point(index1, index2))

    var result = steps

    Point(index1, index2).getAdjacentPoints()
        .filter { p -> p.x >= 0 && p.x < list.size - 1 && p.y >= 0 && p.y < list[index1].length - 1 && list[p.x][p.y] != '9'}
        .forEach {
            result += checkAdjacentOptimized(list, visitedPoints, it.x, it.y, steps)
        }

    return result

}


fun main() {

    val stringList = File("src/main/kotlin/day9_2021", "input.txt").readLines()

    println("Lösning del 1:")
    println(findLowPoints(stringList).sumOf { it + 1 })

    println()

    var result = findSizeOfBasinsOptimized(stringList).sorted().takeLast(3).reduce{ a, b -> a * b}

    println("Lösning del 2:")
    println(result)

}
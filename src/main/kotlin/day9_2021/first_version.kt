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
 * https://adventofcode.com/2021/day/9
 *
 *******************************************************************************/

package day9_2021

import java.io.File

data class Point(val x: Int, val y: Int)

// Jag hittar de lägsta punkterna genom att filtrera ut de punkter som har högre värden
// på platserna ovanför, under och på varje sida. Dessa mappas sedan till en lista
// av integers. Det blev nästlade lambdas och tyvärr kanske mindre läsbart än vad två
// for-loopar skulle blivit. Predikatet för filterIndexed()-metoden blev dessutom extremt långt
// då det innehåller flera if-satser i egenskap av uttryck styr vilka positioner som jämförs
// (för att undvika IndexOutOfBoundsExceptions). Skulle även ha velat returnera listan direkt
// utan att gå via result-variabeln, men hittade inte en lösning för det.
fun findLowPoints(list: List<String>): List<Int> {

    val result = mutableListOf<Int>()

    list.mapIndexed { index1, s ->
        (s.filterIndexed { index2, c ->
            ((s[index2 - (if (index2 > 0) 1 else -1)].digitToInt() > c.digitToInt()) && (s[index2 + (if (index2 < s.length - 1) 1 else -1)].digitToInt() > c.digitToInt()) && (list[index1 - (if (index1 > 0) 1 else -1)][index2].digitToInt() > c.digitToInt()) && (list[index1 + (if (index1 < list.size - 1) 1 else -1)][index2].digitToInt() > c.digitToInt()))

        })
    }.filter {
        it.isNotEmpty()
    }.map { s ->
        s.map {
            it.digitToInt()
        }.toCollection(result)
    }
    return result
}

// För att hitta storlekarna på de största sänkorna använder jag en rekursiv funktion, checkAdjacent().
// Den utgår från varje position på kartan, och kollar sedan hur många steg den kan gå åt varje håll
// innan den stöter på antingen en position som den redan testat eller en 9:a. Jag hittade inga passande
// lambdas för de rekursiva anropen, utan fick nöja mig med if-satser. Det ackumulerade värdet från metoden
// sparas i en lista som returneras av findSizeOfBasins(). Denna sorteras sedan, de tre största värdena plockas
// ut, och multipliceras med varandra för svar på del 2.
fun findSizeOfBasins(list: List<String>): List<Int> {

    val tempList = mutableListOf<Int>()
    val visitedPoints = mutableListOf<Point>()

    list.forEachIndexed { index1, s ->
        s.forEachIndexed { index2, c ->
            if (!visitedPoints.contains(Point(index1, index2))) tempList.add(
                checkAdjacent(
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

fun checkAdjacent(list: List<String>, visitedPoints: MutableList<Point>, index1: Int, index2: Int, steps: Int): Int {

    if (list[index1][index2] == '9') return 0
    visitedPoints.add(Point(index1, index2))

    var result = steps
    if ((index1 > 0) && list[index1 - 1][index2] != '9' && !visitedPoints.contains(
            Point(
                index1 - 1,
                index2
            )
        )
    ) result += checkAdjacent(list, visitedPoints, index1 - 1, index2, steps)
    if ((index1 < list.size - 1) && list[index1 + 1][index2] != '9' && !visitedPoints.contains(
            Point(
                index1 + 1,
                index2
            )
        )
    ) result += checkAdjacent(list, visitedPoints, index1 + 1, index2, steps)
    if ((index2 > 0) && list[index1][index2 - 1] != '9' && !visitedPoints.contains(
            Point(
                index1,
                index2 - 1
            )
        )
    ) result += checkAdjacent(list, visitedPoints, index1, index2 - 1, steps)
    if ((index2 < list[index1].length - 1) && list[index1][index2 + 1] != '9' && !visitedPoints.contains(
            Point(
                index1,
                index2 + 1
            )
        )
    ) result += checkAdjacent(list, visitedPoints, index1, index2 + 1, steps)

    return result

}


fun main() {

    val stringList = File("src/main/kotlin/day9_2021", "input.txt").readLines()

    println("Lösning del 1:")
    println(findLowPoints(stringList).sumOf { it + 1 })

    println()

    var result = 1
    findSizeOfBasins(stringList).sorted().takeLast(3).forEach {
        result *= it
    }

    println("Lösning del 2:")
    println(result)

}
// Efter att ha tittat på Todd Ginsbergs lösning på denna har jag gjort viss refaktorering. Bland annat
// konverterar jag till Long direkt vid filläsningen. Mer intressant är kanske användandet av takeWhile()
// i lösningen på del 2, som bara tar med de tal som finns före summan som ska hittas. Funktionen var ny för
// mig och kanske fastnar bättre i minnet när man använt den som del i en lösning.
//
// Jag gjorde även om min findRange()-funktion, se nedan. Jag lyckades använda en lambda liknande Todds med nästlade
// mappningar, istället för min ursprungliga for-loop, och som returnerar subListan efteråt istället för mitt i lambdan.
// Dock fick jag inte till indexeringen av listorna med mina mapIndexed-metoder (ville inte kopiera hans variant rakt av)
// så jag gjorde en egen variant med en if-sats som expression i parameterlistan till subList()-metoden, så att den inte
// använder ett index2 som är mindre än index1.
//
// https://todd.ginsberg.com/post/advent-of-code/2020/day9/

package day9_2020

import java.io.File

fun findRangeSecondVersion(sumToFind: Long, list: List<Long>): List<Long> =

    list.mapIndexedNotNull { index1, _ ->

        list.mapIndexedNotNull { index2, _ ->

            list.subList(index1, if (index2 > index1) index2 else index1).takeIf { it.sum() == sumToFind }

        }.firstOrNull()
    }.first()


fun main() {
    val stringList = File("src/main/kotlin/day9_2020", "input.txt").readLines().map { it.toLong() }

    var result = 0L

    stringList.windowed(26, 1).forEach {
        if (!isValid(it.takeLast(1).first(), it.dropLast(1))) {
            result = it.takeLast(1).first()
        }
    }

    println(result)

    val range = findRangeSecondVersion(result, stringList.takeWhile { it != result })

    println(range.max() + range.min())

}
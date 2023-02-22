/******************************* --- Day 9: Encoding Error --- *******************************
 *
 * Här ska man hitta det första talet i en sekvens som inte är summan av två av talen i de
 * senaste 25 talen av sekvensen (senaste 5 för testinputen). De två talen måste vara olika.
 *
 * För del 1 räcker det att hitta första talet som inte är giltigt enligt ovan.
 *
 * För del 2 ska man hitta en sammanhängande sekvens av tal som tillsammans utgör summan
 * av talet som hittades i del 1, och sedan lägga ihop det lägsta och högsta talet i denna
 * sekvens.
 *
 * https://adventofcode.com/2020/day/9
 *
 *********************************************************************************************/

package day9_2020

import java.io.File

fun isValid(input: Long, list: List<Long>): Boolean {

    return list.any { x ->
        (list.filter { y -> x + y == input && x != y }).any()
    }

}

fun findRange(sumToFind: Long, list: List<Long>): List<Long> {

    list.forEachIndexed { index, _ ->

        for (i in index..list.size) {

            if (list.subList(index, i).sum() == sumToFind) return list.subList(index, i)

        }

    }
    return listOf(-1)
}


fun main() {
    val stringList = File("src/main/kotlin/day9_2020", "input.txt").readLines()

    // Lösning del 1:
    // Varje tal görs först om till long. Sedan plockar funktionen windowed ut 26 element i taget, och vart
    // och ett av dessa skickas sedan tillsammans med delmängd av listan till min funktion isValid()
    // som kollar av det mot resten av listan och ger true om summan av dem är lika med sista talet i listan.
    // Om det är false har vi hittat rätt svar.

    var result = 0L

    stringList.map { it.toLong() }.windowed(26, 1).forEach {
        if (!isValid(it.takeLast(1)[0], it.dropLast(1))) {
            result = it.takeLast(1)[0]
        }
    }

    println(result)

    // Lösning del 2:
    // Del två löses med metoden findRange(), som tar den summa som eftersöks samt en lista med Longs att kolla igenom.
    // Det blev en for-loop som del av lambdan, vilket kanske inte är det elegantaste sättet, där listan gås igenom
    // en gång utifrån varje index i listan. Om ett range hittas vars summa motsvarar talet som eftersöks returneras detta
    // som en lista, som sparas i variabeln nedan.

    val range = findRange(result, stringList.map { it.toLong() })

    println(range.max() + range.min())

}
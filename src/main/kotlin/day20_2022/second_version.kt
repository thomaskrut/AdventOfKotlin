/************************** --- Day 20: Grove Positioning System --- ***********************
 *
 * Pusslet går ut på att avkryptera en lista med tal. Varje tal i listan ska flyttas framåt
 * eller bakåt ett antal steg motsvarande sitt värde (negativt värde flyttar bakåt). Listan
 * är cirkulär så om ett tal hamnar sist är nästa position först, och tvärtom. Talen ska
 * flyttas i den ordning de stod i den ursprungliga listan.
 *
 * I del 1 får man rätt svar av att leta fram vilket tal som står på position 1000 efter 0,
 * position 2000 efter 0 och position 3000 efter 0 och summera dessa.
 *
 * I del 2 ska talen först multipliceras med 811589153, sedan flyttas 10 ggr (varje gång i
 * den ordning som stod i den ursprungliga listan) och sedan ska samma positioner som ovan
 * hittas och summeras.
 *
 ******************************************************************************************/

package day20_2022

import getNthNumberAfterValue
import move
import java.io.File

// Todd Ginsberg löser denna dag med väldigt lite kod, och på ett sätt som liknar min lösning, förutom
// att han inte flyttar varje tal ett steg i taget.

fun main() {

    val longList = File("src/main/kotlin/day20_2022", "input.txt").readLines().map { s -> s.toLong() }

    val input: MutableList<Element> = MutableList(longList.size) { Element(it, longList[it]) }

    println("Solving part 1...")

    input.sortedWith(compareBy { it.index }).forEach { e -> input.move(input.indexOf(e)) }

    println(input.getNthNumberAfterValue(1000, 0) + input.getNthNumberAfterValue(2000, 0) + input.getNthNumberAfterValue(3000, 0))

    val input2: MutableList<Element> = MutableList(longList.size) { Element(it, longList[it] * 811589153) }

    println("Solving part 2...")

    repeat(10) { i ->
        input2.sortedWith(compareBy { it.index }).forEach { e -> input2.move(input2.indexOf(e)) }
        println("${(i + 1) * 10}% done")
    }

    println(input2.getNthNumberAfterValue(1000, 0) + input2.getNthNumberAfterValue(2000, 0) + input2.getNthNumberAfterValue(3000, 0))

}





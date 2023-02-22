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
 * https://adventofcode.com/2022/day/20
 *
 ******************************************************************************************/

package day20_2022

import getNthNumberAfterValue
import move
import java.io.File

class Element(var index: Int, var value: Long)

// I min lösning lyckades jag inte använda lambdas på något vettigt sätt i funktionerna,
// utan det blev mer old school iteration och if-satser (se fun.kt). Del 1 var förhållandevis
// enkel; för att hålla red på den ursprungliga talföljden sparade jag den i en lista av Element-objekt,
// där varje instans har sin ursprungliga position samt sitt värde. Den ursprungliga positionen
// används sedan i lambdauttrycket nedan för att sortera talen inför iterationen och förflyttningarna.
//
// Del 2 blev mer komplicerad då talen blev väldigt stora. Först fick jag byta typ till Long, och sedan
// fundera över om min lösning att flytta varje tal ett steg i taget i en loop behövde optimeras. Jag insåg
// att då listan bara var 5000 tal lång skulle varje tal flyttas väldigt många "varv" helt i onödan (de är
// nu i storleksordningen flera hundra miljarder, och varje enskild förflyttning på 5000 steg sätter talet
// tillbaka på sin ursprungsposition). Jag hittade en lösning med modulus för att räkna ut hur många steg
// varje tal faktiskt behöver flyttas. Lösningen går fortfarande ganska långsamt att räkna ut (flera minuter)
// men ger i varje fall rätt svar.

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





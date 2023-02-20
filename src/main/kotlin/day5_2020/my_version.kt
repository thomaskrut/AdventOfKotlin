/************************* --- Day 5: Binary Boarding --- **************************

https://adventofcode.com/2020/day/5

Baserat på en sträng bokstäver (front, back, left, right) ska man sortera
ut rätt platsnummer på ett flygplan. Först delas raderna in i främre eller
bakre halvan av flygplanet, sedan den halvan i främre eller bakre raden,
osv tills bara en rad återstår. Sedan gör man likadant med "kolumnera" och
hittar rätt platsnummer.

För del 1 ska man sortera ut alla platsnummer på
alla boardingpass och hitta det högsta ID-numret.

För del 2 ska man hitta sitt egen nummer (man har tappat sitt eget boardingpass)
genom att kolla vilket som inte finns med i den långa listan med alla nummer.

 ************************************************************************************/

package day5_2020

import java.io.File


// Metoderna getRow och getColumn går igenom varsin sträng med bokstäver
// (filtreras ut med lamba) och dessa påverkar sedan variablerna high och low
// med en enkel formel. Det värde som high har efter alla iterationer är
// värdet som strängen motsvarar.

fun getRow(s: String): Int {

    var high = 127
    var low = 0


    s.filterIndexed { i, _ -> i < 7 }.forEach {
        when (it) {
            'F' -> high -= ((high - low) / 2) + 1
            'B' -> low += ((high - low) / 2) + 1

        }

    }
    return high
}

fun getColumn(s: String): Int {

    var high = 7
    var low = 0


    s.filterIndexed { i, _ -> i > 6 }.forEach {
        when (it) {
            'L' -> high -= ((high - low) / 2) + 1
            'R' -> low += ((high - low) / 2) + 1

        }

    }
    return high
}

// Räknar ut unikt ID för en viss plats
fun getId(s: String): Int = (getRow(s) * 8) + getColumn(s)


fun main() {

    val stringList = File("src/main/kotlin/day5_2020", "input.txt").readLines()

    // Lösning del 1:

    println(stringList.map {
        getId(it)
    }.max())

    // Lösning del 2 - filtrerar bort alla värden som kommer i följd, kvar blir de där ett värde fattas.
    // Det saknade värdet är rätt svar

    stringList.map { getId(it) }.sorted().windowed(2, 1).filter { l -> (l[1] == (l[0] + 2)) }.forEach { println(it) }


}
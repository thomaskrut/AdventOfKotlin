/*************************** --- Day 22: Crab Combat --- ****************************
 *
 * Denna går ut på att räkna ut vinnaren och poängen efter en omgång av spelet Combat.
 * Det liknar svälta räv - varje spelare drar sitt översta kort, den som drar högst
 * får behålla båda korten. När en spelare inte har några kort kvar har denna förlorat
 * och vinnarens poäng beräknas utifrån kortens värde och position.
 *
 * Del 1 löses enkelt med en if/else-sats för varje spelomgång
 * och en lambda som räknar ut poängen.
 *
 * Del 2 hann jag inte lösa, mest för att jag tyckte att beskrivningen av de nya
 * reglerna var väldigt otydlig.
 *
 ************************************************************************************/

package day22_2020

import java.io.File

// Varje runda spelas genom att respektive spelares hand skickas in i metoden
// playRound(), som flyttar korten enligt spelets regler. Metoden anropas i en while-sats
// tills någon spelares hand är tom. Poängen räknas sedan ut i calculateScore().
fun playRound(player1: MutableList<Int>, player2: MutableList<Int>) {

    if (player1[0] > player2[0]) {
        player1.add(player1.removeFirst())
        player1.add(player2.removeFirst())
    } else if (player2[0] > player1[0]) {
        player2.add(player2.removeFirst())
        player2.add(player1.removeFirst())
    }

}

fun calculateScore(deck: MutableList<Int>): Int {

    return deck.mapIndexed { index, value -> value * (deck.size - index) }.sum()
}

fun main() {

    val stringList = File("src/main/kotlin/day22_2020", "input.txt").readLines()
    val player1 = stringList.subList(1, 26).map { it.toInt() }.toMutableList()
    val player2 = stringList.subList(28, 53).map { it.toInt() }.toMutableList()

    while (player1.isNotEmpty() && player2.isNotEmpty()) {
        playRound(player1, player2)

    }

    println(maxOf(calculateScore(player1), calculateScore(player2)))


}
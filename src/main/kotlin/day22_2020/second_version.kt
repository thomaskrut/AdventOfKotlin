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

// Jag snappade upp metoden fold() från Todd Ginsbergs lösning, eller i det här fallet
// foldIndexed(), och använder den som ett alternativ till min forEachIndexed() i kombination
// med sum() för att räkna ut poängen. Fold låter oss ackumulera värden från varje lambda-anrop
// vilket var användbart i det här fallet.
//
// Vidare inspirerad av Todd gjorde jag om min playRound()-funktion till en rekursiv
// funktion, om än med lite annan datastruktur än den Todd använder. Jag höll det enkelt
// med två listor där jag jämför första talet och sedan sorterar dem till rätt lista. Dock
// bytte jag ut min indexering med [] mot first() för ökad läsbarhet, samt refaktorerade ner
// kortförflyttningarna till var sin rad med listOf()

tailrec fun playRoundRecursive(player1: MutableList<Int>, player2: MutableList<Int>) {

    if (player1.isEmpty() || player2.isEmpty()) return
    if (player1.first() > player2.first()) {
        player1.addAll(listOf(player1.removeFirst(), player2.removeFirst()))
    } else {
        player2.addAll(listOf(player2.removeFirst(), player1.removeFirst()))
    }
    playRoundRecursive(player1, player2)

}

fun calculateScoreWithFold(deck: MutableList<Int>) = deck.foldIndexed(0) { index, i, value -> i + value * (deck.size - index) }


fun main() {

    val stringList = File("src/main/kotlin/day22_2020", "input.txt").readLines()
    val player1 = stringList.subList(1, 26).map { it.toInt() }.toMutableList()
    val player2 = stringList.subList(28, 53).map { it.toInt() }.toMutableList()

    playRoundRecursive(player1, player2)

    println(maxOf(calculateScoreWithFold(player1), calculateScoreWithFold(player2)))


}
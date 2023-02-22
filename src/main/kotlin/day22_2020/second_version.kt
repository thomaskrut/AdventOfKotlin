package day22_2020

import java.io.File

// Jag snappade upp metoden fold() från Todd Ginsbergs lösning, eller i det här fallet
// foldIndexed(), och använder den som ett alternativ till forEachIndexed() + sum())
// för att räkna ut poängen. Fold låter oss ackumulera värden från varje lambda-anrop
// vilket var användbart i det här fallet, och med foldIndexed() får man även tillgång
// till indexet för varje element, vilket behövdes här då kortets position i leken
// styr vilken poäng det får.
//
// Vidare inspirerad av Todd gjorde jag om min playRound()-funktion till en rekursiv
// funktion, om än med lite annan datastruktur än den Todd använder. Jag höll det enkelt
// med två listor där jag jämför de översta korten och sedan sorterar dem till rätt lista. Dock
// bytte jag ut min indexering med [] mot first() för ökad läsbarhet, samt refaktorerade ner
// kortförflyttningarna till var sin rad med hjälp av listOf()
//
// För att läsa in talen från filen hade jag tidigare hårdkodat antalet rader som läses in
// per spelare, men hittade nu metoden takeWhile() som plockar rader så länge predikatet
// uppfylls, samt dropWhile() som hoppar över rader så länge predikatet uppfylls.
//
// https://todd.ginsberg.com/post/advent-of-code/2020/day22/


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
    val player1 = stringList.drop(1).takeWhile { it.isNotBlank() }.map { it.toInt() }.toMutableList()
    val player2 = stringList.dropWhile {it != "Player 2:" }.drop(1).map { it.toInt() }.toMutableList()

    playRoundRecursive(player1, player2)

    println(maxOf(calculateScoreWithFold(player1), calculateScoreWithFold(player2)))


}
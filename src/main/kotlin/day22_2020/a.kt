package day22_2020

import java.io.File
import java.util.concurrent.atomic.AtomicInteger

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

    val stringList = File("src/day5_2020.day9_2021.main/kotlin/day22_2020/", "input.txt").readLines()
    val player1 = stringList.subList(1, 26).map { it.toInt() }.toMutableList()
    val player2 = stringList.subList(28, 53).map { it.toInt() }.toMutableList()

    while (player1.isNotEmpty() && player2.isNotEmpty()) {
        playRound(player1, player2)
        println(player1)
        println(player2)
    }

    println(calculateScore(player1))
    println(calculateScore(player2))

}
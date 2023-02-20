package day22_2020

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AKtTest {

    @Test
    fun playRound() {

        val player1 = mutableListOf(9, 2, 6, 3, 1)
        val player2 = mutableListOf(5, 8, 4, 7, 10)

        playRound(player1, player2)

        assertEquals(player1, mutableListOf(2, 6, 3, 1, 9, 5))
        assertEquals(player2, mutableListOf(8, 4, 7, 10))
        while (player1.isNotEmpty() && player2.isNotEmpty()) {
            playRound(player1, player2)
            println(player1)
            println(player2)
        }
        assertEquals(calculateScore(player2), 306)

    }
}
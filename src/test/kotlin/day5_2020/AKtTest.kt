package day5_2020

import getColumn
import getRow
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AKtTest {

    @Test
    fun getRow() {

        assertEquals(getRow("BFFFBBFRRR"), 70)
        assertEquals(getRow("FFFBBBFRRR"), 14)
        assertEquals(getRow("BBFFBBFRLL"), 102)

    }

    @Test
    fun getColumn() {

        assertEquals(getColumn("BFFFBBFRRR"), 7)
        assertEquals(getColumn("FFFBBBFRRR"), 7)
        assertEquals(getColumn("BBFFBBFRLL"), 4)


    }

    @Test
    fun getId() {
    }
}
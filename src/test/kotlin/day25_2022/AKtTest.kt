package day25_2022

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AKtTest {

    @Test
    fun testToDecimal() {

        assertEquals("1=-0-2".toDecimal(), 1747)
        assertEquals("12111".toDecimal(), 906)
        assertEquals("2=0=".toDecimal(), 198)
        assertEquals("20012".toDecimal(), 1257)
        assertEquals("1121-1110-1=0".toDecimal(), 314159265)

    }



    @Test
    fun testGetValueOfDigit() {
        assertEquals('2'.toDecimal(1), 2)
        assertEquals('1'.toDecimal(1),1)
        assertEquals('-'.toDecimal(3),-25)
    }

    @Test
    fun testToSnafu() {
        assertEquals(353L.toSnafu(), "1=-1=")
        assertEquals(1747L.toSnafu(), "1=-0-2")
    }


}
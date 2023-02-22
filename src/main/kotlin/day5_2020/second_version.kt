package day5_2020

import java.io.File

fun getIdByBinary(s: String): Int {

    return s.map {
        when(it) {
            'F','L' -> 0
            else -> 1
        }
    }.joinToString("").toInt(2)

}


fun main() {

    val stringList = File("src/main/kotlin/day5_2020", "input.txt").readLines()

    // Lösning del 1: Inspirerad av Todd Ginsbergs variant, där han konverterar bokstäverna till 1:or och 0:or
    // och sedan omvandlar det resulterande binära talet till decimalform. Upptäckten att bokstäverna i pusslet gick
    // att direktöversätta till binär talbas är naturligtvis hans upptäckt, men gav mig möjlighet att testa metoderna
    // joinToString och toInt(talbas) i min egen getId-metod som implementeras ovan. Lösningen eliminerar helt behovet
    // av mina två hjälpmetoder getRow() och getColumn()
    // https://todd.ginsberg.com/post/advent-of-code/2020/day5/

    println(stringList.map {
        getIdByBinary(it)
    }.max())

    // Lösning del 2 - min egen lösning hade inget sätt att skriva ut det värde som saknades i listan,
    // utan skrev istället ut de angränsande värdena. Jag hittade metoden first() i Todd Ginsbergs lösning, som precis
    // som filter tar ett predicate men returnerar den första instans då villkoret uppfylls. Jag använder mitt tidigare predicate
    // igen, och tar resultatet och adderar 1, så får jag rätt svar.
    // https://todd.ginsberg.com/post/advent-of-code/2020/day5/

    println(stringList.map { getIdByBinary(it) }.sorted().windowed(2, 1).first { l -> (l[1] == (l[0] + 2)) }.min() + 1)


}
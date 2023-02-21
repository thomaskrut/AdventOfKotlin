import day20_2022.Element
import kotlin.math.abs

fun MutableList<Element>.getNthNumberAfterValue(n: Int, value: Long): Long {
    var index = this.indexOf(this.first { e -> e.value == value })

    repeat(n) {
        index++
        if (index == this.size) index = 0
    }

    return this[index].value
}

fun MutableList<Element>.move(index: Int) {

    if (this[index].value == 0L) return
    var currentIndex = index
    var elementToMove: Element
    var newIndex: Int

    val n = (abs(this[index].value.toDouble()).toLong() % (this.size.toLong() - 1)).toInt()

    val moveOneStep = if (this[index].value > 0L) 1 else -1

    repeat(n) {

        elementToMove = this.removeAt(currentIndex)
        newIndex = currentIndex + moveOneStep



        if (moveOneStep == 1 && newIndex > this.size) {

            newIndex = 1
        }

        if (moveOneStep == 1 && newIndex == this.size) {

            newIndex = 0
        }


        if (moveOneStep == -1 && newIndex < 0) {

            newIndex = this.size - 1
        }

        if (moveOneStep == -1 && newIndex == 0) {

            newIndex = this.size
        }

        this.add(newIndex, elementToMove)

        currentIndex = newIndex

    }


}

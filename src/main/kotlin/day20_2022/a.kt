import kotlin.math.abs

fun MutableList<Int>.move(index: Int) : {

    var currentIndex = index
    var numberToMove : Int
    var newIndex : Int


    val moveOneStep = if (this[index] > 0) +1 else -1

    repeat(abs(this[index].toDouble()).toInt()) {

        numberToMove  = this.removeAt(currentIndex)
        newIndex = currentIndex + moveOneStep

        if (newIndex >= this.size) {
            newIndex = newIndex - this.size - 1
        }

        if (newIndex < 0) {
            newIndex += this.size + 1
        }

        this.add(newIndex, numberToMove)

        currentIndex = newIndex

    }



}




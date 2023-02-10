

fun MutableList<Int>.move(index: Int, steps : Int = index) {

    val numberToMove = this.removeAt(index)

    var newIndex = index + numberToMove

    if (newIndex >= this.size) {
        newIndex = newIndex - this.size - 1
    }

    if (newIndex < 0) {
        newIndex = this.size + 1 + newIndex
        println(newIndex)
    }

    this.add(newIndex, numberToMove)

}




import java.io.File

object Minerals {

    var ore = 0
    var clay = 0
    var obsidian = 0
    var geode = 0

    fun init() {
        ore = 0
        clay = 0
        obsidian = 0
        geode = 0
    }
}

object Blueprint {

    var oreForClayRobot = 0
    var oreForObsidianRobot = 0
    var clayForObsidianRobot = 0
    var oreForGeodeRobot = 0
    var obsidianForGeodeRobot = 0

}

fun main() {

    val stringList = File("src/main/kotlin/day19_2022", "input.txt").readLines()
    println()

}

interface Robot {
    fun mine(): Unit
    fun manufacture(): Robot?
}

class OreRobot : Robot {
    override fun mine() {
        Minerals.ore++
    }

    override fun manufacture(): Robot {
        return ClayRobot()
    }

}

class ClayRobot : Robot {
    override fun mine() {
        Minerals.clay++
    }

    override fun manufacture(): Robot {
        return ObsidianRobot()
    }

}

class ObsidianRobot : Robot {
    override fun mine() {
        Minerals.obsidian++
    }

    override fun manufacture(): Robot {
        return GeodeRobot()
    }

}

class GeodeRobot : Robot {
    override fun mine() {
        Minerals.geode++
    }

    override fun manufacture(): Robot? {
        return null
    }

}
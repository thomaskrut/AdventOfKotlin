import java.io.File
import java.util.*

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



    for ((n, s) in stringList.withIndex()) {

        var currentBlueprint = n +1

        var scan = Scanner(s)

        while(!scan.hasNextInt()) {
            scan.next()
        }
        scan.nextInt()

        while(!scan.hasNextInt()) {
            scan.next()
        }
        Blueprint.oreForClayRobot = scan.nextInt()

        while(!scan.hasNextInt()) {
            scan.next()
        }
        Blueprint.oreForObsidianRobot = scan.nextInt()

        while(!scan.hasNextInt()) {
            scan.next()
        }
        Blueprint.clayForObsidianRobot = scan.nextInt()

        while(!scan.hasNextInt()) {
            scan.next()
        }
        Blueprint.oreForGeodeRobot = scan.nextInt()

        while(!scan.hasNextInt()) {
            scan.next()
        }
        Blueprint.obsidianForGeodeRobot = scan.nextInt()

    }


    println(Blueprint.oreForClayRobot)
    println(Blueprint.oreForObsidianRobot)
    println(Blueprint.clayForObsidianRobot)
    println(Blueprint.oreForGeodeRobot)
    println(Blueprint.obsidianForGeodeRobot)

}

interface Robot {
    fun mine(): Unit
    fun manufacture(): Robot?
}

class OreRobot : Robot {
    override fun mine() {
        Minerals.ore++
    }

    override fun manufacture(): Robot? {
        return if (Minerals.ore >= Blueprint.oreForClayRobot) ClayRobot() else null
    }

}

class ClayRobot : Robot {
    override fun mine() {
        Minerals.clay++
    }

    override fun manufacture(): Robot? {
        return if (Minerals.ore >= Blueprint.oreForObsidianRobot && Minerals.clay >= Blueprint.clayForObsidianRobot) ObsidianRobot() else null
    }

}

class ObsidianRobot : Robot {
    override fun mine() {
        Minerals.obsidian++
    }

    override fun manufacture(): Robot? {
        return if (Minerals.ore >= Blueprint.oreForGeodeRobot && Minerals.clay >= Blueprint.obsidianForGeodeRobot) GeodeRobot() else null
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
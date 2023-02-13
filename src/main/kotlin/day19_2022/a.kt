package day19_2022

import java.io.File
import java.util.*

object Minerals {

    var ore = 0
    var clay = 0
    var obsidian = 0
    var geode = 0

    fun clear() {
        ore = 0
        clay = 0
        obsidian = 0
        geode = 0
    }
}

object Blueprint {

    var currentBlueprint = 0
    var oreForClayRobot = 0
    var oreForObsidianRobot = 0
    var clayForObsidianRobot = 0
    var oreForGeodeRobot = 0
    var obsidianForGeodeRobot = 0

}

fun main() {

    val stringList = File("src/main/kotlin/day19_2022", "inputtest.txt").readLines()

    var total = 0

    for ((n, s) in stringList.withIndex()) {

        Minerals.clear()

        Blueprint.currentBlueprint = n + 1

        var scan = Scanner(s)

        while (!scan.hasNextInt()) {
            scan.next()
        }
        scan.nextInt()

        while (!scan.hasNextInt()) {
            scan.next()
        }
        Blueprint.oreForClayRobot = scan.nextInt()


        while (!scan.hasNextInt()) {
            scan.next()
        }
        Blueprint.oreForObsidianRobot = scan.nextInt()

        while (!scan.hasNextInt()) {
            scan.next()
        }
        Blueprint.clayForObsidianRobot = scan.nextInt()

        while (!scan.hasNextInt()) {
            scan.next()
        }
        Blueprint.oreForGeodeRobot = scan.nextInt()

        while (!scan.hasNextInt()) {
            scan.next()
        }
        Blueprint.obsidianForGeodeRobot = scan.nextInt()

        var max = 0

        repeat(300000) {

            Minerals.clear()

            //println(Minerals.geode)

            val robots = mutableListOf<Robot>()

            robots.add(OreRobot())

            repeat(24) {

                var robot = manufacture()

                for (r in robots) {

                    r.mine()

                }

                if (robot != null) robots.add(robot)

            }

            if (Minerals.geode > max) max = Minerals.geode

        }

        print("${Blueprint.currentBlueprint}: ")
        println(max)
        total = (total + (Blueprint.currentBlueprint * max))

    }
    println()
    println(total)


}

fun manufacture(): Robot? {

    return if (Minerals.ore >= Blueprint.oreForGeodeRobot && Minerals.obsidian >= Blueprint.obsidianForGeodeRobot) GeodeRobot()
    else if (Minerals.ore >= Blueprint.oreForObsidianRobot && Minerals.clay >= Blueprint.clayForObsidianRobot) ObsidianRobot()
        else if (Minerals.ore >= Blueprint.oreForClayRobot) ClayRobot() else null

}

interface Robot {
    fun mine()

}

class OreRobot : Robot {
    override fun mine() {
        Minerals.ore++

    }



}

class ClayRobot : Robot {

    init {
        Minerals.ore = Minerals.ore - Blueprint.oreForClayRobot

    }

    override fun mine() {
        Minerals.clay++

    }



}

class ObsidianRobot : Robot {

    init {
        Minerals.ore = Minerals.ore - Blueprint.oreForObsidianRobot
        Minerals.clay = Minerals.clay - Blueprint.clayForObsidianRobot

    }

    override fun mine() {
        Minerals.obsidian++

    }



}

class GeodeRobot : Robot {

    init {
        Minerals.ore = Minerals.ore - Blueprint.oreForGeodeRobot
        Minerals.obsidian = Minerals.obsidian - Blueprint.obsidianForGeodeRobot

    }

    override fun mine() {
        Minerals.geode++

    }



}
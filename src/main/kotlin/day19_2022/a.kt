package day19_2022

import java.io.File
import java.util.*
import kotlin.math.min

class Minerals {

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

    override fun toString() : String {
        return "$ore $clay $obsidian $geode"
    }

}

class Blueprint {

    var currentBlueprint = 0
    var oreForClayRobot = 0
    var oreForObsidianRobot = 0
    var clayForObsidianRobot = 0
    var oreForGeodeRobot = 0
    var obsidianForGeodeRobot = 0

    override fun toString() : String {
        return "$currentBlueprint $oreForClayRobot $oreForObsidianRobot $clayForObsidianRobot $oreForGeodeRobot $obsidianForGeodeRobot"
    }

}

fun main() {

    val stringList = File("src/main/kotlin/day19_2022", "inputtest.txt").readLines()

    var total = 0

    for ((n, s) in stringList.withIndex()) {

        val minerals = Minerals()

        val blueprint = Blueprint()

        blueprint.currentBlueprint = n + 1

        var scan = Scanner(s)

        while (!scan.hasNextInt()) {
            scan.next()
        }
        scan.nextInt()

        while (!scan.hasNextInt()) {
            scan.next()
        }
        blueprint.oreForClayRobot = scan.nextInt()


        while (!scan.hasNextInt()) {
            scan.next()
        }
        blueprint.oreForObsidianRobot = scan.nextInt()

        while (!scan.hasNextInt()) {
            scan.next()
        }
        blueprint.clayForObsidianRobot = scan.nextInt()

        while (!scan.hasNextInt()) {
            scan.next()
        }
        blueprint.oreForGeodeRobot = scan.nextInt()

        while (!scan.hasNextInt()) {
            scan.next()
        }
        blueprint.obsidianForGeodeRobot = scan.nextInt()

        var max = 0

        println(minerals.toString())
        println(blueprint.toString())

        repeat(300000) {

            minerals.clear()

            //println(Minerals.geode)

            val robots = mutableListOf<Robot>()

            robots.add(OreRobot())

            repeat(24) {

                var robot = manufacture(minerals, blueprint)

                for (r in robots) {

                    r.mine(minerals)

                }

                if (robot != null) robots.add(robot)

            }

            if (minerals.geode > max) max = minerals.geode

        }

        print("${blueprint.currentBlueprint}: ")
        println(max)
        total = (total + (blueprint.currentBlueprint * max))

    }
    println()
    println(total)


}

fun manufacture(minerals : Minerals, blueprint: Blueprint): Robot? {

    return if (minerals.ore >= blueprint.oreForGeodeRobot && minerals.obsidian >= blueprint.obsidianForGeodeRobot) GeodeRobot(minerals, blueprint)
    else if (minerals.ore >= blueprint.oreForObsidianRobot && minerals.clay >= blueprint.clayForObsidianRobot) ObsidianRobot(minerals, blueprint)
        else if ((1..5).random() == 1 && minerals.ore >= blueprint.oreForClayRobot) ClayRobot(minerals, blueprint) else null

}

interface Robot {
    fun mine(minerals: Minerals)

}

class OreRobot : Robot {
    override fun mine(minerals: Minerals) {
        minerals.ore++

    }



}

class ClayRobot(minerals : Minerals, blueprint: Blueprint) : Robot {

    init {
        minerals.ore = minerals.ore - blueprint.oreForClayRobot

    }

    override fun mine(minerals: Minerals) {
        minerals.clay++

    }



}

class ObsidianRobot(minerals : Minerals, blueprint: Blueprint) : Robot {

    init {
        minerals.ore = minerals.ore - blueprint.oreForObsidianRobot
        minerals.clay = minerals.clay - blueprint.clayForObsidianRobot

    }

    override fun mine(minerals : Minerals) {
        minerals.obsidian++

    }



}

class GeodeRobot(minerals : Minerals, blueprint: Blueprint) : Robot {

    init {
        minerals.ore = minerals.ore - blueprint.oreForGeodeRobot
        minerals.obsidian = minerals.obsidian - blueprint.obsidianForGeodeRobot

    }

    override fun mine(minerals : Minerals) {
        minerals.geode++

    }



}
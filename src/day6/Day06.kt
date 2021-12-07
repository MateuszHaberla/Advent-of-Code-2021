package day6

import readInput

typealias Fish = LongArray

fun main() {

    fun List<Int>.simulationOf(days: Int): Int {
        var allFishes: List<Int> = this
        for (day in 0 until days) {
            val count: Int = allFishes.count { it == 0 }
            val babyFishes: List<Int> = List(count) { 8 }
            val decrementedDays: List<Int> = allFishes.map { if (it != 0) it.dec() else 6 }

            allFishes = decrementedDays.plus(babyFishes)
        }
        return allFishes.size
    }

    fun List<Int>.simulationOfPartTwo(days: Int): Long {
        var fish: LongArray = sortFishByDay()

        (0 until days).forEach { _ ->
            fish = fishAmountAfterDay(fish)
        }

        return fish.sum()
    }

    fun part1(input: List<Int>): Int = input.simulationOf(days = 80)

    fun part2(input: List<Int>): Long = input.simulationOfPartTwo(days = 256)

    fun List<String>.toInt(): List<Int> = this.flatMap { it.split(",") }.map { it.toInt() }

// test if implementation meets criteria from the description, like:
    val testInput: List<Int> = readInput(name = "Day06Input", directory = "day6").toInt()
    println(part1(testInput))
    check(part1(testInput) == 349549)

    val input: List<Int> = readInput(name = "Day06Input", directory = "day6").toInt()
    println(part2(input))
    check(part2(input) == 1589590444365)
}

private fun LongArray.reproduceBabyFish(firstNumber: Long): Fish = apply { this[8] = firstNumber }

private fun LongArray.takeMotherToNextRound(firstNumber: Long): Fish = apply { this[6] += firstNumber }

private fun LongArray.makeOneDayOlder(dayFish: LongArray): Fish = apply {
    (0 until 8).forEach { day ->
        this[day] = dayFish[day + 1]
    }
}

private fun fishAmountAfterDay(dayFish: LongArray): Fish {
    val fish = LongArray(9)
    val firstNumber = dayFish.first()

    return fish.makeOneDayOlder(dayFish)
        .reproduceBabyFish(firstNumber)
        .takeMotherToNextRound(firstNumber)
}

private fun List<Int>.sortFishByDay(): Fish {
    val sortedByDay = LongArray(9)

    (0 until 9).forEach { day ->
        sortedByDay[day] = this.count { it == day }.toLong()
    }

    return sortedByDay
}
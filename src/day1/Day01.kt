package day1

import readInput

fun main() {
    fun part1(input: List<Int>): Int = input
        .zipWithNext { previous: Int, next: Int -> previous < next }
        .count { it }

    fun part2(input: List<Int>): Int = part1(input.windowed(size = 3).map { it.sum() })

// test if implementation meets criteria from the description, like:
    val testInput : List<Int> = readInput(name = "Day01Input", directory = "day1").mapToInt()
    check(part1(testInput) == 1316)

    val input: List<Int> = readInput(name = "Day01Input", directory = "day1").mapToInt()
    println(part1(input))
    println(part2(input))
}

private fun List<String>.mapToInt(): List<Int> = this.map { it.toInt() }
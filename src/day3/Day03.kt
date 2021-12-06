package day3

import readInput

fun main() {
    fun String.inv() = if (this == "0") 1 else 0

    fun getMostCommonNumber(ones: Int, zeroes: Int, mostCommon: String): Int =
        if (zeroes > ones) mostCommon.toInt() else (mostCommon.inv())

    fun getGammaRate(ones: Int, zeros: Int) = getMostCommonNumber(ones, zeros, "0")
    fun getEpsilonRate(ones: Int, zeros: Int) = getMostCommonNumber(ones, zeros, "1")

    fun part1(input: List<String>): Int {
        val bytesLength = input.first().length
        var gammaRate = ""
        var epsilonRate = ""
        for (byte in 0 until bytesLength) {
            val ones = input.count { it[byte] == '1' }
            val zeros = input.size - ones

            gammaRate += getGammaRate(ones, zeros)
            epsilonRate += getEpsilonRate(ones, zeros)
        }

        return gammaRate.toInt(2) * epsilonRate.toInt(2)
    }

    fun countLifeSupportRating(input: List<String>, mostCommon: String): Int {
        val bytesLength = input.first().length
        var numbersFilter = ""

        for (byte in 0 until bytesLength) {
            val numbersFiltered = input.filter { it.startsWith(numbersFilter) }
            val ones = numbersFiltered.count { it[byte] == '1' }
            val zeroes = numbersFiltered.size - ones

            if (zeroes + ones != 1)
                numbersFilter += getMostCommonNumber(ones, zeroes, mostCommon)
            else
                numbersFilter = numbersFiltered.first()
        }

        return numbersFilter.toInt(2)
    }

    fun getOxygenGeneratorRating(input: List<String>): Int = countLifeSupportRating(input, "1")

    fun getCO2ScrubberRating(input: List<String>): Int = countLifeSupportRating(input, "0")

    fun part2(input: List<String>): Int = getOxygenGeneratorRating(input) * getCO2ScrubberRating(input)

// test if implementation meets criteria from the description, like:
    val testInput: List<String> = readInput(name = "Day03Input", directory = "day3")
    println(part1(testInput))
    check(part1(testInput) == 2954600)

    val input: List<String> = readInput(name = "Day03Input", directory = "day3")
    println(part2(input))
    check(part2(input) == 1662846)
}
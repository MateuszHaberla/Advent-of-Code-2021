package day2

import day2.CommandType.DOWN
import day2.CommandType.FORWARD
import day2.CommandType.UP
import readInput

fun main() {
    fun commandOf(it: String): Command {
        val split = it.split(" ")
        val type: CommandType = CommandType.valueOf(split[0].uppercase())
        val value = split[1].toInt()
        return Command(type = type, value = value)
    }

    fun calculatePosition(command: Command, position: Position) =
        when (command.type) {
            DOWN -> position.depth += command.value
            UP -> position.depth -= command.value
            FORWARD -> position.horizontal += command.value
        }

    fun calculatePositionPart2(command: Command, position: PositionPart2) =
        when (command.type) {
            DOWN -> position.aim += command.value
            UP -> position.aim -= command.value
            FORWARD -> {
                position.horizontal += command.value
                position.depth += position.aim * command.value
            }
        }

    fun part1(input: List<String>): Int {
        val position = Position(0, 0)
        input.map { commandOf(it) }
            .map { calculatePosition(it, position) }

        return position.horizontal * position.depth
    }

    fun part2(input: List<String>): Int {
        val position = PositionPart2(0, 0, 0)
        input.map { commandOf(it) }
            .map { calculatePositionPart2(it, position) }

        return position.horizontal * position.depth
    }

// test if implementation meets criteria from the description, like:
    val testInput: List<String> = readInput(name = "Day02Input", directory = "day2")
    println(part1(testInput))
    check(part1(testInput) == 1524750)

    val input: List<String> = readInput(name = "Day02Input", directory = "day2")
    println(part2(input))
}

enum class CommandType { FORWARD, DOWN, UP }

data class Position(var horizontal: Int, var depth: Int)
data class PositionPart2(var horizontal: Int, var depth: Int, var aim: Int)
data class Command(val type: CommandType, val value: Int)
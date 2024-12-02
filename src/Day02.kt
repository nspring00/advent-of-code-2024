import kotlin.math.sign


fun main() {
    fun parseInput(input: List<String>): List<List<Int>> = input.map { line ->
        line.split(" ").map { it.toInt() }
    }

    fun isSafe(report: List<Int>): Boolean {
        val sign = (report[1] - report[0]).sign
        return report.windowed(2, 1).all { (a, b) -> (sign * (b - a) >= 1) and (sign * (b - a) <= 3) }
    }

    fun part1(input: List<List<Int>>): Int = input.count(::isSafe)

    fun part2(input: List<List<Int>>): Int = input.count { report ->
        if (isSafe(report)) return@count true
        report.indices.any { i -> isSafe(report.filterIndexed { index, _ -> index != i }) }
    }

    val testInput = parseInput(readInput("Day02_test"))
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = parseInput(readInput("Day02"))
    val result1 = part1(input)
    val result2 = part2(input)
    println(result1)
    println(result2)

    check(result1 == 282)
    check(result2 == 349)
}

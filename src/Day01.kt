import kotlin.math.abs


fun main() {
    fun parseInput(input: List<String>): Pair<List<Int>, List<Int>> = input.map { line ->
        val (a, b) = line.split("   ").map { it.toInt() }
        a to b
    }.unzip()

    fun part1(input: Pair<List<Int>, List<Int>>): Int {
        val (list1, list2) = input
        return list1.sorted().zip(list2.sorted()).sumOf { (n, m) -> abs(n - m) }
    }

    fun part2(input: Pair<List<Int>, List<Int>>): Int {
        val counts = input.second.groupingBy { it }.eachCount()
        return input.first.sumOf { it * (counts[it] ?: 0) }
    }

    val testInput = parseInput(readInput("Day01_test"))
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = parseInput(readInput("Day01"))
    val result1 = part1(input)
    val result2 = part2(input)
    println(result1)
    println(result2)

    check(result1 == 1151792)
    check(result2 == 21790168)
}

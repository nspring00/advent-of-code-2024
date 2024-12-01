import kotlin.math.abs

typealias ParsedInput = Pair<List<Int>, List<Int>>

fun main() {
    fun parseInput(input: List<String>): ParsedInput {
        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()
        for (line in input) {
            val split = line.split("   ")
            list1.add(split[0].toInt())
            list2.add(split[1].toInt())
        }
        return Pair(list1, list2)
    }

    fun part1(input: ParsedInput): Int {
        val list1 = input.first.sorted()
        val list2 = input.second.sorted()
        return list1.zip(list2).sumOf { (n, m) -> abs(n - m) }
    }

    fun part2(input: ParsedInput): Int {
        val counts = input.second.groupingBy { it }.eachCount()
        return input.first.sumOf { it * (counts[it] ?: 0) }
    }

    val testInput = parseInput(readInput("Day01_test"))
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = parseInput(readInput("Day01"))
    part1(input).println()
    part2(input).println()
}

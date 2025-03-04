data class Input07(val result: Long, val operators: List<Long>)

fun main() {
    val operations1 = listOf<(Long, Long) -> Long>(
        { x, y -> x + y },
        { x, y -> x * y },
    )

    val operations2 = listOf<(Long, Long) -> Long>(
        { x, y -> x + y },
        { x, y -> x * y },
        { x, y -> "$x$y".toLong() },
    )

    fun parseInput(input: List<String>): List<Input07> = input.map { line ->
        val result = line.substringBefore(':').toLong()
        val operators = line.substringAfter(':').trim().split(" ").map { it.trim().toLong() }
        return@map Input07(result, operators)
    }

    fun checkCalibration(input: List<Input07>, operations: List<(Long, Long) -> Long>): Long =
        input.filter { (result, operators) ->
            val potentialNums = operators.drop(1).fold(listOf(operators[0])) { acc, num2 ->
                acc.flatMap { num1 -> operations.map { f -> f(num1, num2) } }
            }
            return@filter potentialNums.contains(result)
        }.sumOf { it.result }

    fun part1(input: List<Input07>): Long = checkCalibration(input, operations1)

    fun part2(input: List<Input07>): Long = checkCalibration(input, operations2)

    val testInput = parseInput(readInput("Day07_test"))
    check(part1(testInput) == 3749L)
    check(part2(testInput) == 11387L)

    val input = parseInput(readInput("Day07"))
    val result1 = part1(input)
    val result2 = part2(input)
    println(result1)
    println(result2)

    check(result1 == 7579994664753L)
    check(result2 == 438027111276610L)
}

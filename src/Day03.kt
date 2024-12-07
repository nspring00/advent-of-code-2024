fun main() {
    fun part1(input: List<String>): Int =
        input.flatMap { line ->
            """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
                .findAll(line)
                .map { it.groupValues[1].toInt() to it.groupValues[2].toInt() }
        }.sumOf { it.first * it.second }

    fun part2(input: List<String>): Int =
        """(mul\((\d{1,3}),(\d{1,3})\))|(do\(\))|(don't\(\))""".toRegex()
            .findAll(input.joinToString("\n"))
            .fold(true to 0) { (canSum, ans), match ->
                when (match.value) {
                    "do()" -> true to ans
                    "don't()" -> false to ans
                    else -> canSum to ans + if (canSum) match.groupValues[2].toInt() * match.groupValues[3].toInt() else 0
                }
            }.second

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
    check(part2(testInput) == 48)

    val input = readInput("Day03")
    val result1 = part1(input)
    val result2 = part2(input)
    println(result1)
    println(result2)

    check(result1 == 171183089)
    check(result2 == 63866497)
}

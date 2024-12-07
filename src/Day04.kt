import kotlin.streams.toList

fun main() {
    val xmasChars = "XMAS".toList()
    val masChars = "MAS".toList()

    fun isSequence(input: List<String>, chars: Iterable<Char>, initR: Int, initC: Int, dr: Int, dc: Int): Boolean {
        var r = initR
        var c = initC
        for (char in chars) {
            if (r < 0 || r >= input.size || c < 0 || c >= input[r].length || input[r][c] != char) return false
            r += dr
            c += dc
        }
        return true
    }

    val directions = listOf(
        (-1 to -1),
        (-1 to 0),
        (-1 to 1),
        (0 to -1),
        (0 to 1),
        (1 to -1),
        (1 to 0),
        (1 to 1),
    )

    fun isXmasCross(input: List<String>, r: Int, c: Int): Boolean =
        (isSequence(input, masChars, r - 1, c - 1, 1, 1) || isSequence(input, masChars, r + 1, c + 1, -1, -1)) &&
                (isSequence(input, masChars, r - 1, c + 1, 1, -1) || isSequence(input, masChars, r + 1, c - 1, -1, 1))

    fun part1(input: List<String>): Int = input.withIndex().sumOf { (r, line) ->
        line.indices.sumOf { c ->
            directions.count { (dr, dc) -> isSequence(input, xmasChars, r, c, dr, dc) }
        }
    }

    fun part2(input: List<String>): Int = input.withIndex().sumOf { (r, line) ->
        line.indices.count { c -> isXmasCross(input, r, c) }
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = readInput("Day04")
    val result1 = part1(input)
    val result2 = part2(input)
    println(result1)
    println(result2)

    check(result1 == 2447)
    check(result2 == 1868)
}

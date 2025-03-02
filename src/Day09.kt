fun main() {
    fun parseInput(input: List<String>): List<Int> = input.first().map { it.code - '0'.code }

    fun part1(input: List<Int>): Long {
        val disk = ArrayList<Long>(input.sum())
        var isFile = true
        var diskSpaceCount = 0L
        input.forEach { s ->
            if (isFile) {
                for (asf in 0 until s) {
                    disk.add(diskSpaceCount)
                }
                diskSpaceCount++
            } else {
                for (asf in 0 until s) {
                    disk.add(-1)
                }
            }
            isFile = !isFile
        }

//        val asdf = disk.map { if (it == -1) '.' else (it + '0'.code).toChar() }.joinToString("")
//        println(asdf)

        var i = 0
        var j = disk.size - 1
        var result = 0L
        var k = 0L

        while (i <= j) {
            if (disk[i] == -1L) {
                if (disk[j] == -1L) {
                    j--
                    continue
                }
                result += k * disk[j--]
                i++
            } else {
                result += k * disk[i++]
            }
            k++
        }

        return result
    }

    fun part2(input: List<Int>): Int = -1

    val testInput = parseInput(readInput("Day09_test"))
    check(part1(testInput) == 1928L)
    check(part2(testInput) == -1)

    val input = parseInput(readInput("Day09"))
    val result1 = part1(input)
    val result2 = part2(input)
    println(result1)
    println(result2)

    check(result1 == 6421128769094L)
    check(result2 == -1)
}

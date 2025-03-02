data class Position08(val x: Int, val y: Int)

data class Input08(val width: Int, val height: Int, val antennas: Map<Char, List<Position08>>)

fun main() {
    fun parseInput(input: List<String>): Input08 =
        Input08(width = input[0].length, height = input.size, antennas = input.flatMapIndexed { y, line ->
            line.mapIndexed { x, c -> c to Position08(x = x, y = y) }
        }.groupBy({ it.first }, { it.second }).filter { it.key != '.' })

    fun part1(input: Input08): Int {
        val positions = mutableSetOf<Position08>()

        input.antennas.forEach { (_, antennas) ->
            antennas.forEach { a1 ->
                antennas.forEach inner@{ a2 ->
                    if (a1 == a2) return@inner
                    val dx = a1.x - a2.x
                    val dy = a1.y - a2.y
                    val pos1 = Position08(a1.x + dx, a1.y + dy)
                    if (pos1.x >= 0 && pos1.x < input.width && pos1.y >= 0 && pos1.y < input.height) {
                        positions.add(pos1)
                    }
                    val pos2 = Position08(a2.x - dx, a2.y - dy)
                    if (pos2.x >= 0 && pos2.x < input.width && pos2.y >= 0 && pos2.y < input.height) {
                        positions.add(pos2)
                    }
                }
            }
        }

        return positions.size
    }

    fun part2(input: Input08): Int = -1

    val testInput = parseInput(readInput("Day08_test"))
    val test1 = part1(testInput)
    check(test1 == 14)
    check(part2(testInput) == -1)

    val input = parseInput(readInput("Day08"))
    val result1 = part1(input)
    val result2 = part2(input)
    println(result1)
    println(result2)

    check(result1 == 413)
    check(result2 == -1)
}

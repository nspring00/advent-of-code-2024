typealias Input06 = Pair<Grid06, Position06>
typealias Grid06 = List<List<Boolean>>

data class Position06(val x: Int, val y: Int)

enum class Direction06(val dx: Int, val dy: Int) {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);
}

fun main() {


    fun parseInput(input: List<String>): Input06 {
        val startY = input.indexOfFirst { line -> line.contains('^') }
        val startX = input[startY].indexOf('^')
        val grid = input.map { line ->
            line.map { it == '#' }
        }
        return grid to Position06(x = startX, y = startY)
    }

    fun turnRight(dir: Direction06): Direction06 = when(dir) {
        Direction06.UP -> Direction06.RIGHT
        Direction06.RIGHT -> Direction06.DOWN
        Direction06.DOWN -> Direction06.LEFT
        Direction06.LEFT -> Direction06.UP
    }

    fun isInBounds(grid: Grid06, position06: Position06): Boolean =
        position06.y >= 0 && position06.y < grid.size && position06.x >= 0 && position06.x < grid[0].size

    fun move(position06: Position06, direction06: Direction06): Position06 =
        Position06(position06.x + direction06.dx, position06.y + direction06.dy)

    fun part1(grid: Grid06, startPos: Position06): Int {
        var pos = startPos.copy()
        var dir = Direction06.UP
        val visited = mutableSetOf(startPos)

        while (true) {
            val nextPos = move(pos, dir)
            if (!isInBounds(grid, nextPos)) {
                break
            }
            if (grid[nextPos.y][nextPos.x]) {
                dir = turnRight(dir)
            } else {
                pos = nextPos
                visited.add(pos)
            }
        }

        return visited.size
    }


    fun part2(grid: Grid06, startPos: Position06): Int =
        -1

    val testInput = parseInput(readInput("Day06_test"))
    check(part1(testInput.first, testInput.second) == 41)
    check(part2(testInput.first, testInput.second) == -1)

    val input = parseInput(readInput("Day06"))
    val result1 = part1(input.first, input.second)
    val result2 = part2(input.first, input.second)
    println(result1)
    println(result2)

    check(result1 == 4711)
    check(result2 == -1)
}

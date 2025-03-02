typealias Input06 = Pair<Grid06, Position06>
typealias Grid06 = List<List<Boolean>>

data class Position06(val x: Int, val y: Int)

enum class Direction06(val dx: Int, val dy: Int) {
    UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);
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

    fun turnRight(dir: Direction06): Direction06 = when (dir) {
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

    fun part2CheckLoop(grid: Grid06, obstacle: Position06, startPos: Position06, startDir: Direction06): Boolean {
        var pos = startPos.copy()
        var dir = startDir
        val visited = mutableSetOf(startPos to startDir)

        while (true) {
            val nextPos = move(pos, dir)
            if (!isInBounds(grid, nextPos)) {
                return false
            }
            if (grid[nextPos.y][nextPos.x] || nextPos == obstacle) {
                dir = turnRight(dir)
            } else {
                pos = nextPos
            }
            if (pos to dir in visited) {
                return true
            }
            visited.add(pos to dir)
        }
    }

    fun part2(grid: Grid06, startPos: Position06): Int {
        var pos = startPos.copy()
        var dir = Direction06.UP
        val obstacles = mutableSetOf<Position06>()

        while (true) {
            val nextPos = move(pos, dir)
            // It's important to start checking from startPos, because the new obstacle could alter the path up to this point.
            // Also, we need to (implicitly) check that nextPos is not already an obstacle to avoid duplicates.
            if (nextPos != startPos && part2CheckLoop(grid, nextPos, startPos, Direction06.UP)) {
                obstacles.add(nextPos)
            }
            if (!isInBounds(grid, nextPos)) {
                break
            }
            if (grid[nextPos.y][nextPos.x]) {
                dir = turnRight(dir)
            } else {
                pos = nextPos
            }
        }

        return obstacles.size
    }

    val testInput = parseInput(readInput("Day06_test"))
    check(part1(testInput.first, testInput.second) == 41)
    check(part2(testInput.first, testInput.second) == 6)

    val input = parseInput(readInput("Day06"))
    val result1 = part1(input.first, input.second)
    val result2 = part2(input.first, input.second)
    println(result1)
    println(result2)

    check(result1 == 4711)
    check(result2 == 1562)
}

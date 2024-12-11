fun main() {
    fun parseInput(input: List<String>): Pair<List<List<Int>>, Map<Int, List<Int>>> {
        val part1 = input.takeWhile { it.isNotBlank() }
        val part2 = input.dropWhile { it.isNotBlank() }.drop(1)

        val map = part1.groupBy ({ it.split("|")[0].toInt() }, {it.split("|")[1].toInt() })

        val lists = part2.map { list ->
            list.split(",").map { it.toInt() }
        }

        return lists to map
    }

    fun isOrdered(list: List<Int>, orders: Map<Int, List<Int>>): Boolean {
        val seen = mutableSetOf<Int>()
        for (value in list) {
            seen.add(value)
            for (prev in orders[value] ?: continue) {
                if (prev in seen) {
                    return false
                }
            }
        }
        return true
    }

    fun findOrdering(list: List<Int>, orders: Map<Int, List<Int>>): List<Int> {
        val result = mutableListOf<Int>()
        val visited = mutableSetOf<Int>()
        val temp = mutableListOf<Int>()

        fun dfs(node: Int): Boolean {
            if (node in temp) return false
            if (node in visited) return true

            temp.add(node)

            orders[node]?.filter { it in list }?.forEach { prev ->
                if (!dfs(prev)) return false
            }

            temp.removeLast()
            visited.add(node)
            result.add(node)
            return true
        }

        for (node in list) {
            if (node !in visited) {
                if (!dfs(node)) throw IllegalStateException()
            }
        }

        return result.reversed()
    }

    fun part1(lists: List<List<Int>>, orders: Map<Int, List<Int>>): Int =
        lists.filter { isOrdered(it, orders) }.sumOf { it[it.size.div(2)] }


    fun part2(lists: List<List<Int>>, orders: Map<Int, List<Int>>): Int =
        lists.filterNot { isOrdered(it, orders) }.map { findOrdering(it, orders) }.sumOf { it[it.size.div(2)] }

    val testInput = parseInput(readInput("Day05_test"))
    check(part1(testInput.first, testInput.second) == 143)
    check(part2(testInput.first, testInput.second) == 123)

    val input = parseInput(readInput("Day05"))
    val result1 = part1(input.first, input.second)
    val result2 = part2(input.first, input.second)
    println(result1)
    println(result2)

    check(result1 == 5964)
    check(result2 == 4719)
}

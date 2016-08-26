package lily

import lily.MathOperator.*

/**
 * @author tenwit
 */
class Puzzle(val leftValue1 : Int, val op : MathOperator, val leftValue2 : Int) {
    companion object Factory {
        fun create(): Puzzle {
            val value1 = (Math.random() * 20).toInt() + 1
            val value2 = (Math.random() * 20).toInt() + 1
            val op = values()[(Math.random() * values().size).toInt()]
            return when (op) {
                PLUS, TIMES -> Puzzle(value1, op, value2)
                MINUS -> Puzzle(value1 + value2, op, value2)
                DIV -> Puzzle(value1 * value2, op, value2)
            }
        }

        val None: Puzzle = Puzzle(0, PLUS, 0)
    }

    val rightValue = op.apply(leftValue1, leftValue2)

    override fun toString(): String {
        return "$leftValue1 ${op.symbol} $leftValue2 = $rightValue"
    }
}

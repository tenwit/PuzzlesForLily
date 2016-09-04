package lily

import lily.EquationElement.*
import lily.MathOperator.*

/**
 * @author tenwit
 */
class Puzzle(val leftValue1 : Int, val op : MathOperator, val leftValue2 : Int) {
    companion object Factory {
        fun create(): Puzzle {
            val value1 = (Math.random() * 20).toInt() + 1
            val value2 = (Math.random() * 20).toInt() + 1
            val op = MathOperator.values()[(Math.random() * MathOperator.values().size).toInt()]
            return when (op) {
                PLUS, TIMES -> Puzzle(value1, op, value2)
                MINUS -> Puzzle(value1 + value2, op, value2)
                DIV -> Puzzle(value1 * value2, op, value2)
            }
        }

        val None: Puzzle = Puzzle(0, PLUS, 0)
    }

    fun present(): String {
        val toPresent = StringBuilder()
        toPresent.append(if (obfuscatedPart == LEFT_VALUE1) "X" else leftValue1).append(" ")
        toPresent.append(if (obfuscatedPart == OPERATOR) "?" else op.symbol).append(" ")
        toPresent.append(if (obfuscatedPart == LEFT_VALUE2) "X" else leftValue2).append(" = ")
        toPresent.append(if (obfuscatedPart == RIGHT_VALUE) "X" else rightValue)
        return toPresent.toString()
    }

    fun submit(answer: String): Boolean {
        submittedAnswer = answer
        return isSolved
    }

    var submittedAnswer = ""
    val rightValue = op.apply(leftValue1, leftValue2)
    val obfuscatedPart = pickPartToObfuscate()
    val solution: String
        get() = when (obfuscatedPart) {
            LEFT_VALUE1 -> leftValue1.toString()
            OPERATOR -> op.symbol.toString()
            LEFT_VALUE2 -> leftValue2.toString()
            RIGHT_VALUE -> rightValue.toString()
        }

    val isSolved: Boolean
        get() {
            return when (obfuscatedPart) {
                LEFT_VALUE1 -> leftValue1.toString() == submittedAnswer
                OPERATOR -> op.isEquivalentTo(submittedAnswer)
                LEFT_VALUE2 -> leftValue2.toString() == submittedAnswer
                RIGHT_VALUE -> rightValue.toString() == submittedAnswer
            }
        }

    override fun toString(): String {
        return "$leftValue1 ${op.symbol} $leftValue2 = $rightValue"
    }

    private fun pickPartToObfuscate(): EquationElement {
        val choice = Math.random()
        return when {
            choice < 0.25 -> LEFT_VALUE1
            choice < 0.35 -> OPERATOR
            choice < 0.60 -> LEFT_VALUE2
            else -> RIGHT_VALUE
        }
    }
}

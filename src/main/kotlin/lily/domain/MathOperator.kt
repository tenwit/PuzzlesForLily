package lily.domain

/**
 * Mathematical operators that can be used to build up maths puzzles.
 * @author tenwit
 */
enum class MathOperator(val symbol : Char, val apply: Int.(Int) -> Int, vararg val equivalents : String) {
    PLUS('+', Int::plus, "+", "plus", "and", "add"),
    MINUS('-', Int::minus, "-", "minus", "less", "subtract", "take away"),
    TIMES('x', Int::times, "x", "*", "times", "multiply", "multiplied by", "by", "."),
    DIV('\u00F7', Int::div, "\u00F7", "/", "div", "divided by");

    companion object {
        fun parse(text: String) : MathOperator = MathOperator.values().single { it.equivalents.contains(text) }
        fun tryParse(text: String): MathOperator? = MathOperator.values().find { it.equivalents.contains(text) }
    }
}

package lily

/**
 * @author tenwit
 */
enum class MathOperator(val symbol : Char, val apply: Int.(Int) -> Int, vararg val equivalents : String) {
    PLUS('+', Int::plus, "+", "plus", "and", "add"),
    MINUS('-', Int::minus, "-", "minus", "less", "subtract", "take away"),
    TIMES('x', Int::times, "x", "*", "times", "multiply", "multiplied by", "by", "."),
    DIV('\u00F7', Int::div, "\u00F7", "/", "div", "divided by");

    fun isEquivalentTo(otherSymbol : String) : Boolean {
        return equivalents.contains(otherSymbol.toLowerCase())
    }
}

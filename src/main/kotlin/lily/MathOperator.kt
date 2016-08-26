package lily

/**
 * @author tenwit
 */
enum class MathOperator(val symbol : Char, val apply: Int.(Int) -> Int) {
    PLUS('+', Int::plus),
    MINUS('-', Int::minus),
    TIMES('x', Int::times),
    DIV('\u00F7', Int::div);
}

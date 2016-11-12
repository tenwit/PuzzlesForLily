package lily.console

import lily.domain.EquationElement
import lily.domain.Puzzle

/**
 * The console view for a {Puzzle}
 *
 * @author tenwit
 */
class PuzzleView(var puzzle: Puzzle) {
    override fun toString(): String {
        val toPresent = StringBuilder()
        toPresent.append(if (puzzle.obfuscatedPart == EquationElement.LEFT_VALUE1) "X" else puzzle.leftValue1).append(" ")
        toPresent.append(if (puzzle.obfuscatedPart == EquationElement.OPERATOR) "?" else puzzle.op.symbol).append(" ")
        toPresent.append(if (puzzle.obfuscatedPart == EquationElement.LEFT_VALUE2) "X" else puzzle.leftValue2).append(" = ")
        toPresent.append(if (puzzle.obfuscatedPart == EquationElement.RIGHT_VALUE) "X" else puzzle.rightValue)
        return toPresent.toString()
    }
}

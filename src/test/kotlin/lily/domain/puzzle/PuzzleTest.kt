package lily.domain.puzzle

import lily.domain.Puzzle
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

/**
 * Test the {Puzzle} class.
 *
 * @author tenwit
 */
class PuzzleTest {
    @Test
    fun correctAnswerSolvesPuzzle() {
        // Given
        val solution = puzzle.solution
        // When
        puzzle.submit(solution)
        // Then
        Assertions.assertThat(puzzle.isSolved).isTrue()
    }

    @Test
    fun incorrectAnswerDoesNotSolvePuzzle() {
        // Given
        val solution = puzzle.solution
        // When
        puzzle.submit(if (solution == "7") "6" else "7")
        // Then
        Assertions.assertThat(puzzle.isSolved).isFalse()
    }

    @Test
    fun newestAnswerIsUsedForSolvingPuzzle() {
        // Given
        val solution = puzzle.solution
        puzzle.submit(if (solution == "7") "6" else "7")
        Assertions.assertThat(puzzle.isSolved).isFalse()
        // When
        puzzle.submit(solution)
        // Then
        Assertions.assertThat(puzzle.isSolved).isTrue()
    }

    var puzzle = Puzzle.create()
}

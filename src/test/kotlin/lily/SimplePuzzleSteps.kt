package lily

import cucumber.api.java.en.When
import org.assertj.core.api.Assertions.assertThat

/**
 * @author tenwit
 */
class SimplePuzzleSteps {
    @When("^(\\p{Alpha}+) requests a new puzzle$")
    fun createPuzzleFor(actor: String): Unit {
        println("Hello $actor!")
        val puzzle = game.newPuzzle()
        assertThat(puzzle)
                .isNotNull()
                .isNotEqualTo(Puzzle.None)
    }

    @When("^a simple puzzle is presented to (\\p{Alpha}+)$")
    fun presentPuzzleTo(actor: String): Unit {
        assertThat(game.currentPuzzle.toString())
                .isNotEmpty()
                .isNotEqualTo(Puzzle.None.toString())
        println("$actor, your puzzle is ${game.currentPuzzle}")
    }

    val game = Game()
}

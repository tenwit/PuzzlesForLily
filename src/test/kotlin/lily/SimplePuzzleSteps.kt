package lily

import cucumber.api.java.en.When
import org.assertj.core.api.Assertions.assertThat

/**
 * // TODO class Javadoc
 *
 * @author tenwit
 */
class SimplePuzzleSteps {
    @When("^(\\p{Alpha}+) requests a new puzzle$")
    fun createPuzzleFor(actor: String): Unit {
        println("Hello $actor, here's your puzzle!")
    }

    @When("^a simple puzzle is presented to (\\p{Alpha}+)$")
    fun presentPuzzleTo(actor: String): Unit {
        var puzzle = String()
        assertThat(puzzle).isNotEmpty()
    }
}

package lily

import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.assertj.core.api.Assertions.assertThat
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

/**
 * @author tenwit
 */
class SimplePuzzleSteps {
    @When("^(\\p{Alpha}+) requests a new puzzle$")
    fun createPuzzleFor(actor: String) {
        println("Hello $actor!")
        assertThat(State.game.newPuzzle())
                .isNotNull()
                .isNotEqualTo(Puzzle.None)
    }

    @Then("^a simple puzzle is presented to (\\p{Alpha}+)$")
    fun assertPuzzleIsPresentedTo(actor: String) {
        assertThat(State.game.currentPuzzle.toString())
                .isNotEmpty()
                .isNotEqualTo(Puzzle.None.toString())
        println("$actor, your puzzle is ${State.game.currentPuzzle.present()}")
    }

    @Then("^the puzzle is an obfuscated equation$")
    fun assertPuzzleIsEquation() {
        assertThat(State.game.currentPuzzle.present())
                .matches("""[X\p{Digit}]+ [?+-x\u00f7] [X\p{Digit}]+ = [X\p{Digit}]+""")
                .isNotEqualTo(State.game.currentPuzzle)
    }

    @Then("^(\\p{Alpha}+) is prompted for an answer$")
    fun assertPromptIsShown(actor: String) {
        game.player = actor
        val originalOut = System.out
        try {
            val out = ByteArrayOutputStream()
            System.setOut(PrintStream(out))
            submitAnswer("")
            assertThat(out.toString()).contains(actor)
        } finally {
            System.setOut(originalOut)
        }
    }

    @Given("the game is waiting for an answer from (\\p{Alpha}+)")
    fun newGame(actor: String) {
        State.game.newPuzzle()
        game.player = actor
        submitAnswer("")
    }

    @When("^(?:\\p{Alpha}+) enters the (i?n?correct) answer$")
    fun answerPuzzle(answerType: String) {
        val isCorrect = answerType == "correct"
        submitAnswer(if (isCorrect) State.game.currentPuzzle.solution else "83f932")
    }

    @Then("^A congratulatory message is presented to (\\p{Alpha}+)$")
    fun assertResultIsSuccess(actor: String) {
        game.player = actor
        val originalOut = System.out
        try {
            val out = ByteArrayOutputStream()
            System.setOut(PrintStream(out))
            State.game.displayResult()
            assertThat(out.toString())
                    .contains(actor)
                    .contains("Well done")
        } finally {
            System.setOut(originalOut)
        }
    }

    @Then("^An encouraging message is presented to (\\p{Alpha}+)$")
    fun assertResultIsFailure(actor: String) {
        game.player = actor
        val originalOut = System.out
        try {
            val out = ByteArrayOutputStream()
            System.setOut(PrintStream(out))
            State.game.displayResult()
            assertThat(out.toString())
                    .contains(actor)
                    .contains("not quite")
        } finally {
            System.setOut(originalOut)
        }
    }

    private fun submitAnswer(answer: String) {
        val originalIn = System.`in`
        try {
            System.setIn(ByteArrayInputStream(answer.toByteArray()))
            State.game.getAnswer()
        } finally {
            System.setIn(originalIn)
        }
    }

    companion object State {
        val game = Game()
    }
}

package lily.console

import lily.domain.Puzzle
import lily.domain.Score

/**
 * @author tenwit
 */
class Game(var player: String = "player") {
    companion object App {
        @JvmStatic fun main(args: Array<String>) {
            val game = Game(if (args.isNotEmpty()) args[0] else "player")
            var keepPlaying = true
            while (keepPlaying) {
                game.newPuzzle()
                game.getAnswer()
                game.displayResult()
                keepPlaying = game.prompt()
            }
            println("Thanks for playing, ${game.player}! You finished the game with ${game.score}.")
        }
    }

    fun newPuzzle() : Puzzle {
        currentPuzzle = Puzzle.create()
        return currentPuzzle
    }

    fun getAnswer() {
        println("Ready, $player? Here's your puzzle:")
        println(PuzzleView(currentPuzzle))
        print("What's the answer, $player? ")
        currentPuzzle.submit(readLine() ?: "")
    }

    fun displayResult() {
        if (currentPuzzle.isSolved) {
            score.correct()
            println("Correct! Well done $player!")
        }
        else {
            score.incorrect()
            println("Ooo, not quite. Better luck next time $player!")
        }
    }

    fun prompt() : Boolean {
        println("You've got $score")
        print("Would you like to play again, $player?")
        val playAgain = (readLine() ?: "")
        return truthyStrings.contains(playAgain.toLowerCase())
    }

    var currentPuzzle = Puzzle.None
    var score = Score()
    val truthyStrings = arrayOf("yes", "true", "sure", "ok", "yep", "y", "")
}

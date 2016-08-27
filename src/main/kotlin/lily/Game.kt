package lily

/**
 * @author tenwit
 */
class Game(var player: String = "player") {
    companion object App {
        @JvmStatic fun main(args: Array<String>) {
            val game = Game(if (args.size > 0) args[0] else "player")
            game.newPuzzle()
            game.prompt()
            game.getAnswer()
            game.displayResult()
        }
    }

    fun newPuzzle() : Puzzle {
        currentPuzzle = Puzzle.create()
        return currentPuzzle
    }

    fun prompt() {
        println("Ready, $player? Here's your puzzle:")
        println(currentPuzzle.present())
        print("What's the answer, $player? ")
    }

    fun getAnswer() {
        currentPuzzle.submit(readLine() ?: "")
    }

    fun displayResult() {
        if (currentPuzzle.isSolved) println("Correct! Well done $player!")
        else println("Ooo, not quite. Would you like to try again, $player?")
    }

    var currentPuzzle : Puzzle = Puzzle.None
}

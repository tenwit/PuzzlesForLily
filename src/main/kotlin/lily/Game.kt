package lily

/**
 * @author tenwit
 */
class Game {
    fun newPuzzle() : Puzzle {
        currentPuzzle = Puzzle.create()
        return currentPuzzle
    }

    var currentPuzzle : Puzzle = Puzzle.None
}

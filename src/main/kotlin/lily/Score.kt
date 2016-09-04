package lily

/**
 * // TODO class Javadoc
 *
 * @author tenwit
 */
class Score {
    fun correct(points : Int = 1) {
        correct += points
    }

    fun incorrect(points: Int = 1) {
        incorrect += points
    }

    override fun toString(): String {
        return "$correct out of ${correct + incorrect}"
    }

    var correct = 0
    var incorrect = 0
}

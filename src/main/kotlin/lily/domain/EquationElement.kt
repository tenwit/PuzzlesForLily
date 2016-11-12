package lily.domain

/**
 * The types of elements in an equation.
 * TODO Rework into a parse tree.
 *
 * @author tenwit
 */
enum class EquationElement {
    LEFT_VALUE1,
    OPERATOR,
    LEFT_VALUE2,
    RIGHT_VALUE
}

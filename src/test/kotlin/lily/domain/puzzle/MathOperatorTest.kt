package lily.domain.puzzle

import lily.domain.MathOperator
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

/**
 * Unit tests for the {MathOperator} class.
 *
 * @see MathOperator
 * @author tenwit
 */
class MathOperatorTest {
    @Test
    fun tryingToParseInvalidTextGivesNull() {
        assertThat(MathOperator.tryParse("invalid")).isNull()
    }

    @Test
    fun parsingInvalidTextThrowsException() {
        assertThatThrownBy { MathOperator.parse("invalid") }.isNotNull()
    }

    class MathOperatorData(val text: String, val operator: MathOperator)

    val testData = listOf(
            MathOperatorData("+", MathOperator.PLUS),
            MathOperatorData("plus", MathOperator.PLUS),
            MathOperatorData("and", MathOperator.PLUS),
            MathOperatorData("add", MathOperator.PLUS),
            MathOperatorData("-", MathOperator.MINUS),
            MathOperatorData("minus", MathOperator.MINUS),
            MathOperatorData("less", MathOperator.MINUS),
            MathOperatorData("subtract", MathOperator.MINUS),
            MathOperatorData("take away", MathOperator.MINUS),
            MathOperatorData("x", MathOperator.TIMES),
            MathOperatorData("*", MathOperator.TIMES),
            MathOperatorData("times", MathOperator.TIMES),
            MathOperatorData("multiply", MathOperator.TIMES),
            MathOperatorData("multiplied by", MathOperator.TIMES),
            MathOperatorData("by", MathOperator.TIMES),
            MathOperatorData(".", MathOperator.TIMES),
            MathOperatorData("\u00F7", MathOperator.DIV),
            MathOperatorData("/", MathOperator.DIV),
            MathOperatorData("divided by", MathOperator.DIV),
            MathOperatorData("div", MathOperator.DIV)
    )

    @TestFactory
    fun parsing(): Collection<DynamicTest> = testData.map {
            dynamicTest("${it.text} should parse to ${it.operator}") {
                assertThat(MathOperator.parse(it.text)).isEqualTo(it.operator)
            }
        }

    @TestFactory
    fun tryParsing(): Collection<DynamicTest> = testData.map {
        dynamicTest("${it.text} should parse to ${it.operator}") {
            assertThat(MathOperator.tryParse(it.text)).isEqualTo(it.operator)
        }
    }
}

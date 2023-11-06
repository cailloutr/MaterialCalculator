package com.cailloutr.materialcalculator.domain

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.math.BigDecimal
import java.math.RoundingMode

class ExpressionEvaluatorTest {

    private lateinit var evaluator: ExpressionEvaluator

    @Test
    fun `Simple expression properly evaluated`() {
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(4.0),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(5.0),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.0),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(5.0),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(3.0),
            )
        )

        assertThat(evaluator.evaluate()).isEqualTo(4)
    }

    @Test
    fun `Expression with decimals properly evaluated`(){
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(4.5),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(5.5),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.5),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(5.5),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(3.5),
            )
        )

        assertThat(evaluator.evaluate()).isEqualTo(4.5)
    }

    @Test
    fun `Expression with parentheses properly evaluated`(){
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Parentheses(ParenthesesType.Opening),
                ExpressionPart.Number(4.5),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(5.5),
                ExpressionPart.Parentheses(ParenthesesType.Closing),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.5),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(5.5),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(3.5),
            )
        )

        assertThat(evaluator.evaluate()).isEqualTo(4.5)
    }

    @Test
    fun `Expression with nested parentheses properly evaluated`(){
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Parentheses(ParenthesesType.Opening),
                ExpressionPart.Parentheses(ParenthesesType.Opening),
                ExpressionPart.Number(4.5),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(5.5),
                ExpressionPart.Parentheses(ParenthesesType.Closing),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Parentheses(ParenthesesType.Opening),
                ExpressionPart.Number(3.5),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(5.5),
                ExpressionPart.Parentheses(ParenthesesType.Closing),
                ExpressionPart.Parentheses(ParenthesesType.Closing),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(3.5),
            )
        )

        val result = BigDecimal(evaluator.evaluate())
        val roundoff = result.setScale(3, RoundingMode.FLOOR)
        assertThat(roundoff.toDouble()).isEqualTo(-2.643)
    }

    @Test
    fun `Expression starting with a subtract symbol properly evaluated`(){
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(4.5),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(5.5),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.5),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(5.5),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(3.5),
            )
        )

        assertThat(evaluator.evaluate()).isEqualTo(-4.5)
    }

    @Test
    fun `Expression starting with a add symbol properly evaluated`(){
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(4.5),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(5.5),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.5),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(5.5),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(3.5),
            )
        )

        assertThat(evaluator.evaluate()).isEqualTo(4.5)
    }

    @Test
    fun `Expression with percentage symbol properly evaluated`(){
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(12.0),
                ExpressionPart.Op(Operation.PERCENT),
                ExpressionPart.Number(2.0),
            )
        )

        assertThat(evaluator.evaluate()).isEqualTo(0.24
        )
    }

    @Test
    fun `Expression with invalid factor throws runtime exception`(){
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(4.5),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(5.5),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.5),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(5.5),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(3.5),
            )
        )

        try {
            val result = evaluator.evaluate()
            Truth.assertWithMessage("Invalid part").fail()
        } catch (e: RuntimeException) {
            assertThat(e).hasMessageThat().isEqualTo("Invalid part")
        }
    }


}
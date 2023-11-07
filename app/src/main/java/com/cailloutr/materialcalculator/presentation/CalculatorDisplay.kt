package com.cailloutr.materialcalculator.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.cailloutr.materialcalculator.ui.theme.MaterialCalculatorTheme

@Composable
fun CalculatorDisplay(
    expression: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = expression,
            onValueChange = {},
            textStyle = TextStyle(
                fontSize = 80.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                textAlign = TextAlign.End
            ),
            maxLines = 1,
            singleLine = true,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun CalculatorDisplayPreview() {
    MaterialCalculatorTheme {
        Surface {
            CalculatorDisplay(expression = "4+4")
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CalculatorDisplayDarkPreview() {
    MaterialCalculatorTheme {
        Surface {
            CalculatorDisplay(expression = "4+4")
        }
    }
}
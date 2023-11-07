package com.cailloutr.materialcalculator.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.cailloutr.materialcalculator.ui.theme.MaterialCalculatorTheme

@Composable
fun CalculatorButton(
    action: CalculatorUiAction,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(
                when (action.highlightLevel) {
                    HighlightLevel.Neutral -> MaterialTheme.colorScheme.surfaceVariant
                    HighlightLevel.SemiHighlighted -> MaterialTheme.colorScheme.inverseSurface
                    HighlightLevel.Highlighted -> MaterialTheme.colorScheme.tertiary
                    HighlightLevel.StrongHighlighted -> MaterialTheme.colorScheme.primary
                }
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (action.text != null) {
            Text(
                text = action.text,
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
                color = when (action.highlightLevel) {
                    HighlightLevel.Highlighted -> MaterialTheme.colorScheme.onTertiary
                    HighlightLevel.Neutral -> MaterialTheme.colorScheme.onSurfaceVariant
                    HighlightLevel.SemiHighlighted -> MaterialTheme.colorScheme.inverseOnSurface
                    HighlightLevel.StrongHighlighted -> MaterialTheme.colorScheme.onPrimary
                }
            )
        } else {
            action.content()
        }
    }
}

@Preview
@Composable
fun CalculatorButtonPreview() {
    MaterialCalculatorTheme {
        LazyVerticalGrid(columns = GridCells.Fixed(4)) {
            items(CalculatorUiActions().calculatorActions) {
                CalculatorButton(action = it, modifier = Modifier, onClick = {})
            }
        }
    }
}
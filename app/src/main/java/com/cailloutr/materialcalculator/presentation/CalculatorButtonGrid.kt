package com.cailloutr.materialcalculator.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cailloutr.materialcalculator.domain.CalculatorAction
import com.cailloutr.materialcalculator.ui.theme.MaterialCalculatorTheme

@Composable
fun CalculatorButtonGrid(
    actions: List<CalculatorUiAction>,
    onAction: (CalculatorAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        userScrollEnabled = false,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(actions) {
            CalculatorButton(
                action = it,
                modifier = Modifier.aspectRatio(1f),
                onClick = { onAction(it.action) }
            )
        }
    }
}

@Preview
@Composable
fun CalculatorButtonGridPreview() {
    MaterialCalculatorTheme {
        CalculatorButtonGrid(actions = CalculatorUiActions().calculatorActions, onAction = {})
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CalculatorButtonGridDarkPreview() {
    MaterialCalculatorTheme {
        CalculatorButtonGrid(actions = CalculatorUiActions().calculatorActions, onAction = {})
    }
}
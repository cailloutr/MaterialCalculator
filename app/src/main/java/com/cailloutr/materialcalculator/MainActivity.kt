package com.cailloutr.materialcalculator

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.cailloutr.materialcalculator.presentation.CalculatorScreen
import com.cailloutr.materialcalculator.ui.theme.MaterialCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialCalculatorTheme {
                CalculatorScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    MaterialCalculatorTheme {
        CalculatorScreen()
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MainActivityDarkPreview() {
    MaterialCalculatorTheme {
        CalculatorScreen()
    }
}
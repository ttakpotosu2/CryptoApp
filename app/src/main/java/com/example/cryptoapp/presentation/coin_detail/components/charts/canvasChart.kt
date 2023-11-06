package com.example.cryptoapp.presentation.coin_detail.components.charts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun CanvasChart(
    modifier: Modifier = Modifier,
    list: List<Float>
) {
    val zippedList: List<Pair<Float?, Float?>> = list.zipWithNext()

    Row (modifier = modifier){
        val max = list.max()
        val min = list.min()

        val lineColor = if (list.last() > list.first()) Color.Green else Color.Red

        fun getValuePercentageForRange(value: Float, max: Float, min: Float) =
            (value - min) / (max - min)

        for (pair in zippedList){

            val fromValuePercentage = pair.first?.let { getValuePercentageForRange(it, max, min) }
            val toValuePercentage = pair.second?.let { getValuePercentageForRange(it, max, min) }

            Canvas(
                modifier = Modifier.fillMaxWidth(),
                onDraw = {
                    val fromPoint = Offset(x = 0f, y = size.height.times(1 - fromValuePercentage!!))
                    val toPoint = Offset(x = size.width, y = size.height.times(1 - toValuePercentage!!))

                    drawLine(
                        color = lineColor,
                        start = fromPoint,
                        end = toPoint,
                        strokeWidth = 3f
                    )
                }
            )
        }
    }
}
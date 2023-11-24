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

        fun getValuePercentageForRange(value: Float?, max: Float?, min: Float?) =
            (min?.let { value?.minus(it) })?.div((max?.minus(min)!!))

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

//@Composable
//fun CanvasChart(
//    modifier: Modifier = Modifier,
//    list: List<Float?>
//) {
//    val zippedList = list.zipWithNext()
//
//    val max = list.maxOfOrNull { it ?: 1f }
//    val min = list.minOfOrNull { it ?: 0f }
//
//    val lineColor = if (list.last()!! > list.first()!!) Color.Green else Color.Red
//
//    Canvas(modifier = modifier) {
//        for ((fromValue, toValue) in zippedList) {
//            val fromPercentage = fromValue?.let { (it - min!!) / (max!! - min) } ?: 0f
//            val toPercentage = toValue?.let { (it - min!!) / (max!! - min) } ?: 0f
//
//            val fromPoint = Offset(x = 0f, y = size.height * (1 - fromPercentage))
//            val toPoint = Offset(x = size.width, y = size.height * (1 - toPercentage))
//
//            drawLine(color = lineColor, start = fromPoint, end = toPoint, strokeWidth = 3f)
//        }
//    }
//}
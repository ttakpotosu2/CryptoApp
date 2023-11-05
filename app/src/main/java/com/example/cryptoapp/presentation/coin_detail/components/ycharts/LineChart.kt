package com.example.cryptoapp.presentation.coin_detail.components.ycharts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.ShadowUnderLine

@Composable
fun SingleLineChart(
    pointData: List<Point>,
   // compWidth: Dp,
) {
    val steps = pointData.size
  //  val xAxisLabels = listOf("15m", "30m","1h","6h","12h","24h","7d","30d","1y")
    val xAxisData = AxisData.Builder()
        .axisStepSize(24.dp)
        .backgroundColor(Color(0xff2C2A2A))
        .steps(pointData.size - 1)
//        .labelData { index ->
//            xAxisLabels[index]
//        }
        .labelAndAxisLinePadding(15.dp)
        .axisLabelColor(Color.LightGray)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color(0xff2C2A2A))
        .labelAndAxisLinePadding(20.dp)
//        .labelData { i ->
//            val yScale = 100 / steps
//            (i * yScale).toString()
//        }
        .axisLineColor(Color.LightGray)
        .axisLabelColor(Color.LightGray)
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointData,
                    lineStyle = LineStyle(
                        color = Color(0xff06CC7B),
                        lineType = LineType.Straight(),
                        width = 5f
                    )
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        backgroundColor = Color(0xff2C2A2A),
        gridLines = GridLines(
            Color.LightGray.copy(alpha = 0.1f),
            enableHorizontalLines = false
        )
    )
    LineChart(
        modifier = Modifier.fillMaxSize().height(200.dp).background(Color(0xff2C2A2A)),
        lineChartData = lineChartData
    )
}
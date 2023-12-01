package com.example.cryptoapp.presentation.coin_detail.components.charts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import coil.size.Size

@Composable
fun SingleLineChart(
    pointData: List<Point>,
    modifier: Modifier = Modifier
) {
    val steps = pointData.size
    val xAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.White)
        .labelAndAxisLinePadding(0.dp)
        .axisLineColor(Color.White)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.White)
        .labelAndAxisLinePadding(0.dp)
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointData,
                    lineStyle = LineStyle(
                        color = Color.Black,
                        lineType = LineType.SmoothCurve(),
                        width = 3f
                    )
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        isZoomAllowed = false,
        backgroundColor = Color.White,
        containerPaddingEnd = 0.dp,
        gridLines = GridLines(
            enableHorizontalLines = false,
            enableVerticalLines = true,
        )
    )
    LineChart(
        modifier = modifier,
        lineChartData = lineChartData
    )
}
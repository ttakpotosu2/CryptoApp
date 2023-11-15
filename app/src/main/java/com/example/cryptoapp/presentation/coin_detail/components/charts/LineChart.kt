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
        .axisStepSize(20.dp)
        .axisLineColor(Color.White)
        .steps(steps - 1)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointData,
                    lineStyle = LineStyle(
                        color = Color(0xff1E60DF),
                        lineType = LineType.SmoothCurve(),
                        width = 5f
                    )
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        isZoomAllowed = false,
    )
    LineChart(
        modifier = modifier,
        lineChartData = lineChartData
    )
}
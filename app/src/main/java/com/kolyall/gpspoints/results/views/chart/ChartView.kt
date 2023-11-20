package com.kolyall.gpspoints.results.views.chart

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import com.androidplot.xy.FastLineAndPointRenderer
import com.androidplot.xy.XYSeries
import com.kolyall.gpspoints.databinding.ViewItemChartBinding


class ChartView : LinearLayoutCompat {
    constructor(context: Context) : super(context) {
        setContentView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setContentView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setContentView()
    }

    private lateinit var binding: ViewItemChartBinding

    private fun setContentView() {
        binding = ViewItemChartBinding.inflate(LayoutInflater.from(context), this)
    }

    fun renderView(chartUiModel: ChartUiModel) {
//        val formatter = FastLineAndPointRenderer.Formatter(Color.BLUE, null, null)
//        formatter.isLegendIconEnabled = false

//        val formatter = LineAndPointFormatter(Color.RED, Color.GREEN, Color.BLUE, null)
        // Установит параметры сглаживания линии
//        formatter.linePaint.isAntiAlias = true
        val formatter = SplineLineAndPointFormatter(
            color.getColor(), null,
            Color.argb(60, color.getRed(), color.getGreen(), color.getBlue()), null
        )

        binding.plotView.addSeries(chartUiModel, formatter)
    }

    class ChartUiModel internal constructor(private val data: List<ChartPointUiModel>) : XYSeries {

        override fun size(): Int {
            return data.size
        }

        override fun getX(index: Int): Number {
            return data[index].x
        }

        override fun getY(index: Int): Number {
            return data[index].y
        }

        override fun getTitle(): String {
            return ""
        }
    }

}

package com.kolyall.gpspoints.results.views.chart

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.Color
import android.os.Environment
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import com.androidplot.xy.CatmullRomInterpolator
import com.androidplot.xy.LineAndPointFormatter
import com.androidplot.xy.PanZoom
import com.androidplot.xy.XYPlot
import com.androidplot.xy.XYSeries
import com.androidplot.xy.ZoomEstimator
import com.kolyall.gpspoints.databinding.ViewItemChartBinding
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.abs


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
        layoutParams = LayoutParams(
            /* width = */ LayoutParams.MATCH_PARENT,
            /* height = */ LayoutParams.WRAP_CONTENT
        )
        orientation = VERTICAL

        val plotView = binding.plotView
        binding.submitButton.setOnClickListener {
            onPrintClickListener?.onPrintClicked(plotView)
        }
    }

    fun renderView(chartUiModel: ChartUiModel) {
//        val formatter = FastLineAndPointRenderer.Formatter(
//            /* lineColor = */ Color.BLUE,
//            /* vertexColor = */ null,
//            /* plf = */ null
//        )
        val formatter = LineAndPointFormatter(
            /* lineColor = */ Color.RED,
            /* vertexColor = */ Color.GREEN,
            /* fillColor = */ Color.TRANSPARENT,
            /* plf = */ null
        )
            .apply {
                isLegendIconEnabled = false
                linePaint.isAntiAlias = true

                interpolationParams = CatmullRomInterpolator.Params(
                    /* pointPerSegment = */ 10,
                    /* type = */CatmullRomInterpolator.Type.Centripetal
                )
            }


        val plotView = binding.plotView
        plotView.addSeries(chartUiModel, formatter)

        val panZoom = PanZoom.attach(
            /* plot = */ plotView,
            /* pan = */ PanZoom.Pan.BOTH,
            /* zoom = */ PanZoom.Zoom.STRETCH_BOTH,
            /* limit = */ PanZoom.ZoomLimit.MIN_TICKS
        )

        // enable autoselect of sampling level based on visible boundaries:
        plotView.registry.estimator = ZoomEstimator()
    }

    fun setOnPrintClickListener(onPrintClickListener: OnPrintClickListener?) {
        this.onPrintClickListener = onPrintClickListener
    }

    private var onPrintClickListener: OnPrintClickListener? = null

    class ChartUiModel internal constructor(val data: List<ChartPointUiModel>) : XYSeries {

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

    companion object {
        const val TAG: String = "ChartView"
    }

    interface OnPrintClickListener {
        fun onPrintClicked(plotView: View)

    }

}

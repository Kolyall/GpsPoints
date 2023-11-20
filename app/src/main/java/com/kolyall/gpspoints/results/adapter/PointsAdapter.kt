package com.kolyall.gpspoints.results.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kolyall.gpspoints.results.views.chart.ChartView
import com.kolyall.gpspoints.results.views.point.PointUiModel
import com.kolyall.gpspoints.results.views.point.PointView

sealed interface PointsAdapterItem {
    data class PointItem(val point: PointUiModel) : PointsAdapterItem
    data class Chart(val chartUiModel: ChartView.ChartUiModel) : PointsAdapterItem
}

class MyDiffUtil : DiffUtil.ItemCallback<PointsAdapterItem>() {
    override fun areItemsTheSame(oldItem: PointsAdapterItem, newItem: PointsAdapterItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: PointsAdapterItem,
        newItem: PointsAdapterItem
    ): Boolean {
        return if (newItem is PointsAdapterItem.Chart && oldItem is PointsAdapterItem.Chart) {
            false
        } else if (newItem is PointsAdapterItem.PointItem && oldItem is PointsAdapterItem.PointItem) {
            false
        } else {
            false
        }
    }

}

class PointsAdapter constructor() :
    ListAdapter<PointsAdapterItem, RecyclerView.ViewHolder>(MyDiffUtil()) {

    companion object {
        const val VIEW_TYPE_ITEM_POINT: Int = Int.MIN_VALUE
        const val VIEW_TYPE_ITEM_CHART: Int = Int.MIN_VALUE + 1
    }

    private var onPrintClickListener: ChartView.OnPrintClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM_POINT -> {
                val itemView = PointView(parent.context)
                ItemViewHolder(itemView)
            }

            VIEW_TYPE_ITEM_CHART -> {
                val itemView = ChartView(parent.context)
                ChartViewHolder(itemView)
            }

            else -> {
                throw IllegalStateException("Not supported viewType: $viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is PointsAdapterItem.Chart -> {
                val chartViewHolder = holder as ChartViewHolder
                chartViewHolder.setOnPrintClickListener(onPrintClickListener)
                chartViewHolder.renderView(item)
            }

            is PointsAdapterItem.PointItem -> {
                val itemViewHolder = holder as ItemViewHolder
                itemViewHolder.renderView(item)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PointsAdapterItem.PointItem -> VIEW_TYPE_ITEM_POINT
            is PointsAdapterItem.Chart -> VIEW_TYPE_ITEM_CHART
        }
    }

    fun setOnPrintClickListener(onPrintClickListener: ChartView.OnPrintClickListener) {
        this.onPrintClickListener = onPrintClickListener
    }

    inner class ItemViewHolder constructor(private val pointView: PointView) :
        RecyclerView.ViewHolder(pointView) {
        fun renderView(item: PointsAdapterItem.PointItem) {
            pointView.renderView(item.point)
        }
    }

    inner class ChartViewHolder constructor(private val chartView: ChartView) :
        RecyclerView.ViewHolder(chartView) {
        fun renderView(item: PointsAdapterItem.Chart) {
            chartView.renderView(item.chartUiModel)
        }

        fun setOnPrintClickListener(onPrintClickListener: ChartView.OnPrintClickListener?) {
            chartView.setOnPrintClickListener(onPrintClickListener)
        }
    }
}

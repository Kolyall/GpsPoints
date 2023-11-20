package com.kolyall.gpspoints.results

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.core.presentation.fragment.BaseViewModel
import com.kolyall.gpspoints.results.adapter.PointsAdapterItem
import com.kolyall.gpspoints.results.views.chart.ChartPointUiModel
import com.kolyall.gpspoints.results.views.chart.ChartView
import com.kolyall.gpspoints.results.views.point.PointUiModel
import com.module.domain.GetPointListUseCase
import com.module.domain.Point
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PointsResultViewModel @Inject constructor(
    application: Application,
    private val getPointListUseCase: GetPointListUseCase
) : BaseViewModel(application) {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState get() = _uiState.asStateFlow()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        viewModelScope.launch {
            try {
                val points = getPointListUseCase()
                val pointUiModels = points.list.map {
                    it.toPointUiModel()
                }
                _uiState.value = UiState.Display(
                    pointsUiList = pointUiModels.map {
                        PointsAdapterItem.PointItem(it) as PointsAdapterItem
                    }
                        .toMutableList()
                        .apply {
                            add(
                                PointsAdapterItem.Chart(
                                    ChartView.ChartUiModel(
                                        points.list.map {
                                            it.toChartPointUiModel()
                                        }
                                    )
                                )
                            )
                        },
                )
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    sealed class UiState {
        data object Loading : UiState()
        data class Display(
            val pointsUiList: List<PointsAdapterItem>,
        ) : UiState()
    }

}

private fun Point.toChartPointUiModel(): ChartPointUiModel {
    return ChartPointUiModel(
        x = x.value,
        y = y.value
    )
}

private fun Point.toPointUiModel(): PointUiModel {
    return PointUiModel(
        xText = "x:${x.value}",
        yText = "y:${y.value}"
    )
}

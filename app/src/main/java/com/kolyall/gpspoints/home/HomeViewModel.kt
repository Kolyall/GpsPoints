package com.kolyall.gpspoints.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.core.presentation.fragment.BaseViewModel
import com.module.domain.FetchPointsPackUseCase
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    application: Application,
    private val fetchPointsPackUseCase: FetchPointsPackUseCase
) : BaseViewModel(application) {

    fun loadPointList(
        pointCount: Int?,
        onSuccess: () -> Unit?,
        onFailure: (String) -> Unit
    ) {
        val count = pointCount?.takeIf { it > 0 } ?: run {
            onFailure("Enter points count!")
            return
        }
        viewModelScope.launch {
            try {
                fetchPointsPackUseCase(count = count)
                onSuccess()
            } catch (exception: HttpException) {
                Log.w(TAG, "loadPointList: ", exception)
                onFailure(exception.message())
            } catch (exception: Exception) {
                Log.w(TAG, "loadPointList: ", exception)
                onFailure("An error occurs!")
            } catch (exception: NotImplementedError) {
                Log.w(TAG, "loadPointList: ", exception)
                onFailure("Not Implemented!")
            }
        }
    }
}
package com.kolyall.gpspoints.results

import androidx.lifecycle.ViewModel
import com.core.presentation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PointsResultFragmentModules {

    @Binds
    @IntoMap
    @ViewModelKey(PointsResultViewModel::class)
    fun bindsViewModel(viewModel: PointsResultViewModel): ViewModel
}

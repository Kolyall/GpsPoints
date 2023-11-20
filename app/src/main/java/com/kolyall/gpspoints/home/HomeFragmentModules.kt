package com.kolyall.gpspoints.home

import androidx.lifecycle.ViewModel
import com.core.presentation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HomeFragmentModules {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindsViewModel(viewModel: HomeViewModel): ViewModel
}

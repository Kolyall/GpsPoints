package com.core.presentation

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Deprecated("will be removed after migration to Compose")
@Module
interface ViewModelFactoryModule {

    @Binds
    fun factory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}

package com.core.presentation.fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.DefaultLifecycleObserver

open class BaseViewModel constructor(
    application: Application
) : AndroidViewModel(application), DefaultLifecycleObserver {

    companion object {

        const val TAG: String = "BaseViewModel"
    }
}

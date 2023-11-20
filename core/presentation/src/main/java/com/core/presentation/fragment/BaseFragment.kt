package com.core.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.properties.Delegates
import kotlin.reflect.KClass

abstract class BaseFragment<T : ViewBinding,VM : BaseViewModel> constructor(
    private val modelClass: KClass<VM>
) : Fragment() {
    //region Setup Mvvm
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var viewModel: VM by Delegates.notNull()

    private fun createViewModel(modelClass: KClass<VM>): VM {
        viewModel = ViewModelProvider(viewModelStore, viewModelFactory)[modelClass.java]
        return viewModel
    }
    //endregion

    //region ViewBinding
    private var _binding: T? = null

    // This property is only valid between onCreateView and onDestroyView.
    protected val binding get() = requireNotNull(_binding) { "Binding isn't initialized!" }
    //endregion

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInjection()
        setupViewModel()
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return view?.let {
            bindView(it)
        }?.also {
            _binding = it
        }?.root ?: buildViewBinding(inflater, container).also {
            _binding = it
        }.root
    }

    abstract fun buildViewBinding(inflater: LayoutInflater, container: ViewGroup?): T

    abstract fun bindView(view: View): T


    private fun setupInjection() {
        AndroidSupportInjection.inject(this)
    }

    private fun setupViewModel() {
        viewModel = createViewModel(modelClass)
        setupViewModelArguments()
        lifecycle.addObserver(viewModel)
    }


    /**
     * setup ViewModel arguments before lifecycle will be attached to viewModel
     * */
    open fun setupViewModelArguments() {
    }

    fun <T> Flow<T>.observeWith(block: (T) -> Unit): Job {
        return flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
            .onEach { item ->
                runCatching {
                    block(item)
                }
                    .onFailure { throwable ->
                        Log.e(TAG, "observeWith runCatching", throwable)
                    }
            }
            .flowOn(Dispatchers.Main)
            .catch { throwable -> Log.e(TAG, "observeWith", throwable) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    companion object {
        const val TAG: String = "BaseFragment"
    }
}
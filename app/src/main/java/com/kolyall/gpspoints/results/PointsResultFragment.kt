package com.kolyall.gpspoints.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.core.presentation.fragment.BaseFragment
import com.kolyall.gpspoints.R
import com.kolyall.gpspoints.databinding.FragmentPointsResultBinding
import com.kolyall.gpspoints.results.adapter.PointsAdapter

class PointsResultFragment :
    BaseFragment<FragmentPointsResultBinding, PointsResultViewModel>(PointsResultViewModel::class) {

    private val recyclerView get() = binding.recyclerView

    override fun buildViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPointsResultBinding {
        return FragmentPointsResultBinding.inflate(inflater, container, false)
    }

    override fun bindView(view: View): FragmentPointsResultBinding {
        return FragmentPointsResultBinding.bind(view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.uiState.observeWith { uiState ->
            when (uiState) {
                is PointsResultViewModel.UiState.Display -> {
                    val adapter = recyclerView.adapter as PointsAdapter
                    adapter.submitList(uiState.pointsUiList)
                    recyclerView.isVisible = true
                }

                PointsResultViewModel.UiState.Loading -> {
                    recyclerView.isVisible = false
                }
            }

        }
    }

    private fun setupRecyclerView() {
        val adapter = PointsAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.divider_drawable)
        drawable?.let { dividerItemDecoration.setDrawable(it) }

        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    companion object {
        const val TAG: String = "PointsResultFragment"
    }

}

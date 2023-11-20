package com.kolyall.gpspoints.results

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.core.presentation.fragment.BaseFragment
import com.kolyall.gpspoints.R
import com.kolyall.gpspoints.databinding.FragmentPointsResultBinding
import com.kolyall.gpspoints.results.adapter.PointsAdapter
import com.kolyall.gpspoints.results.bitmapconverter.ViewToBitmap
import com.kolyall.gpspoints.results.views.chart.ChartView
import kotlinx.coroutines.launch

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
        adapter.setOnPrintClickListener(object : ChartView.OnPrintClickListener {
            override fun onPrintClicked(plotView: View) {
                if (isPermissionGranted()) {
                    saveAsImage(plotView)
                } else {
                    requestMediaPermissions()
                }
            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.divider_drawable)
        drawable?.let { dividerItemDecoration.setDrawable(it) }

        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    private fun requestMediaPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.READ_MEDIA_IMAGES,
                ),
                REQUEST_CODE
            )
        } else {
            requestPermissions(
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                ),
                REQUEST_CODE
            )
        }
    }

    private fun isPermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
                    &&
                    ContextCompat.checkSelfPermission(
                        requireActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED

        }
    }

    private fun saveAsImage(plotView: View) {
        viewLifecycleOwner.lifecycleScope.launch {
            val filePath =
                ViewToBitmap.saveViewAsImageToFilePath("gpspoints", plotView)
            Toast.makeText(requireContext(), "Saved to: $filePath", Toast.LENGTH_SHORT).show()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                Toast.makeText(context, "Permissions are granted! Press `Print` button again!", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(context, "Permissions should be granted!", Toast.LENGTH_LONG)
                    .show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    companion object {
        const val TAG: String = "PointsResultFragment"
        const val REQUEST_CODE: Int = 500
    }

}

package com.kolyall.gpspoints.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.core.presentation.fragment.BaseFragment
import com.kolyall.gpspoints.R
import com.kolyall.gpspoints.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(HomeViewModel::class) {

    override fun buildViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun bindView(view: View): FragmentHomeBinding {
        return FragmentHomeBinding.bind(view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submitButton.setOnClickListener {
            viewModel.loadPointList(
                pointCount = binding.textInputEditText.text?.toString()?.toIntOrNull(),
                onSuccess = {
                    onSuccessListener?.onHomeSuccess()
                },
                onFailure = {message->
                    showErrorDialog(message)
                }
            )
        }
    }

    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dlg_error__title))
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { dialogInterface, id ->
                dialogInterface.dismiss()
            }
            .create()
            .show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSuccessListener) {
            onSuccessListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        onSuccessListener = null
    }

    private var onSuccessListener: OnSuccessListener? = null


    interface OnSuccessListener {
        fun onHomeSuccess()
    }

}


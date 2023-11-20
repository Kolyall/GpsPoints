package com.kolyall.gpspoints.results.views.point

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import com.kolyall.gpspoints.databinding.ViewItemPointBinding

class PointView : ConstraintLayout {
    constructor(context: Context) : super(context) {
        setContentView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setContentView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setContentView()
    }

    private lateinit var binding: ViewItemPointBinding

    private fun setContentView() {
        binding = ViewItemPointBinding.inflate(LayoutInflater.from(context), this)
        layoutParams = LayoutParams(
            /* width = */ LayoutParams.MATCH_PARENT,
            /* height = */LayoutParams.WRAP_CONTENT
        )
    }

    fun renderView(pointUiModel: PointUiModel) {
        binding.xTextView.text = pointUiModel.xText
        binding.yTextView.text = pointUiModel.yText
    }

}

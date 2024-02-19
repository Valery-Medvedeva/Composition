package com.example.composition.presentation

import android.content.res.ColorStateList
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.composition.R

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_answers),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.your_count),
        count
    )
}

@BindingAdapter("winnerImage")
fun bindWinnerImage(imageView: ImageView, winner: Boolean) {
    if (winner) {
        imageView.setImageResource(R.drawable.smile_happy)
    } else {
        imageView.setImageResource(R.drawable.smile_sad)
    }
}

@BindingAdapter("requiredPercent")
fun bindRequiredPercent(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percent),
        count
    )
}

@BindingAdapter("questions", "rightAnswers")
fun bindScorePercent(textView: TextView, questions: Int, rightAnswers: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.your_percent),
        (rightAnswers / questions.toDouble() * 100).toInt()
    )
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("percentOfRightAnswers")
fun bindPercentOfRightAnswers(progressBar: ProgressBar, percent: Int) {
    progressBar.setProgress(percent, true)
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, enoughCount: Boolean) {
    val colorID = if (enoughCount) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    val color = ContextCompat.getColor(textView.context, colorID)
    textView.setTextColor(color)
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, enoughPercent: Boolean) {
    val colorID = if (enoughPercent) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    val color = ContextCompat.getColor(progressBar.context, colorID)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener{
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}
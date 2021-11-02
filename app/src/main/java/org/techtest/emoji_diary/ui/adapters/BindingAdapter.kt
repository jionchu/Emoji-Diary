package org.techtest.emoji_diary.ui.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import org.techtest.emoji_diary.R

@BindingAdapter("android:setImage")
fun setImage(view: ImageView, id: Int) {
    view.setImageResource(id)
    println("$id,${R.drawable.emoji1}")
}
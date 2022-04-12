package com.example.actioninputspranto


import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:setFavoriteIcon")
fun setFavoriteIcon(ImageView:ImageView,favorite:Boolean){
    if (favorite){
        ImageView.setImageResource(R.drawable.ic_baseline_favorite_red)
    }
    else{
        ImageView.setImageResource(R.drawable.ic_baseline_favorite_24)
    }
}
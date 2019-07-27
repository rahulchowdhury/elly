package co.rahulchowdhury.elly.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageSrc")
fun loadImage(imageView: AppCompatImageView, url: String?) {
    Glide.with(imageView.context).load(url).into(imageView)
}

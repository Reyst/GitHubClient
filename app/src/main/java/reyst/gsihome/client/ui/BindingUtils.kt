package reyst.gsihome.client.ui

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

@BindingAdapter("app:url")
fun loadImage(view: ImageView, url: String?) {
    Picasso.get().load(url?:"").into(view)
}
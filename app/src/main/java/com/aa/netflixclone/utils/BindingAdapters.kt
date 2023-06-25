package com.aa.netflixclone.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aa.netflixclone.R
import com.aa.netflixclone.ui.base.BaseAdapter
import com.aa.netflixclone.ui.base.ErrorUiState
import com.aa.netflixclone.ui.search_fragment.ResultUiState
import com.aa.netflixclone.ui.search_fragment.SearchInteractionListener
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items)

}

@BindingAdapter(value = ["app:imageUrl"])
fun loadImage(view: ImageView, imageUrl: String) {
    Glide.with(view).load(imageUrl)
        .fitCenter()
        .centerCrop()
        .placeholder(R.drawable.baseline_image_24)
        .into(view)
}


@BindingAdapter("cardClickHandler", "clickListener")
fun setCardClickHandler(
    view: MaterialCardView,
    item: ResultUiState,
    listener: SearchInteractionListener,
) {
    view.setOnClickListener {
        if (item.mediaType.equals("tv", ignoreCase = true) || item.mediaType.equals(
                "person",
                ignoreCase = true
            )
        ) {
            listener.onSeriesClicked(item.id!!)
        } else {
            listener.onMovieClicked(item.id!!)
        }
    }
}


@BindingAdapter("android:showError")
fun showError(view: View, errorState: ErrorUiState?) {
    view.visibility =
        if (errorState is ErrorUiState.InternalServerError) View.VISIBLE else View.GONE
}


package com.aa.netflixclone.ui.movie_fragment

import com.aa.netflixclone.R
import com.aa.netflixclone.ui.base.BaseAdapter
import com.aa.netflixclone.ui.base.BaseInteractionListener

class MovieAdapter(
    items: List<MovieUiState>,
    listener: MovieInteractionListener
): BaseAdapter<MovieUiState>(items,listener) {
    override val layoutId = R.layout.item_movie_card
}

interface MovieInteractionListener : BaseInteractionListener {

    fun onMovieClicked(movieId: Int)
}
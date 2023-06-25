package com.aa.netflixclone.ui.search_fragment

import com.aa.netflixclone.R
import com.aa.netflixclone.ui.base.BaseAdapter
import com.aa.netflixclone.ui.base.BaseInteractionListener

class SearchAdapter (
    items: List<ResultUiState>,
    listener: SearchInteractionListener
): BaseAdapter<ResultUiState>(items, listener) {
    override val layoutId = R.layout.item_search_card
}

interface SearchInteractionListener : BaseInteractionListener {

    fun onMovieClicked(resultId: Int)
    fun onSeriesClicked(resultId: Int)
}
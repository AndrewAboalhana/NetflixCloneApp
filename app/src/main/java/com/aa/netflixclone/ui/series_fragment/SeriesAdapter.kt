package com.aa.netflixclone.ui.series_fragment

import com.aa.netflixclone.R
import com.aa.netflixclone.ui.base.BaseAdapter
import com.aa.netflixclone.ui.base.BaseInteractionListener

class SeriesAdapter(
    items: List<SeriesListUiState>,
    listener: SeriesInteractionListener

) : BaseAdapter<SeriesListUiState>(items,listener) {
    override val layoutId = R.layout.item_series_card
}

interface SeriesInteractionListener : BaseInteractionListener {

    fun onSeriesClicked(seriesId: Int)
}
package com.aa.netflixclone.ui.series_fragment

import com.aa.netflixclone.ui.base.BaseUiEffect
import com.aa.netflixclone.ui.movie_fragment.MovieUiEffect

sealed interface SeriesUiEffect : BaseUiEffect {

    data class ClickOnSeries(val seriesId: Int) : SeriesUiEffect
}
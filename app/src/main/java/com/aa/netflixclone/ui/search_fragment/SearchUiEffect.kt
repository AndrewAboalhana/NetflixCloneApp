package com.aa.netflixclone.ui.search_fragment

import com.aa.netflixclone.ui.base.BaseUiEffect

sealed interface SearchUiEffect : BaseUiEffect {

    data class ClickOnMovie(val movieId: Int) : SearchUiEffect

    data class ClickOnSeries(val seriesId: Int) : SearchUiEffect

}

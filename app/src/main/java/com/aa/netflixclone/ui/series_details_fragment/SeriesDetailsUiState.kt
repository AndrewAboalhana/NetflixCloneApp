package com.aa.netflixclone.ui.series_details_fragment

import com.aa.netflixclone.ui.base.BaseUiState
import com.aa.netflixclone.ui.base.ErrorUiState

data class SeriesDetailsUiState(
    val id: Int? = 0,
    val name: String? = "",
    val imageUrl: String? = "",
    val overview: String? = "",
    val firstAirDate: String? = "",
    val isLoading:Boolean = false,
    val error: ErrorUiState? = null,
):BaseUiState

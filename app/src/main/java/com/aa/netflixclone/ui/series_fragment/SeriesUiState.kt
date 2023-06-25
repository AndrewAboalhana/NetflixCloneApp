package com.aa.netflixclone.ui.series_fragment

import com.aa.netflixclone.ui.base.BaseUiState
import com.aa.netflixclone.ui.base.ErrorUiState

data class SeriesUiState(
    val series: List<SeriesListUiState> = emptyList(),
    val isLoading:Boolean = false,
    val error: ErrorUiState? = null,
): BaseUiState
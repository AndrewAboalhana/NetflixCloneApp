package com.aa.netflixclone.ui.movie_fragment

import com.aa.netflixclone.ui.base.BaseUiState
import com.aa.netflixclone.ui.base.ErrorUiState

data class MoviesUiState(
    val movies: List<MovieUiState> = emptyList(),
    val isLoading:Boolean = false,
    val error: ErrorUiState? = null,
): BaseUiState

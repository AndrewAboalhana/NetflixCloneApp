package com.aa.netflixclone.ui.movie_details_fragment

import com.aa.netflixclone.ui.base.BaseUiState
import com.aa.netflixclone.ui.base.ErrorUiState

data class MovieDetailsUiState(
    val id: Int? = 0,
    val imageUrl: String? = "",
    val overview: String? = "",
    val title: String? = "",
    val isLoading:Boolean = false,
    val error: ErrorUiState? = null,
):BaseUiState

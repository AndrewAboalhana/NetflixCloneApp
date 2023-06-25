package com.aa.netflixclone.ui.search_fragment

import com.aa.netflixclone.ui.base.BaseUiState
import com.aa.netflixclone.ui.base.ErrorUiState


data class SearchUiState(
    val searchInput: String = "",
    val results: List<ResultUiState> = listOf(),
    val isLoading:Boolean = false,
    val error: ErrorUiState? = null,
    val isResultIsEmpty: Boolean = false,
): BaseUiState

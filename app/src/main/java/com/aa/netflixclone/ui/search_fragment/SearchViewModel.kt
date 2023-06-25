package com.aa.netflixclone.ui.search_fragment

import androidx.lifecycle.viewModelScope
import com.aa.netflixclone.domain.models.search.MultiSearchEntity
import com.aa.netflixclone.domain.models.search.ResultEntity
import com.aa.netflixclone.domain.usecases.MultiSearchUseCase
import com.aa.netflixclone.ui.base.BaseViewModel
import com.aa.netflixclone.ui.base.ErrorUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getMultiSearchUseCase: MultiSearchUseCase,
) : BaseViewModel<SearchUiState, SearchUiEffect>(SearchUiState()), SearchInteractionListener {

    private var debounceJob: Job? = null
    private val _searchInputFlow = MutableStateFlow("")

    fun onInputSearchChange(newSearchInput: CharSequence) {
        _state.update { it.copy(searchInput = newSearchInput.toString()) }
        _searchInputFlow.value = newSearchInput.toString()
        debounceJob?.cancel()
        debounceJob = viewModelScope.launch {
            _searchInputFlow
                .debounce(400)
                .flowOn(Dispatchers.Default)
                .collect { onGetData() }
        }
    }

    private fun onGetData() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            { getMultiSearchUseCase.invoke(state.value.searchInput) },
            onSuccess = ::onMultiSearchSuccess,
            onError = ::onError
        )
    }

    private fun onMultiSearchSuccess(result: MultiSearchEntity) {
        val searchResult = result.results.toResultUiState()
        _state.update {
            it.copy(
                results = searchResult,
                isLoading = false,
                isResultIsEmpty = searchResult.isEmpty()
            )
        }
    }

    private fun onError(errorUiState: ErrorUiState) {
        _state.update { it.copy(error = errorUiState, isLoading = false) }
    }

    override fun onMovieClicked(resultId: Int) {
        viewModelScope.launch { _effect.emit(SearchUiEffect.ClickOnMovie(resultId)) }
    }

    override fun onSeriesClicked(resultId: Int) {
        viewModelScope.launch { _effect.emit(SearchUiEffect.ClickOnSeries(resultId)) }
    }
}

fun List<ResultEntity>.toResultUiState(): List<ResultUiState> {
    return this.map {
        ResultUiState(
            id = it.id,
            mediaType = it.mediaType,
            originalTitle = it.originalTitle,
            posterPath =  it.posterPath
        )
    }
}


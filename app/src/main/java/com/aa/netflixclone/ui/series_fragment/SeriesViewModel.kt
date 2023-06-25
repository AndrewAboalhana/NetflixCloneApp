package com.aa.netflixclone.ui.series_fragment

import androidx.lifecycle.viewModelScope
import com.aa.netflixclone.domain.models.series.SeriesEntity
import com.aa.netflixclone.domain.usecases.GetTrendingSeriesUseCase
import com.aa.netflixclone.ui.base.BaseViewModel
import com.aa.netflixclone.ui.base.ErrorUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val getTrendingSeriesUseCase: GetTrendingSeriesUseCase,
) : BaseViewModel<SeriesUiState, SeriesUiEffect>(SeriesUiState()), SeriesInteractionListener {


    init {
        getTrendingSeries()
    }

    private fun getTrendingSeries() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            getTrendingSeriesUseCase::invoke,
            onSuccess = ::getTrendingSeriesSuccess,
            onError = ::onError
        )
    }

    private fun getTrendingSeriesSuccess(movies: Flow<List<SeriesEntity>>) {
        viewModelScope.launch {
            movies.collect { items ->
                val seriesState = items.toSeriesUiState()
                _state.update { it.copy(series = seriesState, isLoading = false) }
            }
        }
    }

    private fun onError(errorUiState: ErrorUiState) {
        _state.update { it.copy(error = errorUiState, isLoading = false) }
    }

    override fun onSeriesClicked(seriesId: Int) {
        viewModelScope.launch { _effect.emit(SeriesUiEffect.ClickOnSeries(seriesId)) }
    }

    private fun List<SeriesEntity>.toSeriesUiState(): List<SeriesListUiState> {
        return this.map {
            SeriesListUiState(
                id = it.id,
                image = it.imageUrl,
            )
        }
    }


}
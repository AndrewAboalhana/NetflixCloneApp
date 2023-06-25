package com.aa.netflixclone.ui.series_details_fragment

import androidx.lifecycle.SavedStateHandle
import com.aa.netflixclone.domain.models.series.SeriesDetailsEntity
import com.aa.netflixclone.domain.usecases.GetSeriesDetailsUseCase
import com.aa.netflixclone.ui.base.BaseViewModel
import com.aa.netflixclone.ui.base.ErrorUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SeriesDetailsViewModel @Inject constructor(
    private val getSeriesDetailsUseCase: GetSeriesDetailsUseCase,
    stateHandle: SavedStateHandle,
) : BaseViewModel<SeriesDetailsUiState, SeriesDetailsUiEffect>(SeriesDetailsUiState()) {

    val args = SeriesDetailsFragmentArgs.fromSavedStateHandle(stateHandle)

    init {
        getSeriesDetails(args.id)
    }

    private fun getSeriesDetails(id: Int) {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            { getSeriesDetailsUseCase.invoke(id) },
            onError = ::onError,
            onSuccess = ::getSeriesDetailsSuccess
        )
    }

    private fun getSeriesDetailsSuccess(seriesDetails: SeriesDetailsEntity) {
        val seriesDetailsState = seriesDetails.toSeriesDetailsUiState()
        _state.update {
            it.copy(
                id = seriesDetailsState.id, name = seriesDetailsState.name,
                imageUrl = seriesDetailsState.imageUrl, overview = seriesDetailsState.overview,
                firstAirDate = seriesDetailsState.firstAirDate, isLoading = false
            )
        }
    }

    private fun onError(errorUiState: ErrorUiState) {
        _state.update { it.copy(error = errorUiState, isLoading = false) }
    }
}

fun SeriesDetailsEntity.toSeriesDetailsUiState(): SeriesDetailsUiState {
    return SeriesDetailsUiState(
        id = id,
        name = name,
        imageUrl = imageUrl,
        overview = overview,
        firstAirDate = firstAirDate,
    )
}
package com.aa.netflixclone.ui.movie_details_fragment

import androidx.lifecycle.SavedStateHandle
import com.aa.netflixclone.domain.models.movie.MovieDetailsEntity
import com.aa.netflixclone.domain.usecases.GetMovieDetailsUseCase
import com.aa.netflixclone.ui.base.BaseViewModel
import com.aa.netflixclone.ui.base.ErrorUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    stateHandle: SavedStateHandle
): BaseViewModel<MovieDetailsUiState,MovieDetailsUiEffect>(MovieDetailsUiState()) {

    val args = MovieDetailsFragmentArgs.fromSavedStateHandle(stateHandle)

    init {
        getMovieDetails(args.id)
    }

    private fun getMovieDetails(id: Int){
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            {getMovieDetailsUseCase.invoke(id)},
            onError = ::onError,
            onSuccess = ::getMovieDetailsSuccess
        )
    }

    private fun getMovieDetailsSuccess(movieDetails:MovieDetailsEntity){
        val movieDetailsState = movieDetails.toMovieDetailsUiState()
        _state.update { it.copy(id = movieDetailsState.id, imageUrl = movieDetailsState.imageUrl,
            overview = movieDetailsState.overview, title = movieDetailsState.title,
        isLoading = false) }
    }

    private fun onError(errorUiState: ErrorUiState){
        _state.update { it.copy(error = errorUiState, isLoading = false) }
    }
}

fun MovieDetailsEntity.toMovieDetailsUiState():MovieDetailsUiState{
    return MovieDetailsUiState(
        id = id ,
        imageUrl = imageUrl ,
        overview = overview ,
        title = title ,
    )
}
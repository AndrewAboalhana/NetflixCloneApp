package com.aa.netflixclone.ui.movie_fragment

import androidx.lifecycle.viewModelScope
import com.aa.netflixclone.domain.models.movie.MovieEntity
import com.aa.netflixclone.domain.usecases.GetTrendingMoviesUseCase
import com.aa.netflixclone.ui.base.BaseViewModel
import com.aa.netflixclone.ui.base.ErrorUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
) : BaseViewModel<MoviesUiState, MovieUiEffect>(MoviesUiState()),MovieInteractionListener {


    init {
        getTrendingMovies()
    }


   private fun getTrendingMovies(){
       _state.update { it.copy(isLoading = true) }
       tryToExecute(
           getTrendingMoviesUseCase::invoke,
           onSuccess = ::getTrendingMoviesSuccess,
           onError = ::onError
       )
   }

    private fun onError(errorUiState: ErrorUiState){
        _state.update { it.copy(error = errorUiState, isLoading = false) }
    }

    private fun getTrendingMoviesSuccess(movies: Flow<List<MovieEntity>>){
        viewModelScope.launch {
            movies.collect{items ->
                val moviesState = items.toMovieUiState()
                _state.update { it.copy(movies = moviesState,isLoading = false) }
            }
        }
    }

    override fun onMovieClicked(movieId: Int) {
        viewModelScope.launch { _effect.emit(MovieUiEffect.ClickOnMovie(movieId)) }
    }

}


fun List<MovieEntity>.toMovieUiState(): List<MovieUiState>{
    return this.map {
        MovieUiState(
            id = it.id,
            imageUrl = it.imageUrl,
            title = it.title,
            originalLanguage = it.originalLanguage
        )
    }
}
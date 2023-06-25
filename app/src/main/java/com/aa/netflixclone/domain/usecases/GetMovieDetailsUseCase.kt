package com.aa.netflixclone.domain.usecases

import com.aa.netflixclone.domain.models.movie.MovieDetailsEntity
import com.aa.netflixclone.domain.repositories.MoviesAndSeriesRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val moviesAndSeriesRepository: MoviesAndSeriesRepository,
) {
    suspend operator fun invoke(id: Int): MovieDetailsEntity{
        return moviesAndSeriesRepository.getMoviesDetails(id)
    }
}
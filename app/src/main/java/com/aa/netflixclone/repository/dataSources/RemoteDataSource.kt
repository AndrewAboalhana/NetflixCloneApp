package com.aa.netflixclone.repository.dataSources

import com.aa.netflixclone.data.remote.resources.MultiSearchResource
import com.aa.netflixclone.data.remote.resources.TrendingResource
import com.aa.netflixclone.data.remote.resources.movie_details.MovieDetailsResource
import com.aa.netflixclone.data.remote.resources.series_details.SeriesDetailsResource

interface RemoteDataSource {

    suspend fun getTrendingMovies(
        media_type: String = MOVIE,
        time_window: String = TIME_WINDOW,
    ): TrendingResource

    suspend fun getTrendingSeries(
        media_type: String = SERIES,
        time_window: String = TIME_WINDOW,
    ): TrendingResource

    suspend fun getMovieDetails(movieId: Int): MovieDetailsResource

    suspend fun getSeriesDetails(seriesId: Int): SeriesDetailsResource

    suspend fun multiSearch(query: String): MultiSearchResource

    companion object {
        const val MOVIE = "movie"
        const val TIME_WINDOW = "week"
        const val SERIES = "tv"
    }


}
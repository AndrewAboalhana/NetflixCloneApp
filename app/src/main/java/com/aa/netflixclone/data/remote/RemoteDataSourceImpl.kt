package com.aa.netflixclone.data.remote

import com.aa.netflixclone.data.remote.resources.MultiSearchResource
import com.aa.netflixclone.data.remote.resources.TrendingResource
import com.aa.netflixclone.data.remote.resources.movie_details.MovieDetailsResource
import com.aa.netflixclone.data.remote.resources.series_details.SeriesDetailsResource
import com.aa.netflixclone.domain.models.exceptions.NetworkException
import com.aa.netflixclone.repository.dataSources.RemoteDataSource
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val netflixService: APIService
): RemoteDataSource {
    override suspend fun getTrendingMovies(
        media_type: String,
        time_window: String,
    ): TrendingResource {
        return tryToExecute {
            netflixService.getTrending(media_type, time_window)
        }
    }

    override suspend fun getTrendingSeries(
        media_type: String,
        time_window: String,
    ): TrendingResource {
      return  tryToExecute {
          netflixService.getTrending(media_type, time_window)
      }
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsResource {
       return tryToExecute {
           netflixService.getMovieDetails(movieId)
       }
    }

    override suspend fun getSeriesDetails(seriesId: Int): SeriesDetailsResource {
       return tryToExecute {
           netflixService.getSeriesDetails(seriesId)
       }
    }

    override suspend fun multiSearch(query: String): MultiSearchResource {
       return tryToExecute {
           netflixService.multiSearch(query)
       }
    }

    private suspend fun <T> tryToExecute(func: suspend () -> Response<T>): T {
        val response = func()
        if (response.isSuccessful) {
            return response.body() ?: throw NetworkException.NotFoundException
        }
        throw when (response.code()) {
            404 -> NetworkException.NotFoundException
            402 -> NetworkException.ApiKeyExpiredException
            else -> IOException()
        }
    }
}
package com.aa.netflixclone.data.remote

import com.aa.netflixclone.data.remote.resources.MultiSearchResource
import com.aa.netflixclone.data.remote.resources.TrendingResource
import com.aa.netflixclone.data.remote.resources.movie_details.MovieDetailsResource
import com.aa.netflixclone.data.remote.resources.series_details.SeriesDetailsResource
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrending(
        @Path("media_type") media_type: String,
        @Path("time_window") time_window: String
    ): Response<TrendingResource>

    @GET("search/multi")
    suspend fun multiSearch(
        @Query("query") query: String
    ): Response<MultiSearchResource>

    @GET("movie/{movie_id}")
     suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
    ): Response<MovieDetailsResource>

    @GET("tv/{tv_id}")
    suspend fun getSeriesDetails(
        @Path("tv_id") seriesId: Int,
    ): Response<SeriesDetailsResource>

}
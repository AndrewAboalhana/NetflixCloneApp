package com.aa.netflixclone.data

import com.aa.netflixclone.data.remote.APIService
import com.aa.netflixclone.data.remote.RemoteDataSourceImpl
import com.aa.netflixclone.data.remote.resources.MultiSearchResource
import com.aa.netflixclone.data.remote.resources.TrendingResource
import com.aa.netflixclone.data.remote.resources.movie_details.MovieDetailsResource
import com.aa.netflixclone.data.remote.resources.series_details.SeriesDetailsResource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response

class RemoteDataSourceImplTest {

    private lateinit var dataSource: RemoteDataSourceImpl
    private lateinit var mockNetflixService: APIService

    @Before
    fun setup() {
        mockNetflixService = mock(APIService::class.java)
        dataSource = RemoteDataSourceImpl(mockNetflixService)
    }

    @Test
    fun getTrendingMovies_shouldReturnTrendingResource() = runBlocking {
        // Arrange
        val mediaType = "movie"
        val timeWindow = "day"
        val expectedResource = TrendingResource()
        `when`(mockNetflixService.getTrending(mediaType, timeWindow)).thenReturn(Response.success(expectedResource))

        // Act
        val result: TrendingResource = dataSource.getTrendingMovies(mediaType, timeWindow)

        // Assert
        assertEquals(expectedResource, result)
    }

    @Test
    fun getTrendingSeries_shouldReturnTrendingResource() = runBlocking {
        // Arrange
        val mediaType = "tv"
        val timeWindow = "week"
        val expectedResource = TrendingResource()
        `when`(mockNetflixService.getTrending(mediaType, timeWindow)).thenReturn(Response.success(expectedResource))

        // Act
        val result: TrendingResource = dataSource.getTrendingSeries(mediaType, timeWindow)

        // Assert
        assertEquals(expectedResource, result)
    }

    @Test
    fun getMovieDetails_shouldReturnMovieDetailsResource() = runBlocking {
        // Arrange
        val movieId = 123
        val expectedResource = MovieDetailsResource(/* mock data */)
        `when`(mockNetflixService.getMovieDetails(movieId)).thenReturn(Response.success(expectedResource))

        // Act
        val result: MovieDetailsResource = dataSource.getMovieDetails(movieId)

        // Assert
        assertEquals(expectedResource, result)
    }

    @Test
    fun getSeriesDetails_shouldReturnSeriesDetailsResource() = runBlocking {
        // Arrange
        val seriesId = 456
        val expectedResource = SeriesDetailsResource(/* mock data */)
        `when`(mockNetflixService.getSeriesDetails(seriesId)).thenReturn(Response.success(expectedResource))

        // Act
        val result: SeriesDetailsResource = dataSource.getSeriesDetails(seriesId)

        // Assert
        assertEquals(expectedResource, result)
    }

    @Test
    fun multiSearch_shouldReturnMultiSearchResource() = runBlocking {
        // Arrange
        val query = "example query"
        val expectedResource = MultiSearchResource(/* mock data */)
        `when`(mockNetflixService.multiSearch(query)).thenReturn(Response.success(expectedResource))

        // Act
        val result: MultiSearchResource = dataSource.multiSearch(query)

        // Assert
        assertEquals(expectedResource, result)
    }

}

package com.aa.netflixclone.data

import com.aa.netflixclone.data.remote.resources.MultiSearchResource
import com.aa.netflixclone.data.remote.resources.TrendingResource
import com.aa.netflixclone.data.remote.resources.movie_details.MovieDetailsResource
import com.aa.netflixclone.data.remote.resources.series_details.SeriesDetailsResource
import com.aa.netflixclone.domain.models.movie.MovieDetailsEntity
import com.aa.netflixclone.domain.models.movie.MovieEntity
import com.aa.netflixclone.domain.models.search.MultiSearchEntity
import com.aa.netflixclone.domain.models.search.ResultEntity
import com.aa.netflixclone.domain.models.series.SeriesDetailsEntity
import com.aa.netflixclone.domain.models.series.SeriesEntity
import com.aa.netflixclone.repository.MoviesAndSeriesRepositoryImpl
import com.aa.netflixclone.repository.dataSources.LocalDataSource
import com.aa.netflixclone.repository.dataSources.RemoteDataSource
import com.aa.netflixclone.repository.dataSources.SharedPreferenceService
import com.aa.netflixclone.repository.mappers.toMoviesDatabase
import com.aa.netflixclone.repository.mappers.toResultResource
import com.aa.netflixclone.repository.mappers.toSeasonResource
import com.aa.netflixclone.repository.mappers.toSeriesDatabase
import com.aa.netflixclone.repository.mappers.toSeriesResultResource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MoviesAndSeriesRepositoryImplTest {
    private lateinit var repository: MoviesAndSeriesRepositoryImpl
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var localDataSource: LocalDataSource
    private lateinit var sharedPreferenceService: SharedPreferenceService

    @Before
    fun setUp() {
        remoteDataSource = mockk()
        localDataSource = mockk()
        sharedPreferenceService = mockk()
        repository = MoviesAndSeriesRepositoryImpl(
            remoteDataSource,
            localDataSource,
            sharedPreferenceService
        )
    }

    @Test
    fun getTrendingMovies_returnsMappedMovies() = runBlockingTest {
        // Arrange
        val expectedMovies = listOf(
            MovieEntity(
                id = 1, title = "Movie 1", imageUrl = "",
                overview = "", popularity = 0.0, originalLanguage =
                "en", releaseDate = "", voteAverage = 0.0
            ),
            MovieEntity(
                id = 2, imageUrl = "",
                overview = "", popularity = 0.0,
                originalLanguage = "en",
                releaseDate = "", voteAverage = 0.0, title = "movie 2"
            )
        )
        coEvery { remoteDataSource.getTrendingMovies() } returns TrendingResource(results = expectedMovies.toResultResource())

        // Act
        val result = repository.getTrendingMovies("movie", "week")

        // Assert
        assertEquals(expectedMovies, result)
    }

    @Test
    fun getTrendingSeries_returnsMappedSeries() = runBlockingTest {
        // Arrange
        val expectedSeries = listOf(
            SeriesEntity(
                id = 1,
                originalName = "Series 1",
                imageUrl = "",
                overview = "",
                popularity = 0.0,
                originalLanguage = "en",
                voteAverage = 0.0,
                firstAirDate = ""
            ), SeriesEntity(
                id = 2,
                originalName = "Series 2",
                imageUrl = "",
                overview = "",
                popularity = 0.0,
                originalLanguage = "en",
                voteAverage = 0.0,
                firstAirDate = ""
            )
        )
        coEvery { remoteDataSource.getTrendingSeries() } returns
                TrendingResource(results = expectedSeries.toSeriesResultResource())

        // Act
        val result = repository.getTrendingSeries("tv", "day")

        // Assert
        assertEquals(expectedSeries, result)
    }

    @Test
    fun getMoviesDetails_returnsMappedMovieDetails() = runBlockingTest {
        // Arrange
        val movieId = 123
        val expectedMovieDetails = MovieDetailsEntity(
            id = movieId, title = "Movie Details",
            imageUrl = null,
            adult = null,
            imdbId = null,
            overview = null,
            date = null,
            runtime = null,
            video = null,
            voteAverage = null
        )
        coEvery { remoteDataSource.getMovieDetails(movieId) } returns MovieDetailsResource(
            id = expectedMovieDetails.id,
            title = expectedMovieDetails.title,
            adult = expectedMovieDetails.adult,
            imdbId = expectedMovieDetails.imdbId,
            overview = expectedMovieDetails.overview,
            releaseDate = expectedMovieDetails.date,
            runtime = expectedMovieDetails.runtime,
            video = expectedMovieDetails.video,
            voteAverage = expectedMovieDetails.voteAverage
        )

        // Act
        val result = repository.getMoviesDetails(movieId)

        // Assert
        assertEquals(expectedMovieDetails, result)
    }


    @Test
    fun getSeriesDetails_returnsMappedSeriesDetails() = runBlockingTest {
        // Arrange
        val seriesId = 456
        val expectedSeriesDetails = SeriesDetailsEntity(
            id = seriesId,
            name = "Series Details",
            adult = null,
            imageUrl = null,
            firstAirDate = null,
            numberOfEpisodes = null,
            numberOfSeasons = null,
            overview = null,
            popularity = null,
            seasons = null,
            type = null,
            voteAverage = null
        )
        coEvery { remoteDataSource.getSeriesDetails(seriesId) } returns SeriesDetailsResource(
            name = expectedSeriesDetails.name,
            adult = expectedSeriesDetails.adult,
            id = expectedSeriesDetails.id,
            posterPath = expectedSeriesDetails.imageUrl,
            firstAirDate = expectedSeriesDetails.firstAirDate,
            numberOfSeasons = expectedSeriesDetails.numberOfSeasons,
            numberOfEpisodes = expectedSeriesDetails.numberOfEpisodes,
            overview = expectedSeriesDetails.overview,
            popularity = expectedSeriesDetails.popularity,
            seasons = expectedSeriesDetails.seasons?.toSeasonResource(),
            type = expectedSeriesDetails.type,
            voteAverage = expectedSeriesDetails.voteAverage

        )

        // Act
        val result = repository.getSeriesDetails(seriesId)

        // Assert
        assertEquals(expectedSeriesDetails, result)
    }

    @Test
    fun saveCachingTimeStamp_callsSharedPreferenceService() = runBlocking {

        // Define the behavior or response for the method call
        coEvery {
            sharedPreferenceService.saveLastCachingTimeStamp(
                "caching_key",
                123456
            )
        } returns Unit

        // Create an instance of the class being tested and pass the mock instance through the constructor
        val repository = MoviesAndSeriesRepositoryImpl(
            remoteDataSource,
            localDataSource,
            sharedPreferenceService
        )

        // Perform the test and assert the expected behavior
        repository.saveCachingTimeStamp("caching_key", 123456)

    }

    @Test
    fun getLastCachingTimeStamp_returnsValueFromSharedPreferenceService() = runBlockingTest {
        // Arrange
        val key = "caching_key"
        val expectedTime = 123456L
        coEvery { sharedPreferenceService.getLastCachingTime(key) } returns expectedTime

        // Act
        val result = repository.getLastCachingTimeStamp(key)

        // Assert
        assertEquals(expectedTime, result)
    }


    @Test
    fun multiSearch_returnsMappedMultiSearchResults() = runBlockingTest {
        // Arrange
        val query = "Netflix"
        val expectedSearch = listOf(
            ResultEntity(id = 1,
                originalTitle = "Movie 1",
                posterPath = "",
                overview = "",
                mediaType = "movie",
                originalLanguage = "",
                releaseDate = "",
            ),
            ResultEntity(id = 2,
                originalTitle = "Series 1",
                posterPath = "",
                overview = "",
                mediaType = "Series",
                originalLanguage = "",
                releaseDate = "",
            )
        )
        val expectedMultiSearch = MultiSearchEntity(
            results = expectedSearch
           )


        coEvery { remoteDataSource.multiSearch(query) } returns MultiSearchResource(
            results = expectedMultiSearch.results.toResultResource()
        )

        // Act
        val result = repository.multiSearch(query)

        // Assert
        assertEquals(expectedMultiSearch, result)
    }

    @Test
    fun getTrendingMoviesFromLocal_returnsMappedMovies() = runBlockingTest {
        // Arrange
        val expectedMovies = listOf(
            MovieEntity(id = 1,
                title = "Movie 1",
                imageUrl = "",
                overview = "",
                popularity = 0.0,
                originalLanguage = "",
                releaseDate = "",
                voteAverage = 0.0
            ),
            MovieEntity(id = 2,
                title = "Movie 2",
                imageUrl = "",
                overview = "",
                popularity = 0.0,
                originalLanguage = "",
                releaseDate = "",
                voteAverage = 0.0
            )
        )

        coEvery { localDataSource.getTrendingMovies() } returns flowOf(expectedMovies.toMoviesDatabase())

        // Act
        val resultFlow: Flow<List<MovieEntity>> = repository.getTrendingMoviesFromLocal()
        val result = resultFlow.single()

        // Assert
        assertEquals(expectedMovies, result)
    }

    @Test
    fun getTrendingSeriesFromLocal_returnsMappedSeries() = runBlockingTest {
        // Arrange
        val expectedSeries = listOf(
            SeriesEntity(
                id = 1,
                originalName = "Series 1",
                imageUrl = "",
                originalLanguage = "",
                overview = "",
                firstAirDate = "",
                popularity = 0.0,
                voteAverage = 0.0
            ),
            SeriesEntity(
                id = 2,
                originalName = "Series 2",
                imageUrl = "",
                originalLanguage = "",
                overview = "",
                firstAirDate = "",
                popularity = 0.0,
                voteAverage = 0.0
            )
        )
        coEvery { localDataSource.getTrendingSeries() } returns flowOf(expectedSeries.toSeriesDatabase())

        // Act
        val resultFlow: Flow<List<SeriesEntity>> = repository.getTrendingSeriesFromLocal()
        val result = resultFlow.single()

        // Assert
        assertEquals(expectedSeries, result)
    }

    @Test
    fun refreshTrendingMovies_insertsMappedMovies() = runBlockingTest {
        // Arrange
        val movies = listOf(
            MovieEntity(id = 1,
                title = "Movie 1",
                imageUrl = "",
                overview = "",
                popularity = 0.0,
                originalLanguage = "",
                releaseDate = "",
                voteAverage = 0.0
            ),
            MovieEntity(id = 2,
                title = "Movie 2",
                imageUrl = "",
                overview = "",
                popularity = 0.0,
                originalLanguage = "",
                releaseDate = "",
                voteAverage = 0.0
            )
        )

        // Define the behavior for the insertTrendingMovies method
        coEvery { localDataSource.insertTrendingMovies(any()) } returns Unit

        // Create an instance of the class being tested and pass the mock instance through the constructor
        val repository = MoviesAndSeriesRepositoryImpl(
            remoteDataSource,
            localDataSource,
            sharedPreferenceService
        )

        // Act
        repository.refreshTrendingMovies(movies)

        // Assert
        coEvery { localDataSource.insertTrendingMovies(movies.toMoviesDatabase()) } returns Unit
    }

    @Test
    fun refreshTrendingSeries_insertsMappedSeries() = runBlockingTest {
        // Arrange
        val series = listOf(
            SeriesEntity(
                id = 1,
                originalName = "Series 1",
                imageUrl = "",
                originalLanguage = "",
                overview = "",
                firstAirDate = "",
                popularity = 0.0,
                voteAverage = 0.0
            ),
            SeriesEntity(
                id = 2,
                originalName = "Series 2",
                imageUrl = "",
                originalLanguage = "",
                overview = "",
                firstAirDate = "",
                popularity = 0.0,
                voteAverage = 0.0
            )
        )
        // Define the behavior for the insertTrendingSeries method
        coEvery { localDataSource.insertTrendingSeries(any()) } returns Unit

        // Create an instance of the class being tested and pass the mock instance through the constructor
        val repository = MoviesAndSeriesRepositoryImpl(
            remoteDataSource,
            localDataSource,
            sharedPreferenceService
        )

        // Act
        repository.refreshTrendingSeries(series)

        // Assert
        coEvery { localDataSource.insertTrendingSeries(series.toSeriesDatabase()) } returns Unit
    }
}

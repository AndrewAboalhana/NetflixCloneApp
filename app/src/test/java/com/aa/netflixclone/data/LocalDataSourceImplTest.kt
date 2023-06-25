package com.aa.netflixclone.data

import com.aa.netflixclone.data.local.LocalDataSourceImpl
import com.aa.netflixclone.data.local.database.dao.MovieDao
import com.aa.netflixclone.data.local.database.dao.SeriesDao
import com.aa.netflixclone.data.local.database.entities.MovieDatabaseDto
import com.aa.netflixclone.data.local.database.entities.SeriesDatabaseDto
import com.aa.netflixclone.repository.dataSources.LocalDataSource
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class LocalDataSourceImplTest {

    private lateinit var localDataSource: LocalDataSource
    private lateinit var mockMovieDao: MovieDao
    private lateinit var mockSeriesDao: SeriesDao

    @Before
    fun setup() {
        mockMovieDao = mock(MovieDao::class.java)
        mockSeriesDao = mock(SeriesDao::class.java)

        localDataSource = LocalDataSourceImpl(mockMovieDao, mockSeriesDao)
    }

    @Test
    fun getTrendingMovies_shouldReturnFlowOfMovies() {
        // Arrange
        val expectedMovies = listOf(MovieDatabaseDto(id = 0,"spider",
            originalLanguage = "en", overview = "", imageUrl = "", date = ""))
        `when`(mockMovieDao.getTrendingMovies()).thenReturn(flowOf(expectedMovies))

        // Act
        val result: List<MovieDatabaseDto> = runBlocking {
            localDataSource.getTrendingMovies().single()
        }

        // Assert
        assertEquals(expectedMovies, result)
    }

    @Test
    fun insertTrendingMovies_shouldCallMovieDaoInsertTrendingMovies() = runBlockingTest {
        // Arrange
        val movies = listOf(MovieDatabaseDto(id = 0,"spider",
            originalLanguage = "en", overview = "", imageUrl = "", date = ""))

        // Act
        runBlocking {
            localDataSource.insertTrendingMovies(movies)
        }

        // Assert
        // Verify that the insertTrendingMovies function is called on the mockMovieDao
        verify(mockMovieDao).insertTrendingMovies(movies)
    }

    @Test
    fun getTrendingSeries_shouldReturnFlowOfSeries() {
        // Arrange
        val expectedSeries = listOf(SeriesDatabaseDto(id = 0, title = "spider", originalLanguage = "en",
            overview = "", imageUrl = "", date = "", popularity = 0.0, voteAverage = 0.0))
        `when`(mockSeriesDao.getTrendingSeries()).thenReturn(flowOf(expectedSeries))

        // Act
        val result: List<SeriesDatabaseDto> = runBlocking {
            localDataSource.getTrendingSeries().single()
        }

        // Assert
        assertEquals(expectedSeries, result)
    }

    @Test
    fun insertTrendingSeries_shouldCallSeriesDaoInsertTrendingSeries() = runBlockingTest {
        // Arrange
        val series = listOf(SeriesDatabaseDto(id = 0, title = "spider", originalLanguage = "en",
            overview = "", imageUrl = "", date = "", popularity = 0.0, voteAverage = 0.0))

        // Act
        localDataSource.insertTrendingSeries(series)

        // Assert
        // Verify that the insertTrendingSeries function is called once on the mockSeriesDao
        verify(mockSeriesDao, times(1)).insertTrendingSeries(series)
    }

}

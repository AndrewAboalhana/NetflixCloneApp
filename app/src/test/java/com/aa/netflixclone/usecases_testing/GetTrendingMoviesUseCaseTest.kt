package com.aa.netflixclone.usecases_testing

import com.aa.netflixclone.domain.models.movie.MovieEntity
import com.aa.netflixclone.domain.repositories.MoviesAndSeriesRepository
import com.aa.netflixclone.domain.usecases.GetTrendingMoviesUseCase
import com.aa.netflixclone.domain.usecases.ShouldCacheApiResponseUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetTrendingMoviesUseCaseTest {

    @Test
    fun `invoke should return trending movies from local repository`() = runBlocking {
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
        val repositoryMock = mockk<MoviesAndSeriesRepository>()
        val shouldCacheUseCaseMock = mockk<ShouldCacheApiResponseUseCase>()

        coEvery { shouldCacheUseCaseMock("trending_movies") } returns false
        coEvery { repositoryMock.getTrendingMoviesFromLocal() } returns flowOf(expectedMovies)

        val useCase = GetTrendingMoviesUseCase(repositoryMock, shouldCacheUseCaseMock)

        // Act
        val result = useCase.invoke().toList().flatten() // Flatten the list

        // Assert
        assertEquals(expectedMovies, result)
    }

    @Test
    fun `invoke should refresh local trending movies if cache is required`() = runBlocking {
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

        val repositoryMock = mockk<MoviesAndSeriesRepository>()
        val shouldCacheUseCaseMock = mockk<ShouldCacheApiResponseUseCase>()

        coEvery { shouldCacheUseCaseMock("trending_movies") } returns true
        coEvery { repositoryMock.getTrendingMovies("movie", "week") } returns expectedMovies
        coEvery { repositoryMock.refreshTrendingMovies(expectedMovies) } returns Unit
        coEvery { repositoryMock.getTrendingMoviesFromLocal() } returns flowOf(expectedMovies)

        val useCase = GetTrendingMoviesUseCase(repositoryMock, shouldCacheUseCaseMock)

        // Act
        val result = useCase.invoke().toList().flatten() // Flatten the list

        // Assert
        assertEquals(expectedMovies, result)
    }
}


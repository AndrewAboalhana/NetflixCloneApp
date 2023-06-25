package com.aa.netflixclone.usecases_testing

import com.aa.netflixclone.domain.models.movie.MovieDetailsEntity
import com.aa.netflixclone.domain.repositories.MoviesAndSeriesRepository
import com.aa.netflixclone.domain.usecases.GetMovieDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetMovieDetailsUseCaseTest {

    @Test
    fun `invoke should return the movie details`() = runBlocking {
        // Arrange
        val movieId = 1
        val expectedMovieDetails = MovieDetailsEntity(
            id = movieId,
            title = "Example Movie",
            overview = "This is an example movie",
            date = "2023-06-01",
            runtime = 120,
            imageUrl = "",
            adult = false,
            imdbId = "",
            video = false,
            voteAverage = 0.0
        )

        // Create a mock instance of MoviesAndSeriesRepository
        val repositoryMock = mockk<MoviesAndSeriesRepository>()

        // Define the behavior for the getMovieDetails method
        coEvery { repositoryMock.getMoviesDetails(movieId) } returns expectedMovieDetails

        // Create an instance of the GetMovieDetailsUseCase class and pass the mock repository through the constructor
        val useCase = GetMovieDetailsUseCase(repositoryMock)

        // Act
        val result = useCase(movieId)

        // Assert
        assertEquals(expectedMovieDetails, result)
    }
}

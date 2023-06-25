package com.aa.netflixclone.usecases_testing

import com.aa.netflixclone.domain.models.series.SeriesDetailsEntity
import com.aa.netflixclone.domain.repositories.MoviesAndSeriesRepository
import com.aa.netflixclone.domain.usecases.GetSeriesDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetSeriesDetailsUseCaseTest {

    @Test
    fun `invoke should return the series details`() = runBlocking {
        // Arrange
        val seriesId = 1
        val expectedSeriesDetails = SeriesDetailsEntity(
            id = seriesId,
            name =  "Example Series",
            overview = "This is an example series",
            firstAirDate = "2023-06-01",
            numberOfEpisodes = 10,
            numberOfSeasons = 2,
            adult = false,
            imageUrl = "",
            popularity = 0.0,
            seasons = emptyList(),
            type = "",
            voteAverage = 0.0
          )

        // Create a mock instance of MoviesAndSeriesRepository
        val repositoryMock = mockk<MoviesAndSeriesRepository>()

        // Define the behavior for the getSeriesDetails method
        coEvery { repositoryMock.getSeriesDetails(seriesId) } returns expectedSeriesDetails

        // Create an instance of the GetSeriesDetailsUseCase class and pass the mock repository through the constructor
        val useCase = GetSeriesDetailsUseCase(repositoryMock)

        // Act
        val result = useCase(seriesId)

        // Assert
        assertEquals(expectedSeriesDetails, result)
    }
}

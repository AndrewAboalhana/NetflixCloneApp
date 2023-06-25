package com.aa.netflixclone.usecases_testing

import com.aa.netflixclone.domain.models.series.SeriesEntity
import com.aa.netflixclone.domain.repositories.MoviesAndSeriesRepository
import com.aa.netflixclone.domain.usecases.GetTrendingSeriesUseCase
import com.aa.netflixclone.domain.usecases.ShouldCacheApiResponseUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetTrendingSeriesUseCaseTest {

    @Test
    fun `invoke should return trending series from local repository`() = runBlocking {
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

        val repositoryMock = mockk<MoviesAndSeriesRepository>()
        val shouldCacheUseCaseMock = mockk<ShouldCacheApiResponseUseCase>()

        coEvery { shouldCacheUseCaseMock("trending_series") } returns false
        coEvery { repositoryMock.getTrendingSeriesFromLocal() } returns flowOf(expectedSeries)

        val useCase = GetTrendingSeriesUseCase(repositoryMock, shouldCacheUseCaseMock)

        // Act
        val result = useCase.invoke().toList().flatten() // Flatten the list

        // Assert
        assertEquals(expectedSeries, result)
    }

    @Test
    fun `invoke should refresh local trending series if cache is required`() = runBlocking {
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

        val repositoryMock = mockk<MoviesAndSeriesRepository>()
        val shouldCacheUseCaseMock = mockk<ShouldCacheApiResponseUseCase>()

        coEvery { shouldCacheUseCaseMock("trending_series") } returns true
        coEvery { repositoryMock.getTrendingSeries() } returns expectedSeries
        coEvery { repositoryMock.refreshTrendingSeries(expectedSeries) } returns Unit
        coEvery { repositoryMock.getTrendingSeriesFromLocal() } returns flowOf(expectedSeries)

        val useCase = GetTrendingSeriesUseCase(repositoryMock, shouldCacheUseCaseMock)

        // Act
        val result = useCase.invoke().toList().flatten() // Flatten the list

        // Assert
        assertEquals(expectedSeries, result)
    }
}

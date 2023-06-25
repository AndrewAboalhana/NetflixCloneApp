package com.aa.netflixclone.usecases_testing

import com.aa.netflixclone.domain.repositories.MoviesAndSeriesRepository
import com.aa.netflixclone.domain.usecases.GetCurrentTimesTampUseCase
import com.aa.netflixclone.domain.usecases.RefreshLastCashingTimesTampUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class RefreshLastCachingTimestampUseCaseTest {

    @Test
    fun `invoke should save current timestamp for the given key`() = runBlockingTest {
        // Arrange
        val key = "test_key"
        val currentTime = 1624543200L // Example current timestamp

        val repositoryMock = mockk<MoviesAndSeriesRepository>()
        val getCurrentTimestampUseCaseMock = mockk<GetCurrentTimesTampUseCase>()

        coEvery { getCurrentTimestampUseCaseMock() } returns currentTime
        coEvery { repositoryMock.saveCachingTimeStamp(key, currentTime) } returns Unit // Return type of suspend function is Unit

        val useCase = RefreshLastCashingTimesTampUseCase(repositoryMock, getCurrentTimestampUseCaseMock)

        // Act
        useCase.invoke(key)

        // Assert
        coEvery { repositoryMock.saveCachingTimeStamp(key, currentTime) }
    }
}

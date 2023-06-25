package com.aa.netflixclone.usecases_testing

import com.aa.netflixclone.domain.repositories.MoviesAndSeriesRepository
import com.aa.netflixclone.domain.usecases.GetLastCashingTimeUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetLastCachingTimeUseCaseTest {

    @Test
    fun `invoke should return the last caching timestamp`() = runBlockingTest {
        // Arrange
        val key = "example_key"
        val expectedTimestamp = 1624556789000L // Example timestamp

        // Create a mock instance of MoviesAndSeriesRepository
        val repositoryMock = mockk<MoviesAndSeriesRepository>()

        // Define the behavior for the getLastCachingTimeStamp method
        coEvery { repositoryMock.getLastCachingTimeStamp(key) } returns expectedTimestamp

        // Create an instance of the GetLastCachingTimeUseCase class and pass the mock repository through the constructor
        val useCase = GetLastCashingTimeUseCase(repositoryMock)

        // Act
        val result = useCase(key)

        // Assert
        assertEquals(expectedTimestamp, result)
    }
}

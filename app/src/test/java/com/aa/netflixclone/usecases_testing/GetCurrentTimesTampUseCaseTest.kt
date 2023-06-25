package com.aa.netflixclone.usecases_testing

import com.aa.netflixclone.domain.usecases.GetCurrentTimesTampUseCase
import org.junit.Assert
import org.junit.Test
import java.util.Date

class GetCurrentTimesTampUseCaseTest {

    @Test
    fun `invoke should return current timestamp`() {
        // Arrange
        val currentTimestamp = System.currentTimeMillis()
        val useCase = GetCurrentTimesTampUseCase(Date(currentTimestamp))

        // Act
        val result = useCase.invoke()

        // Assert
        Assert.assertEquals(currentTimestamp, result)
    }
}
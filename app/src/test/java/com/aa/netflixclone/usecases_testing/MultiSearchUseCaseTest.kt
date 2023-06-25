package com.aa.netflixclone.usecases_testing

import com.aa.netflixclone.domain.models.search.MultiSearchEntity
import com.aa.netflixclone.domain.models.search.ResultEntity
import com.aa.netflixclone.domain.repositories.MoviesAndSeriesRepository
import com.aa.netflixclone.domain.usecases.MultiSearchUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class MultiSearchUseCaseTest {

    @Test
    fun `invoke should return multi-search entity from repository`() = runBlocking {
        // Arrange
        val query = "test"
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
        val expectedMultiSearchEntity = MultiSearchEntity(
            results = expectedSearch
        )

        val repositoryMock = mockk<MoviesAndSeriesRepository>()

        coEvery { repositoryMock.multiSearch(query) } returns expectedMultiSearchEntity

        val useCase = MultiSearchUseCase(repositoryMock)

        // Act
        val result = useCase.invoke(query)

        // Assert
        assertEquals(expectedMultiSearchEntity, result)
    }
}

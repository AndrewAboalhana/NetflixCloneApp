package com.aa.netflixclone.domain.usecases

import java.util.Date
import javax.inject.Inject

class GetCurrentTimesTampUseCase @Inject constructor(
    private val date: Date
) {
    operator fun invoke(): Long {
        return date.time
    }
}
package com.aa.netflixclone.domain.models.exceptions

import java.lang.Exception

sealed class NetworkException : Exception() {

    object NotFoundException :NetworkException()

    object ApiKeyExpiredException : NetworkException()

}


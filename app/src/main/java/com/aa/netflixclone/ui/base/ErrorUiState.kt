package com.aa.netflixclone.ui.base

sealed class ErrorUiState() {
    object InternalServerError : ErrorUiState()

}
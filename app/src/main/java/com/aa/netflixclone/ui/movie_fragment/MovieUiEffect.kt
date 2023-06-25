package com.aa.netflixclone.ui.movie_fragment

import com.aa.netflixclone.ui.base.BaseUiEffect

sealed interface MovieUiEffect : BaseUiEffect {

    data class ClickOnMovie(val movieId: Int) : MovieUiEffect
}
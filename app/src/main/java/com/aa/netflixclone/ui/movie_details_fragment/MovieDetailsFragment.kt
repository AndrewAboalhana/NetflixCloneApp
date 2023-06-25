package com.aa.netflixclone.ui.movie_details_fragment

import androidx.fragment.app.viewModels
import com.aa.netflixclone.R
import com.aa.netflixclone.databinding.FragmentMovieDetailsBinding
import com.aa.netflixclone.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment :
    BaseFragment<FragmentMovieDetailsBinding, MovieDetailsUiState,
            MovieDetailsUiEffect, MovieDetailsViewModel>() {

    override val layoutIdFragment = R.layout.fragment_movie_details

    override val viewModel: MovieDetailsViewModel by viewModels()

    override fun observeOnUIEffects() {
    }

    override fun handleUIEffect(uiEffect: MovieDetailsUiEffect) {
    }

}
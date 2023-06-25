package com.aa.netflixclone.ui.movie_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.aa.netflixclone.R
import com.aa.netflixclone.databinding.FragmentMovieBinding
import com.aa.netflixclone.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding,MoviesUiState,MovieUiEffect,MovieViewModel>() {
    override val layoutIdFragment = R.layout.fragment_movie

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val moviesAdapter = MovieAdapter(emptyList(),viewModel)
        binding.moviesRecyclerView.adapter = moviesAdapter
    }

    override val viewModel: MovieViewModel by viewModels()

    override fun observeOnUIEffects() {
        lifecycleScope.launch { viewModel.effect.collectLatest { handleUIEffect(it) } }
    }

    override fun handleUIEffect(uiEffect: MovieUiEffect) {
        when (uiEffect){
            is MovieUiEffect.ClickOnMovie -> onMovieClicked(uiEffect.movieId)
        }
    }

    private fun onMovieClicked(movieId: Int ) {
        val directions = MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment(movieId)
        findNavController().navigate(directions)
    }
}
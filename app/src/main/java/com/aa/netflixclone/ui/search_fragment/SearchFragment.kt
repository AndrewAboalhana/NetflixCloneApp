package com.aa.netflixclone.ui.search_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.aa.netflixclone.R
import com.aa.netflixclone.databinding.FragmentSearchBinding
import com.aa.netflixclone.ui.base.BaseFragment
import com.aa.netflixclone.ui.movie_fragment.MovieFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchUiState, SearchUiEffect, SearchViewModel>() {
    override val layoutIdFragment = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchAdapter = SearchAdapter(emptyList(), viewModel)
        binding.recyclerViewSearch.adapter = searchAdapter
    }

    override fun observeOnUIEffects() {
        lifecycleScope.launch { viewModel.effect.collectLatest { handleUIEffect(it) } }
    }

    override fun handleUIEffect(uiEffect: SearchUiEffect) {
        when (uiEffect) {
            is SearchUiEffect.ClickOnMovie -> onMovieClicked(uiEffect.movieId)
            is SearchUiEffect.ClickOnSeries -> onSeriesClicked(uiEffect.seriesId)
        }
    }

    private fun onMovieClicked(movieId: Int) {
        val directions =
            SearchFragmentDirections.actionSearchFragmentToMovieDetailsFragment(movieId)
        findNavController().navigate(directions)
    }

    private fun onSeriesClicked(seriesId: Int) {
        val directions =
            SearchFragmentDirections.actionSearchFragmentToSeriesDetailsFragment(seriesId)
        findNavController().navigate(directions)
    }
}
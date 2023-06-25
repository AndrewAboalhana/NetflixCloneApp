package com.aa.netflixclone.ui.series_fragment


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.aa.netflixclone.R
import com.aa.netflixclone.databinding.FragmentSeriesBinding
import com.aa.netflixclone.ui.base.BaseFragment
import com.aa.netflixclone.ui.movie_fragment.MovieFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SeriesFragment :
    BaseFragment<FragmentSeriesBinding, SeriesUiState, SeriesUiEffect, SeriesViewModel>() {
    override val layoutIdFragment = R.layout.fragment_series
    override val viewModel: SeriesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val seriesAdapter = SeriesAdapter(emptyList(),viewModel)
        binding.seriesRecyclerView.adapter = seriesAdapter
    }

    override fun observeOnUIEffects() {
        lifecycleScope.launch { viewModel.effect.collectLatest { handleUIEffect(it) } }
    }

    override fun handleUIEffect(uiEffect: SeriesUiEffect) {
        when(uiEffect){
            is SeriesUiEffect.ClickOnSeries -> onSeriesClicked(uiEffect.seriesId)
        }
    }

    private fun onSeriesClicked(seriesId: Int ) {
        val directions = SeriesFragmentDirections.actionSeriesFragmentToSeriesDetailsFragment(seriesId)
        findNavController().navigate(directions)
    }
}
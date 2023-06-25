package com.aa.netflixclone.ui.series_details_fragment

import androidx.fragment.app.viewModels
import com.aa.netflixclone.R
import com.aa.netflixclone.databinding.FragmentSeriesDetailsBinding
import com.aa.netflixclone.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesDetailsFragment :
    BaseFragment<FragmentSeriesDetailsBinding, SeriesDetailsUiState, SeriesDetailsUiEffect
            , SeriesDetailsViewModel>() {

    override val layoutIdFragment = R.layout.fragment_series_details

    override val viewModel: SeriesDetailsViewModel by viewModels()

    override fun observeOnUIEffects() {
    }

    override fun handleUIEffect(uiEffect: SeriesDetailsUiEffect) {
    }

}
package com.aa.netflixclone.ui


import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.aa.netflixclone.R
import com.aa.netflixclone.data.remote.APIService
import com.aa.netflixclone.data.remote.RemoteDataSourceImpl
import com.aa.netflixclone.databinding.ActivityMainBinding
import com.aa.netflixclone.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(){
    override val LOG_TAG: String = "TAG"
    private val navController by lazy { findNavController(R.id.nav_host_fragment_content_main) }
    override fun getLayoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.black)
        setSupportActionBar(binding.toolbar)
        initNavigationDestinationListener()
        setupActionBarWithNavController(navController)
        binding.bottomNav.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.movieFragment,
                R.id.searchFragment,
                R.id.seriesFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun initNavigationDestinationListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.movieFragment ,
                R.id.seriesFragment,
                R.id.searchFragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    binding.bottomNav.isVisible = true
                }
                else -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    binding.bottomNav.isVisible = false
                }
            }
        }
    }



}
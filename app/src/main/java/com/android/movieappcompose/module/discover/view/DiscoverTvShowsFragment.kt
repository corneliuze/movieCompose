package com.android.movieappcompose.module.discover.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.android.movieappcompose.module.commons.MovieLists
import com.android.movieappcompose.module.discover.vm.DiscoverTvShowsViewModel
import com.android.movieappcompose.rest.data.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverTvShowsFragment : Fragment() {

    private val discoverTvShowsViewModel: DiscoverTvShowsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    DiscoverMoviesContent()
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        discoverTvShowsViewModel.discoverTvShows()
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun DiscoverMoviesContent() {

        val movies by discoverTvShowsViewModel.movies.observeAsState(emptyList())
        MovieLists(movieList = movies, onItemClick = {
            navigateToDetailsPage(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        discoverTvShowsViewModel.discoverTvShows()
    }

    private fun navigateToDetailsPage(movie: Movie?) {
        val action =
            DiscoverTvShowsFragmentDirections.actionDiscoverFragToTvShowDetailsFragment(movie)
        view?.findNavController()?.navigate(action)
    }
}

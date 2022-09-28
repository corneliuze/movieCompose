package com.android.movieappcompose.module.toprated.view

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
import com.android.movieappcompose.databinding.FragmentTopRatedTvShowsBinding
import com.android.movieappcompose.module.commons.MovieLists
import com.android.movieappcompose.module.toprated.vm.TopRatedTvShowsViewModel
import com.android.movieappcompose.rest.data.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopRatedTvShowsFragment : Fragment() {

    private val viewModel: TopRatedTvShowsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    TopRatedMoviesContent()
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getTopRatedTvShows()
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun TopRatedMoviesContent() {
        val movies by viewModel.movies.observeAsState(emptyList())
        MovieLists(movieList = movies, onItemClick = {
            navigateToDetailsPage(it)
        })
    }


    private fun navigateToDetailsPage(movie: Movie?) {
        val action =
            TopRatedTvShowsFragmentDirections.actionTopRatedFragToTvShowDetailsFragment(movie)
        view?.findNavController()?.navigate(action)
    }
}
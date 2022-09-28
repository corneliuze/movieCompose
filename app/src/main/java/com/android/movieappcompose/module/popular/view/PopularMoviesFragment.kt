package com.android.movieappcompose.module.popular.view

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
import com.android.movieappcompose.databinding.FragmentPopularMoviesBinding
import com.android.movieappcompose.module.commons.MovieAppAdapter
import com.android.movieappcompose.module.commons.MovieLists
import com.android.movieappcompose.module.popular.vm.PopularMoviesViewModel
import com.android.movieappcompose.rest.data.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {

    private val viewModel: PopularMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    PopularMoviesContent()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPopularTvShows()
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun PopularMoviesContent() {
        val movies by viewModel.movies.observeAsState(emptyList())
        MovieLists(movieList = movies, onItemClick = {
            navigateToDetailsPage(it)
        })
    }

    private fun navigateToDetailsPage(movie: Movie?) {
        val action = PopularMoviesFragmentDirections.actionPopularFragToTvShowDetailsFragment(movie)
        view?.findNavController()?.navigate(action)
    }
}

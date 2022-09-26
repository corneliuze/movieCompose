package com.android.movieappcompose.module.toprated.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.movieappcompose.databinding.FragmentTopRatedTvShowsBinding
import com.android.movieappcompose.module.commons.MovieAppAdapter
import com.android.movieappcompose.module.toprated.vm.TopRatedTvShowsViewModel
import com.android.movieappcompose.rest.data.Movie
import com.android.movieappcompose.rest.data.Movies
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopRatedTvShowsFragment : Fragment() {

    private val viewModel: TopRatedTvShowsViewModel by viewModels()
    private lateinit var binding: FragmentTopRatedTvShowsBinding
    private val movieAdapter by lazy {
        MovieAppAdapter(MovieAppAdapter.OnclickListener {
            navigateToDetailsPage(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopRatedTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getTopRatedTvShows()
        setUpViews()
    }

    private fun setUpViews() {
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            if (movies != null) {
                showMovies(movies)
            } else {
                showEmptyScreen()
            }
        }
    }

    private fun showEmptyScreen() {
        // TODO("Not yet implemented")
    }

    private fun showMovies(movies: Movies) {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(
                context,
                3,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = movieAdapter.apply {
                submitList(movies.movies)
            }
        }
    }

    private fun navigateToDetailsPage(movie: Movie?) {
        val action =
            TopRatedTvShowsFragmentDirections.actionTopRatedFragToTvShowDetailsFragment(movie)
        view?.findNavController()?.navigate(action)
    }
}
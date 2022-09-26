package com.android.movieappcompose.module.popular.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.movieappcompose.databinding.FragmentPopularMoviesBinding
import com.android.movieappcompose.module.commons.MovieAppAdapter
import com.android.movieappcompose.module.popular.vm.PopularMoviesViewModel
import com.android.movieappcompose.module.toprated.view.TopRatedTvShowsFragmentDirections
import com.android.movieappcompose.rest.data.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {

    private val viewModel: PopularMoviesViewModel by viewModels()

    private lateinit var binding: FragmentPopularMoviesBinding
    private val movieAdapter by lazy {
        MovieAppAdapter(MovieAppAdapter.OnclickListener {
            navigateToDetailsPage(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getPopularTvShows()
        setUpViews()
    }

    private fun setUpViews() {
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
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
    }

    private fun navigateToDetailsPage(movie: Movie?) {
        val action = PopularMoviesFragmentDirections.actionPopularFragToTvShowDetailsFragment(movie)
        view?.findNavController()?.navigate(action)
    }
}

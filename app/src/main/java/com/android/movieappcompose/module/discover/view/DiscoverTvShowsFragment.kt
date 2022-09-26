package com.android.movieappcompose.module.discover.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.movieappcompose.R
import com.android.movieappcompose.databinding.FragmentDiscoverTvShowsBinding
import com.android.movieappcompose.module.commons.MovieAppAdapter
import com.android.movieappcompose.module.discover.vm.DiscoverTvShowsViewModel
import com.android.movieappcompose.module.toprated.view.TopRatedTvShowsFragmentDirections
import com.android.movieappcompose.rest.data.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverTvShowsFragment : Fragment() {

    private val discoverTvShowsViewModel: DiscoverTvShowsViewModel by viewModels()
    private lateinit var binding: FragmentDiscoverTvShowsBinding
    private val movieAdapter by lazy {
        MovieAppAdapter(MovieAppAdapter.OnclickListener {
            navigateToDetailsPage(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiscoverTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        discoverTvShowsViewModel.discoverTvShows()
        setUpViews()
    }

    private fun setUpViews() {
        discoverTvShowsViewModel.movies.observe(viewLifecycleOwner) { movies ->
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
        val action =
            DiscoverTvShowsFragmentDirections.actionDiscoverFragToTvShowDetailsFragment(movie)
        view?.findNavController()?.navigate(action)
    }
}

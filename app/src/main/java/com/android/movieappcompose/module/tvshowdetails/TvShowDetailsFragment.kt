package com.android.movieappcompose.module.tvshowdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.android.movieappcompose.R
import com.android.movieappcompose.databinding.FragmentTvShowDetailsBinding
import com.android.movieappcompose.rest.Urls
import com.android.movieappcompose.rest.data.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowDetailsFragment : Fragment() {

    private lateinit var binding: FragmentTvShowDetailsBinding
    private val args: TvShowDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val movie = args.movie
        setUpViews(movie)
    }

    private fun setUpViews(movie: Movie?) {
        binding.movieImageView.load(Urls.BASE_IMAGE_URL + movie?.backdropPath)
        binding.movieTitleTextView.text = movie?.name
        binding.movieAirDateTextView.text = "First Air Date : ${movie?.firstAirDate}"
        binding.movieOverviewTextView.text = movie?.overview
    }

}
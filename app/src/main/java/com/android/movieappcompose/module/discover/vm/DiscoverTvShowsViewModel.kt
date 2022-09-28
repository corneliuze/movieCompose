package com.android.movieappcompose.module.discover.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.movieappcompose.module.base.BaseViewModel
import com.android.movieappcompose.network_utils.LoadingStatus
import com.android.movieappcompose.repository.MovieRepository
import com.android.movieappcompose.repository.Result
import com.android.movieappcompose.rest.data.Movie
import com.android.movieappcompose.rest.data.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class DiscoverTvShowsViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    BaseViewModel() {

    var _movies = MutableLiveData<List<Movie>>()
    val movies : LiveData<List<Movie>>
    get() = _movies

    fun discoverTvShows() {
        _loadingState.value = LoadingStatus.Loading("Loading  TV Shows, Please Wait")
        movieRepository.discoverTvShows()
            .subscribeBy {
                when (it) {
                    is Result.Success -> {
                        _movies.value = it.data.movies
                        _loadingState.value = LoadingStatus.Success
                    }

                    is Result.Error -> {
                        _loadingState.value =  LoadingStatus.Error(it.errorCode, it.errorMessage)
                    }
                }
            }.run {
                CompositeDisposable().add(this)
            }
    }

}
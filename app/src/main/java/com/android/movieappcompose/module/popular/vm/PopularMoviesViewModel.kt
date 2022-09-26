package com.android.movieappcompose.module.popular.vm

import androidx.lifecycle.MutableLiveData
import com.android.movieappcompose.module.base.BaseViewModel
import com.android.movieappcompose.network_utils.LoadingStatus
import com.android.movieappcompose.repository.MovieRepository
import com.android.movieappcompose.repository.Result
import com.android.movieappcompose.rest.data.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel
@Inject constructor(private val movieRepository: MovieRepository) :
    BaseViewModel() {

    var movies = MutableLiveData<Movies>()


    fun getPopularTvShows() {
        _loadingState.value = LoadingStatus.Loading("Loading Popular Tv Shows, Please Wait")
        movieRepository.getPopularTvShows()
            .subscribeBy {
                when (it) {
                    is Result.Success -> {
                        movies.value = it.data
                        _loadingState.value = LoadingStatus.Success
                    }

                    is Result.Error -> {
                        _loadingState.value = LoadingStatus.Error(it.errorCode, it.errorMessage)
                    }
                }
            }.run {
                disposeBag.add(this)
            }
    }

}
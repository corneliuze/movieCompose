package com.android.movieappcompose.module.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.movieappcompose.network_utils.LoadingStatus
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val disposeBag = CompositeDisposable()


    protected var _loadingState = MutableLiveData<LoadingStatus?>()
    val loadingState: LiveData<LoadingStatus?>
        get() = _loadingState

    fun errorShown() {
        _loadingState.value = null
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }
}
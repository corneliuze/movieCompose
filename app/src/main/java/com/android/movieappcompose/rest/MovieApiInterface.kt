package com.android.movieappcompose.rest

import com.android.movieappcompose.rest.data.Movies
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterface {

    @GET(Urls.DISCOVER_TV_SHOW + "?")
    fun discoverMovieShow(@Query("api_key") apiKey: String = Urls.API_KEY): Single<Response<Movies>>

    @GET(Urls.SEARCH_TOP_TV_SHOW + "?")
    fun searchTopTvShow(@Query("api_key") apiKey: String = Urls.API_KEY): Single<Response<Movies>>

    @GET(Urls.GET_POPULAR_TV_SHOW + "?")
    fun getPopularTvShow(@Query("api_key") apiKey: String = Urls.API_KEY): Single<Response<Movies>>

    @GET(Urls.GET_TOP_RATED_TV_SHOW + "?")
    fun getTopRatedTvShow(@Query("api_key") apiKey: String = Urls.API_KEY): Single<Response<Movies>>

}
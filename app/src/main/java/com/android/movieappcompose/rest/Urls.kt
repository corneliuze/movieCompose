package com.android.movieappcompose.rest

interface Urls {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = "530409b67fad675e7ee11b7fb566d42e"
        const val SEARCH_TOP_TV_SHOW = "search/tv"
        const val DISCOVER_TV_SHOW = "discover/tv"
        const val GET_POPULAR_TV_SHOW = "tv/popular"
        const val GET_TOP_RATED_TV_SHOW = "tv/top_rated"
    }
}
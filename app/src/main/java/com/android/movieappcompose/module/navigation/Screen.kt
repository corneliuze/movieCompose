package com.android.movieappcompose.module.navigation

sealed class Screen (val route : String) {
    object DiscoverMovieScreen : Screen("discoverMovieScreen")
    object PopularMovieScreen : Screen("popularMovieScreen")
    object TopRatedMovieScreen : Screen("topRatedMovieScreen")
    object MovieDetailsScreen : Screen("movieDetailsScreen")
}

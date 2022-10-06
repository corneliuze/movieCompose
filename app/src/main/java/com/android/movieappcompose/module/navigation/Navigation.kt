package com.android.movieappcompose.module.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.movieappcompose.module.discover.view.DiscoverTvShowsFragment

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.DiscoverMovieScreen.route){
        composable(route = Screen.DiscoverMovieScreen.route) {navBackStackEntry ->


        }
    }
}
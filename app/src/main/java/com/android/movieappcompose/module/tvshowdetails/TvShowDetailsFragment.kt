package com.android.movieappcompose.module.tvshowdetails

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.fragment.navArgs
import coil.compose.AsyncImage
import com.android.movieappcompose.rest.Urls.Companion.BASE_IMAGE_URL
import com.android.movieappcompose.rest.data.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowDetailsFragment : Fragment() {

    private val args: TvShowDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    MovieDetailsContentWrapper(args.movie)
                }
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    private fun MovieDetailsContentWrapper(movie: Movie?) {
        Scaffold(
            topBar = {},
        ) {
            MovieDetailsContent(movie)
        }
    }

    private @Composable
    fun MovieDetailsContent(movie: Movie?) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(12.dp))

                Card(
                    modifier = Modifier.size(
                        width = 200.dp,
                        height = 250.dp
                    ), elevation = 12.dp, shape = RoundedCornerShape(12.dp)
                ) {
                    AsyncImage(
                        model = BASE_IMAGE_URL + movie?.backdropPath,
                        contentDescription = "Movie Backdrop",
                        contentScale = ContentScale.FillBounds,
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = movie?.name ?: "No Movie Title",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Movie first Air Date ${movie?.firstAirDate}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.primaryVariant
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    modifier = Modifier
                        .padding(start = 12.dp, end = 12.dp),
                    text = movie?.overview ?: "",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colors.primaryVariant
                )

            }
        }
    }
}
package com.android.movieappcompose.module.commons

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.android.movieappcompose.rest.Urls
import com.android.movieappcompose.rest.data.Movie

@ExperimentalFoundationApi
@Composable
fun MovieLists(
    movieList: List<Movie>,
    onItemClick: (Movie) -> Unit
) {
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        items(movieList) { movie ->
            MovieItem(movie, onItemClick)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MovieItem(movie: Movie, onItemClick: (Movie) -> Unit) {
    Card(
        modifier = Modifier
            .height(170.dp)
            .padding(5.dp)
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onItemClick(movie) },
                indication = null,
            ),
        shape = RoundedCornerShape(3.dp)
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = Urls.BASE_IMAGE_URL + movie.backdropPath,
                contentDescription = "Movie Backdrop",
                contentScale = ContentScale.FillBounds,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                color = Color.Black,
                text = movie.name ?: ""
            )
        }
    }

}

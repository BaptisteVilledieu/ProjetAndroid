package fr.baptistevilledieu.monprofil


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun Films(viewModel: MainViewModel) {
        val movies by viewModel.movies.collectAsState()

        LazyVerticalGrid(
                columns = GridCells.Fixed(count=2)
        ) {
                items(movies.results) { movie ->
                        Card(modifier = Modifier
                                .padding(12.dp)
                                .fillMaxSize(), elevation = 8.dp, shape = RoundedCornerShape(20.dp)) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        val url = "https://image.tmdb.org/t/p/w780/"
                                        AsyncImage(
                                                model = url + movie.backdrop_path,
                                                contentDescription = movie.title
                                        )
                                        Text(
                                                text = movie.title,
                                                fontWeight = FontWeight.SemiBold,
                                                textAlign = TextAlign.Center
                                        )
                                        Text(
                                                text = movie.release_date,
                                                fontStyle = FontStyle.Italic,
                                                textAlign = TextAlign.Center,
                                        )
                                }
                        }
                }
        }
        
        Scaffold() {
                
        }
}

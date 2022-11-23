package fr.baptistevilledieu.monprofil


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun Films(viewModel: MainViewModel) {
        val movies by viewModel.movies.collectAsState()

        var text by remember { mutableStateOf(TextFieldValue("")) }

        Scaffold(topBar = {
                TopAppBar(backgroundColor = Color.Blue, contentColor = Color.White ) {
                        Row(){
                                TextField(value = text, onValueChange = {text = it})
                        }
                        IconButton(onClick = { if (text.text == ""){
                                viewModel.getFilmsInitiaux()
                        }else{
                                viewModel.rechercheFilms(text.text)
                        }
                        }) {
                                Icon(painter = painterResource(id = R.drawable.iconrecherche), contentDescription ="icon de recherche", modifier = Modifier.size(30.dp) )
                        }
                        IconButton(onClick = { viewModel.getFilmsInitiaux() }) {
                                Icon(painter = painterResource(id = R.drawable.iconrevenirenarriere), contentDescription ="icon pour revenir en arrière", modifier = Modifier.size(30.dp) )
                        }
                }
        }) {

                LazyVerticalGrid(
                        columns = GridCells.Fixed(count = 2)
                ) {
                        items(movies.results) { movie ->
                                Card(
                                        modifier = Modifier
                                                .padding(12.dp)
                                                .fillMaxSize(),
                                        elevation = 8.dp,
                                        shape = RoundedCornerShape(20.dp)
                                ) {
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
        }
}


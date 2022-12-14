package fr.baptistevilledieu.monprofil

import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun Series(viewModel: MainViewModel, navController: NavController) {
    val series by viewModel.series.collectAsState()

    var text by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Blue, contentColor = Color.White ) {
            Row(){
                TextField(value = text, onValueChange = {text = it})
            }
            IconButton(onClick = { if (text.text == ""){
                viewModel.getSeriesInitiales()
            }else{
                viewModel.rechercheSeries(text.text)
            }
            }) {
                Icon(painter = painterResource(id = R.drawable.iconrecherche), contentDescription ="icon de recherche", modifier = Modifier.size(30.dp) )
            }
            IconButton(onClick = { viewModel.getSeriesInitiales() }) {
                Icon(painter = painterResource(id = R.drawable.iconrevenirenarriere), contentDescription ="icon pour revenir en arrière", modifier = Modifier.size(30.dp) )
            }
        }
    }) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2)
        ) {
            items(series.results) { serie ->
                Card(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxSize().clickable { navController.navigate("detailsSeries") },
                    elevation = 8.dp,
                    shape = RoundedCornerShape(20.dp),
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        val url = "https://image.tmdb.org/t/p/w780/"
                        AsyncImage(
                            model = url + serie.backdrop_path,
                            contentDescription = serie.name
                        )
                        Text(
                            text = serie.name,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = serie.first_air_date,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}
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
fun Acteurs(viewModel: MainViewModel) {
    val actors by viewModel.actors.collectAsState()

    var text by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Blue, contentColor = Color.White ) {
            Row(){
                TextField(value = text, onValueChange = {text = it})
            }
            IconButton(onClick = { if (text.text == ""){
                viewModel.getActeursInitiaux()
            }else{
                viewModel.rechercheActeurs(text.text)
            }
            }) {
                Icon(painter = painterResource(id = R.drawable.iconrecherche), contentDescription ="icon de recherche", modifier = Modifier.size(30.dp) )
            }
            IconButton(onClick = { viewModel.getActeursInitiaux() }) {
                Icon(painter = painterResource(id = R.drawable.iconrevenirenarriere), contentDescription ="icon pour revenir en arrière", modifier = Modifier.size(30.dp) )
            }
        }
    }) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2)
        ) {
            items(actors.results) { actor ->
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
                            model = url + actor.profile_path,
                            contentDescription = actor.name
                        )
                        Text(
                            text = actor.name,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

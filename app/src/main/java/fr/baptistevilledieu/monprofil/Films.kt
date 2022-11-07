package fr.baptistevilledieu.monprofil

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController

@Composable
fun Films(viewModel: MainViewModel){
        val movies by viewModel.movies.collectAsState()

        LazyColumn {
                items(movies.results) { movie ->
                        Text(text = movie.title)
                }
        }
}
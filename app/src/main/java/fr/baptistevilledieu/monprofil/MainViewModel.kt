package fr.baptistevilledieu.monprofil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainViewModel:ViewModel() {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val service = retrofit.create(TmdbAPI::class.java)

    val movies = MutableStateFlow(TmdbMovieResult())

    fun getFilmsInitiaux(){
            viewModelScope.launch {
                movies.value = service.lastmovies("a70fdcaf6041b798cfa437ee72337ed1")
        }
    }

    init {
        getFilmsInitiaux()
    }
}
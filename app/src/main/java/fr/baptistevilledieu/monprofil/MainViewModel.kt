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
    val series = MutableStateFlow(TmdbSerieResult())
    val actors = MutableStateFlow(TmdbActorResult())

    fun getFilmsInitiaux(){
            viewModelScope.launch {
                movies.value = service.lastMovies("a70fdcaf6041b798cfa437ee72337ed1")
        }
    }

    fun rechercheFilms(search : String){
        viewModelScope.launch {
            movies.value = service.searchMovies(search,"a70fdcaf6041b798cfa437ee72337ed1")
        }
    }

    fun getSeriesInitiales(){
        viewModelScope.launch {
            series.value = service.lastSeries("a70fdcaf6041b798cfa437ee72337ed1")
        }
    }

    fun rechercheSeries(search : String){
        viewModelScope.launch {
            series.value = service.searchSeries(search,"a70fdcaf6041b798cfa437ee72337ed1")
        }
    }

    fun getActeursInitiaux(){
        viewModelScope.launch {
            actors.value = service.lastActors("a70fdcaf6041b798cfa437ee72337ed1")
        }
    }

    fun rechercheActeurs(search : String){
        viewModelScope.launch {
            actors.value = service.searchActors(search,"a70fdcaf6041b798cfa437ee72337ed1")
        }
    }

    init {
        getFilmsInitiaux()
        getSeriesInitiales()
        getActeursInitiaux()
    }
}
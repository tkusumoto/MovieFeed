package com.kussuue.moviefeed.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.kussuue.moviefeed.data.Movie
import com.kussuue.moviefeed.repository.MovieRepository
import com.kussuue.moviefeed.util.LoadStatus

class MainViewModel(private val app: Application) : AndroidViewModel(app) {

    private val repository: MovieRepository by lazy {
        MovieRepository(app)
    }

//    val movieList = MutableLiveData<List<Movie>>()
//
//    fun getMovie(searchWord: String) {
//        searchWord?.let {
//            viewModelScope.launch {
//                movieList.value = repository.getMovie(searchWord)
//            }
//        }
//    }

    val searchWord = MutableLiveData<String?>()

    fun getState(): LiveData<LoadStatus> = repository.loadStatus

    fun getMovie(word: String?){
        searchWord.value = word
    }
    val movieList: LiveData<List<Movie>> = Transformations.switchMap(searchWord){ word ->
        word?.let {
            liveData {
                emit(repository.getMovie(it))
            }
        }
    }

}
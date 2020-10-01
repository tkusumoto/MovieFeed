package com.kussuue.moviefeed.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.kussuue.moviefeed.data.Movie
import com.kussuue.moviefeed.network.MovieApiService
import com.kussuue.moviefeed.util.LoadStatus
import com.kussuue.moviefeed.util.makeToast

class MovieRepository(private val context: Context) {

    private val movieApiService: MovieApiService by lazy {
        MovieApiService.createApiService()
    }

    val loadStatus = MutableLiveData<LoadStatus>()

    suspend fun getMovie(searchWord: String): List<Movie> {
        var returnList = emptyList<Movie>()
        loadStatus.value = LoadStatus.LOADING

        try {
            val response = movieApiService.getMovie(searchString = searchWord)
            if (response.isSuccessful) {
                loadStatus.value = LoadStatus.DONE
                returnList = response.body()!!.movieList
            } else {
                loadStatus.value = LoadStatus.ERROR
                makeToast(context, response.message())
            }
        } catch (e: Throwable) {
            loadStatus.value = LoadStatus.ERROR
            makeToast(context, "Exception: ${e.message}")
        }

        return returnList
    }
}
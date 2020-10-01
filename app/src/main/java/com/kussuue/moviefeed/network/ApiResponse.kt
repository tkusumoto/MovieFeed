package com.kussuue.moviefeed.network

import com.kussuue.moviefeed.data.Movie
import com.squareup.moshi.Json

data class ApiResponse (
    @Json(name = "items") val movieList: List<Movie>
)
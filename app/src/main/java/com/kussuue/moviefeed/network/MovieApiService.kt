package com.kussuue.moviefeed.network

import com.kussuue.moviefeed.util.API_KEY
import com.kussuue.moviefeed.util.BASE_URL
import com.kussuue.moviefeed.util.PART
import com.kussuue.moviefeed.util.TYPE
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    companion object {
        fun createApiService(): MovieApiService{
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(MovieApiService::class.java)
        }
    }

    // e.x.
    //https://www.googleapis.com/youtube/v3/search?type=video&part=snippet&q=SEARCHWORD&key=APIKEY
    @GET("search")
    suspend fun getMovie(
        @Query("type") type: String = TYPE,
        @Query("part") part: String = PART,
        @Query("q") searchString: String,
        @Query("key") apiKey: String = API_KEY
    ) : Response<ApiResponse>
}
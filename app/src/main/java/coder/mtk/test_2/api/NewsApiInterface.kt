package coder.mtk.test_2.api

import coder.mtk.test_2.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiInterface {

    @GET("top-headlines")
    fun getTopHeadLine (
        @Query("country") country : String,
        @Query("apiKey") apiKey : String,
        @Query("category") category : String,
    ): Call<News>

}
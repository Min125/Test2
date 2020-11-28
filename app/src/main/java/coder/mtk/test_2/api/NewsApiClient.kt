package coder.mtk.test_2.api

import coder.mtk.test_2.model.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiClient {
    private val BASE_URL = "https://newsapi.org/v2/"

    private val apiInterface : NewsApiInterface

    init {
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface = retrofit.create(NewsApiInterface::class.java)
    }

    fun getNewsData (category : String) : Call<News>{
        return apiInterface.getTopHeadLine("us","8f4b1cee839b43ab80645baee4a33881",category)
    }

}
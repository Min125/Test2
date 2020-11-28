package coder.mtk.test_2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import coder.mtk.test_2.api.NewsApiClient
import coder.mtk.test_2.model.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel(){

    private var newsData : MutableLiveData<News> = MutableLiveData()
    private var progressBar : MutableLiveData<Boolean> = MutableLiveData()

    fun getNewsData () : LiveData<News> = newsData
    fun getProgress () : LiveData<Boolean> = progressBar


    fun loadNews(category: String) {
        val apiClient = NewsApiClient()
        val apiCall = apiClient.getNewsData(category)

        apiCall.enqueue(object : Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
//                Log.d("NewsJsonData>>", response.body().toString())
                newsData.value = response.body()
                progressBar.value = false
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("NewsJsonError>>", t.toString())
                progressBar.value = true
            }

        })
    }
}
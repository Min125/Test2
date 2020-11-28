package coder.mtk.test_2

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coder.mtk.test_2.adapter.NewsAdapter
import coder.mtk.test_2.model.Article
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,  NewsAdapter.ClickListener{

    lateinit var newsViewModel : NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setTitle("NewsApi (Test-2) ")

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        newsViewModel.loadNews("general")
        newsAdapter = NewsAdapter()

        recyclerNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }

        newsViewModel.getNewsData().observe(
            this, Observer {
                newsAdapter.updateNewsList(it.articles)
            }
        )

        newsViewModel.getProgress().observe(
            this, Observer {
                progressBar.visibility = if (it){
                    View.VISIBLE
                }else{
                    View.INVISIBLE
                }
            }
        )

        val dataList : Array<String> = arrayOf("general","business","entertainment","health","science","sports","technology")
        var adapter = this.let { ArrayAdapter(it, R.layout.list_item,dataList) }
        spinnerCategory.adapter = adapter

        spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, postion: Int, p3: Long) {
                if(postion.equals(0)){
                    progressBar.visibility = View.VISIBLE
                    newsViewModel.loadNews("general")
                    newsViewModel.getNewsData().observe(
                        this@MainActivity, Observer {
                            newsAdapter.updateNewsList(it.articles)
                        }
                    )

                }else if (postion.equals(1)){
                    newsViewModel.loadNews("business")
                    newsViewModel.getNewsData().observe(
                        this@MainActivity, Observer {
                            newsAdapter.updateNewsList(it.articles)
                        }
                    )
                }
                else if (postion.equals(2)){
                    newsViewModel.loadNews("entertainment")
                    newsViewModel.getNewsData().observe(
                        this@MainActivity, Observer {
                            newsAdapter.updateNewsList(it.articles)
                        }
                    )
                }
                else if (postion.equals(3)){
                    newsViewModel.loadNews("health")
                    newsViewModel.getNewsData().observe(
                        this@MainActivity, Observer {
                            newsAdapter.updateNewsList(it.articles)
                        }
                    )
                }
                else if (postion.equals(4)){
                    newsViewModel.loadNews("science")
                    newsViewModel.getNewsData().observe(
                        this@MainActivity, Observer {
                            newsAdapter.updateNewsList(it.articles)
                        }
                    )
                }
                else if (postion.equals(5)){
                    newsViewModel.loadNews("sports")
                    newsViewModel.getNewsData().observe(
                        this@MainActivity, Observer {
                            newsAdapter.updateNewsList(it.articles)
                        }
                    )
                }
                else if (postion.equals(6)){
                    newsViewModel.loadNews("technology")
                    newsViewModel.getNewsData().observe(
                        this@MainActivity, Observer {
                            newsAdapter.updateNewsList(it.articles)
                        }
                    )
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        newsAdapter.onClickListener(this)

    }

    override fun onClick(newList: Article) {
        var intent = Intent(this,DetailNewsActivity2::class.java)
        intent.putExtra("title",newList.title)
        intent.putExtra("publishedAt",newList.publishedAt)
        intent.putExtra("content",newList.content)
        intent.putExtra("urlToImage",newList.urlToImage)
        startActivity(intent)
    }


}

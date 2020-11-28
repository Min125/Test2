package coder.mtk.test_2.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coder.mtk.test_2.R
import coder.mtk.test_2.model.Article
import coder.mtk.test_2.model.News
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_list.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    private var newsList: List<Article> = ArrayList()

    private var clickListener : ClickListener? = null

    fun onClickListener (clickListener: ClickListener){
        this.clickListener = clickListener
    }

    fun updateNewsList (newsList : List<Article>){
        this.newsList = newsList
        notifyDataSetChanged()
    }

    inner class NewsViewHolder (itemView:View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        lateinit var article:Article

        fun bind(article: Article){
            this.article=article
            Log.d("title>>>",article.title)
            if (!(article.title.isNullOrEmpty() && article.urlToImage.isNullOrEmpty())){
                itemView.txtTitleList.text = article.title
                Picasso.get()
                    .load(article.urlToImage)
                    .placeholder(R.drawable.ic_baseline_image_24)
                    .fit()
                    .into(itemView.imageList)
            }
        }

        override fun onClick(p0: View?) {
            clickListener?.onClick(article)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    interface ClickListener {
        fun onClick(newList : Article)
    }

}
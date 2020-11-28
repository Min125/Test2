package coder.mtk.test_2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_news2.*
import kotlinx.android.synthetic.main.item_list.view.*
import java.text.SimpleDateFormat

class DetailNewsActivity2 : AppCompatActivity() {
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news2)

        val title : String? = intent.getStringExtra("title")
        val publishedAt : String? = intent.getStringExtra("publishedAt")
        val content : String? = intent.getStringExtra("content")
        val urlToImage : String? = intent.getStringExtra("urlToImage")

        txtTitle_DetailPage.text = title
        Picasso.get()
            .load(urlToImage)
            .placeholder(R.drawable.ic_baseline_image_24)
            .fit()
            .into(img_DetailPage)

        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
        val output: String = formatter.format(parser.parse(publishedAt))

        txtDate_DetailPage.text = output
        txtContent_DetailPage.text = content

    }
}
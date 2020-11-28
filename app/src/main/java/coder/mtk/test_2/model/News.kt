package coder.mtk.test_2.model

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
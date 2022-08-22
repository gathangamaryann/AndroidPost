package dev.maryann.myposts

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dev.maryann.myposts.databinding.ActivityCommentactivtyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class CommentActivity : AppCompatActivity() {
    var postId = 0
    lateinit var binding: ActivityCommentactivtyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentactivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obtainPostId()
        fetchPostbyId()
        fetchComments()
        setuptoolbar()
    }

    fun obtainPostId() {
        postId = intent.extras?.getInt("POST_ID") ?: 0
    }
    fun setuptoolbar(){
        setSupportActionBar(binding.tbcomments)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    fun fetchPostbyId() {
        val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.getPostsById(postId)
        request.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    var post = response.body()
                    binding.tvPostTitle.text = post?.title
                    binding.tvPostBody.text = post?.body
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun fetchComments() {
        val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.getComments()
        request.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                var comment = response.body()
                if (response.isSuccessful) {
                    if (comment != null) {
                        displayComment(comment)
                    }
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

        })

    }

    fun displayComment(comment: List<Comment>){
        var adapter = CommentRvadapter(comment)
        binding.rvcomments.layoutManager=LinearLayoutManager(this)
        binding.rvcomments.adapter = adapter

    }
}
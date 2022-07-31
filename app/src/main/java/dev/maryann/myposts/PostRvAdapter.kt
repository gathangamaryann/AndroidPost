package dev.maryann.myposts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.maryann.myposts.databinding.PostListItemBinding

class PostRvAdapter(var postlist:List<Post>): RecyclerView.Adapter<postViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): postViewHolder{
        var binding =PostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return postViewHolder(binding)
    }

    override fun onBindViewHolder(holder: postViewHolder, position: Int) {
        var currentPost = postlist.get(position)
        val postBinding = holder.binding
        postBinding.tvUserid.text = currentPost.userId.toString()
        postBinding.tvId.text = currentPost.id.toString()
        postBinding.tvTitle.text = currentPost.title
        postBinding.tvBody.text = currentPost.body


    }

    override fun getItemCount(): Int {
        return postlist.size


}
    }
    class postViewHolder(var binding:PostListItemBinding):RecyclerView.ViewHolder(binding.root){


    }



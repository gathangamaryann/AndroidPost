package dev.maryann.myposts


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.maryann.myposts.databinding.PostListItemBinding


class CommentRvadapter(var commentlist: List<Comment>):
    RecyclerView.Adapter<CommentRvadapter.CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder{
        var bindingView = PostListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(bindingView)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        var currentPost = commentlist.get(position)

        var context = holder.itemView.context
        holder.bindingView.cvPost.setOnClickListener {
            val  intent = Intent(context,CommentActivity::class.java)
            intent.putExtra("COMMENT_ID", currentPost.id)
            context.startActivity(intent)
        }

        with(holder.bindingView){

            tvId.text = currentPost.name.toString()
            tvBody.text = currentPost.body.toString()
            tvTitle.text = currentPost.id.toString()
            tvTitle.text=currentPost.email.toString()

        }

    }

    override fun getItemCount(): Int {
        return commentlist.size
    }


    class CommentViewHolder(var bindingView:PostListItemBinding):
        RecyclerView.ViewHolder(bindingView.root){

    }
}




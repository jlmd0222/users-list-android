package jlmd.dev.android.pruebaceiba.views.postsuser.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import jlmd.dev.android.pruebaceiba.R
import jlmd.dev.android.pruebaceiba.core.model.Post
import jlmd.dev.android.pruebaceiba.databinding.PostViewItemBinding

class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var items: List<Post> = listOf()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.post_view_item, parent, false)

        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = items[position]

        with(holder){
            binding.postTitleTv.text = post.title
            binding.postBodyTv.text = post.body
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class PostViewHolder(view: View): ViewHolder(view) {
        val binding = PostViewItemBinding.bind(view)
    }
}
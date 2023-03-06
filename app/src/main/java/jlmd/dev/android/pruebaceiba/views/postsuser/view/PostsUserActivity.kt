package jlmd.dev.android.pruebaceiba.views.postsuser.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import jlmd.dev.android.pruebaceiba.core.model.Post
import jlmd.dev.android.pruebaceiba.databinding.ActivityPostsUserBinding
import jlmd.dev.android.pruebaceiba.views.postsuser.viewmodel.PostsUserViewModel
import jlmd.dev.android.pruebaceiba.views.postsuser.model.PostsUserViewState
import jlmd.dev.android.pruebaceiba.views.users.model.UserItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsUserActivity : AppCompatActivity() {

    private val viewModel: PostsUserViewModel by viewModel()
    private lateinit var binding: ActivityPostsUserBinding
    private val adapter = PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getUser()
        bindObservers()
        binding.postsRecycler.adapter = adapter
    }

    private fun getUser() {
        val userId = intent.getIntExtra("user_id", 0)
        viewModel.getUserById(userId)
        viewModel.getPostByUser(userId)
    }

    private fun bindObservers() {
        viewModel.user.observe(this) {
            showUser(it)
        }

        viewModel.postsViewState.observe(this) { postsViewState ->
            when (postsViewState) {
                is PostsUserViewState.Loading -> showLoading(true)
                is PostsUserViewState.ShowPosts -> showPostsUser(postsViewState.posts)
                is PostsUserViewState.Error -> showMessageError()
            }
        }
    }

    private fun showUser(userItem: UserItem) {
        binding.nameUserTv.text = userItem.name
        binding.emailUserTv.text = userItem.email
        binding.phoneUserTv.text = userItem.phone
    }

    private fun showPostsUser(posts: List<Post>) {
        binding.postsRecycler.visibility = View.VISIBLE
        adapter.items = posts
        showLoading(false)
    }

    private fun showLoading(show: Boolean) {
        if(show)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.GONE
    }

    private fun showMessageError(){
        binding.postsRecycler.visibility = View.GONE
        Toast.makeText(this, "Ocurrio un error al consultar los posts del usuario", Toast.LENGTH_SHORT).show()
    }
}
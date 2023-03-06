package jlmd.dev.android.pruebaceiba.views.users.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import jlmd.dev.android.pruebaceiba.databinding.ActivityMainBinding
import jlmd.dev.android.pruebaceiba.views.postsuser.view.PostsUserActivity
import jlmd.dev.android.pruebaceiba.views.users.model.UserItem
import jlmd.dev.android.pruebaceiba.views.users.model.UsersViewState
import jlmd.dev.android.pruebaceiba.views.users.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindListeners()
        bindObservers()
    }

    private fun bindObservers() {
        viewModel.usersListViewState.observe(this) { viewState ->
            when(viewState) {
                is UsersViewState.Loading -> showLoading(true)
                is UsersViewState.ShowUsers -> showList(viewState.usersList)
                is UsersViewState.NoUsersFound -> showEmptyList()
                is UsersViewState.Error -> showMessageError()
                else -> {}
            }
        }

        viewModel.foundUsersByQuery.observe(this) {
            if (it)
                binding.noResultsFoundTextView.visibility = View.GONE
            else
                binding.noResultsFoundTextView.visibility = View.VISIBLE
        }
    }

    private fun showEmptyList() {
        showLoading(false)
        binding.noResultsFoundTextView.visibility = View.VISIBLE
        binding.usersRecycler.visibility = View.GONE
    }

    private fun bindListeners() {
        binding.searchUserEditText.addTextChangedListener(textWatcher)

        adapter.userListener =
            object : UserAdapter.UserListener {
                override fun onShowUser(user: UserItem) {
                    val intent = Intent(this@MainActivity, PostsUserActivity::class.java)
                    intent.putExtra("user_id", user.id)
                    startActivity(intent)
                }
            }

        binding.usersRecycler.adapter = adapter
    }

    private fun showMessageError(){
        Toast.makeText(this, "Ocurrio un error al consultar los usuarios", Toast.LENGTH_SHORT).show()
        binding.usersRecycler.visibility = View.GONE
        binding.noResultsFoundTextView.visibility = View.GONE
    }

    private fun showLoading(show: Boolean){
        if (show)
            binding.progressBar.visibility = View.VISIBLE
        else
            binding.progressBar.visibility = View.GONE
    }

    private fun showList(usersList: List<UserItem>) {
        binding.usersRecycler.visibility = View.VISIBLE
        binding.noResultsFoundTextView.visibility = View.GONE

        binding.usersRecycler.scheduleLayoutAnimation()
        adapter.items = usersList

        showLoading(false)
    }

    private val textWatcher: TextWatcher = object : TextWatcher {
        private var timer = Timer()
        private val DELAY: Long = 200L
        private var query = ""

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

        override fun onTextChanged(query: CharSequence?, start: Int, before: Int, count: Int) {
            this.query = query.toString()
        }

        override fun afterTextChanged(s: Editable?) {
            timer.cancel()
            timer = Timer()
            timer.schedule(object : TimerTask() {
                override fun run() {
                    viewModel.filterUsers(query)
                }
            }, DELAY)
        }
    }
}
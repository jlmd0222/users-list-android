package jlmd.dev.android.pruebaceiba.views.users.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jlmd.dev.android.pruebaceiba.R
import jlmd.dev.android.pruebaceiba.databinding.UserViewItemBinding
import jlmd.dev.android.pruebaceiba.views.users.model.UserItem

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var items: List<UserItem> = listOf()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    var userListener: UserListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.user_view_item, parent, false)

        return UserViewHolder(view, userListener)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = items[position]

        with(holder){
            setListener(user)
            binding.userName.text = user.name
            binding.userPhone.text = user.phone
            binding.userEmail.text = user.email
        }
    }

    override fun getItemCount() = items.size

    inner class UserViewHolder(view: View, userListener: UserListener?): RecyclerView.ViewHolder(view){
        val binding = UserViewItemBinding.bind(view)
        private val listener = userListener

        fun setListener(user: UserItem){
            binding.btnPosts.setOnClickListener {
                this.listener?.onShowUser(user)
            }
        }
    }

    interface UserListener {
        fun onShowUser(user: UserItem)
    }
}
package jlmd.dev.android.pruebaceiba.views.postsuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jlmd.dev.android.pruebaceiba.core.usecases.GetPostsByUserUseCase
import jlmd.dev.android.pruebaceiba.core.usecases.GetUserByIdUseCase
import jlmd.dev.android.pruebaceiba.common.RequestResult
import jlmd.dev.android.pruebaceiba.views.postsuser.model.PostsUserViewState
import jlmd.dev.android.pruebaceiba.views.users.model.UserItem
import jlmd.dev.android.pruebaceiba.utils.toUserItem
import kotlinx.coroutines.launch

class PostsUserViewModel(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val getPostsByUserUseCase: GetPostsByUserUseCase
): ViewModel() {

    private val _postsViewState = MutableLiveData<PostsUserViewState>()
    val postsViewState: LiveData<PostsUserViewState> get() = _postsViewState

    private val _user = MutableLiveData<UserItem>()
    val user: LiveData<UserItem> get() = _user

    fun getUserById(userId: Int) {
        viewModelScope.launch {
            when (val user = getUserByIdUseCase.invoke(userId)){
                is RequestResult.Success -> _user.value = user.data.toUserItem()
                else -> {}
            }
        }
    }

    fun getPostByUser(userId: Int){
        viewModelScope.launch {
            when(val posts = getPostsByUserUseCase.invoke(userId)) {
                is RequestResult.Success -> _postsViewState.value =
                    PostsUserViewState.ShowPosts(posts.data)
                is RequestResult.Error -> _postsViewState.value = PostsUserViewState.Error
            }
        }
    }
}
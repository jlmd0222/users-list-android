package jlmd.dev.android.pruebaceiba.views.postsuser.model

import jlmd.dev.android.pruebaceiba.core.model.Post

sealed class PostsUserViewState {
    data class ShowPosts(
        val posts: List<Post>
    ): PostsUserViewState()

    object Error: PostsUserViewState()

    object Loading: PostsUserViewState()
}

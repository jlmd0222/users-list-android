package jlmd.dev.android.pruebaceiba.views.users.model

sealed class UsersViewState{
    object Loading: UsersViewState()

    object NoUsersFound: UsersViewState()

    object Error: UsersViewState()

    data class ShowUsers(
        val usersList: List<UserItem>
    ): UsersViewState()
}

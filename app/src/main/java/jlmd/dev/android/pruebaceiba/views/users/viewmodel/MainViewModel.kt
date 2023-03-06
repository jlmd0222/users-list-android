package jlmd.dev.android.pruebaceiba.views.users.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jlmd.dev.android.pruebaceiba.core.usecases.GetUsersUseCase
import jlmd.dev.android.pruebaceiba.core.model.User
import jlmd.dev.android.pruebaceiba.common.RequestResult
import jlmd.dev.android.pruebaceiba.views.users.model.UserItem
import jlmd.dev.android.pruebaceiba.views.users.model.UsersViewState
import kotlinx.coroutines.launch

class MainViewModel(
    private val getUsersUseCase: GetUsersUseCase
): ViewModel() {

    private val _usersListViewState = MutableLiveData<UsersViewState>()
    val usersListViewState: LiveData<UsersViewState> get() = _usersListViewState

    private val _foundUsersByQuery = MutableLiveData<Boolean>()
    val foundUsersByQuery: LiveData<Boolean> get() = _foundUsersByQuery

    private var _allDataUsers = listOf<UserItem>()

    init {
        checkUsersData()
    }

    private fun checkUsersData() {
        _usersListViewState.value = UsersViewState.Loading

        viewModelScope.launch {
            val users = getUsersUseCase.invoke()

            if (users is RequestResult.Error){
                _usersListViewState.value = UsersViewState.Error
            } else {
                if (users is RequestResult.Success) {
                    val usersList = users.data.toViewData()
                    _usersListViewState.value = UsersViewState.ShowUsers(usersList)
                    _allDataUsers = usersList
                }
            }
        }
    }

    fun filterUsers(query: CharSequence?) {
        query?.toString()?.let { queryString ->
            if (queryString.isNotBlank()){
                val filteredUsers = _allDataUsers
                    .filter { item -> item.name.uppercase().contains(queryString.uppercase()) }
                    .distinctBy { it.id }
                    .toMutableList()

                val foundUsers = filteredUsers.isNotEmpty()

                if (foundUsers){
                    _usersListViewState.postValue(
                        UsersViewState.ShowUsers(filteredUsers)
                    )
                } else {
                    _usersListViewState.postValue(UsersViewState.NoUsersFound)
                }

                _foundUsersByQuery.postValue(foundUsers)
            }else {
                _usersListViewState.postValue(UsersViewState.ShowUsers(_allDataUsers))
                _foundUsersByQuery.postValue(true)
            }
        }
    }

    private fun List<User>.toViewData(): List<UserItem> {
        return map {
            UserItem(
                id = it.id,
                name = it.name,
                email = it.email,
                phone = it.phone
            )
        }
    }
}
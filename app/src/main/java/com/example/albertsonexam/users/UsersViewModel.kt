package com.example.albertsonexam.users

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albertsonexam.data.UserRepository
import com.example.albertsonexam.data.models.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UsersUiState())
    val uiState: StateFlow<UsersUiState> = _uiState

    private val _selectedUser = mutableStateOf<UserResponse?>(null)
    val selectedUser: State<UserResponse?> = _selectedUser

    private var hasFetchedUsers = false

    fun fetchUsers(count: Int = 100) {
        if (!hasFetchedUsers) {
            _uiState.value = _uiState.value.copy(isLoading = true)
            viewModelScope.launch {
                try {
                    val result = userRepository.getUsers(count)
                    _uiState.value = _uiState.value.copy(users = result, isLoading = false)
                    hasFetchedUsers = true
                } catch (e: Exception) {
                    _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = e.message)
                }
            }
        }
    }

    fun selectUser(user: UserResponse) {
        _selectedUser.value = user
    }

}

data class UsersUiState(
    val users: List<UserResponse> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
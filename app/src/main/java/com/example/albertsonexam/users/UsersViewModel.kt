package com.example.albertsonexam.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albertsonexam.data.UserRepository
import com.example.albertsonexam.data.source.UserResponse
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

    fun fetchUsers(count: Int = 10) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val result = userRepository.getUsers(count)
                _uiState.value = _uiState.value.copy(users = result, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = e.message)
            }
        }
    }

}

data class UsersUiState(
    val users: List<UserResponse> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
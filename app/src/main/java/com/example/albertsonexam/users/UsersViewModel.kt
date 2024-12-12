package com.example.albertsonexam.users

import android.accounts.NetworkErrorException
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

    fun fetchUsers(count: Int) {
        if (!hasFetchedUsers) {
            _uiState.value = _uiState.value.copy(isLoading = true, showInput = false)
            viewModelScope.launch {
                try {
                    val result = userRepository.getUsers(count)
                    _uiState.value = _uiState.value.copy(users = result, isLoading = false)
                    hasFetchedUsers = true
                } catch (e: NetworkErrorException) {
                    _uiState.value = _uiState.value.copy(isLoading = false, showInput = true, errorMessage = e.message ?: NETWORK_ERROR)
                } catch (e: Exception) {
                    _uiState.value = _uiState.value.copy(isLoading = false, showInput = true, errorMessage = e.message ?: UNKNOWN_ERROR)
                }
            }
        }
    }

    fun selectUser(user: UserResponse) {
        _selectedUser.value = user
    }

    fun validateInput(count: Int) {
        if (count in 1..5000) {
            _uiState.value = _uiState.value.copy(errorMessage = null)
            fetchUsers(count)
        } else {
            _uiState.value = _uiState.value.copy(errorMessage = INPUT_1_TO_5000)
        }
    }

    companion object {
        const val NETWORK_ERROR = "Network error"
        const val UNKNOWN_ERROR = "Unknown error"
        const val INPUT_1_TO_5000 = "Enter a number between 1 and 5000"
    }

}

data class UsersUiState(
    val users: List<UserResponse> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val showInput: Boolean = true
)
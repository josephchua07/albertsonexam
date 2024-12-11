package com.example.albertsonexam.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albertsonexam.data.UserRepository
import com.example.albertsonexam.data.source.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _users = MutableLiveData<List<UserResponse>>()
    val users: LiveData<List<UserResponse>> get() = _users

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        fetchUsers(10)
    }

    private fun fetchUsers(count: Int) {
        viewModelScope.launch {
            try {
                _users.value = userRepository.getUsers(count)
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            }
        }
    }

}
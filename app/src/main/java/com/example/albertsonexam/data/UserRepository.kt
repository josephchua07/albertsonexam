package com.example.albertsonexam.data

import com.example.albertsonexam.data.source.UserResponse

interface UserRepository {

    suspend fun getUsers(results: Int): List<UserResponse>

}
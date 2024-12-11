package com.example.albertsonexam.data

import com.example.albertsonexam.data.source.RandomUserService
import com.example.albertsonexam.data.source.UserResponse
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val service: RandomUserService) : UserRepository {

    override suspend fun getUsers(results: Int): List<UserResponse> {
        val response = service.getUsers(results)

        return if (response.isSuccessful) {
            response.body()?.results ?: emptyList()
        } else {
            throw Exception("Error fetching users")
        }

    }

}
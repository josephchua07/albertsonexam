package com.example.albertsonexam.users

import android.accounts.NetworkErrorException
import com.example.albertsonexam.MainCoroutineRule
import com.example.albertsonexam.data.UserRepository
import com.example.albertsonexam.data.models.CoordinatesResponse
import com.example.albertsonexam.data.models.LocationResponse
import com.example.albertsonexam.data.models.NameResponse
import com.example.albertsonexam.data.models.PictureResponse
import com.example.albertsonexam.data.models.StreetResponse
import com.example.albertsonexam.data.models.TimezoneResponse
import com.example.albertsonexam.data.models.UserResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UsersViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val userRepository: UserRepository = mockk()

    private lateinit var viewModel: UsersViewModel

    @Before
    fun setup() {
        viewModel = UsersViewModel(userRepository)
    }

    @Test
    fun `fetchUsers should call getUsers from repository and update uiState`() = runTest {

        coEvery { userRepository.getUsers(expectedNumberOfResults) } returns expectedUsers

        viewModel.fetchUsers(expectedNumberOfResults)

        coVerify { userRepository.getUsers(expectedNumberOfResults) }

        assertEquals(expectedUsers, viewModel.uiState.value.users)

    }

    @Test
    fun `fetchUsers should handle network error and update uiState`() = runTest {

        coEvery { userRepository.getUsers(any()) } throws NetworkErrorException()

        viewModel.fetchUsers(expectedNumberOfResults)

        assertEquals(UsersViewModel.NETWORK_ERROR, viewModel.uiState.value.errorMessage)
        assertFalse(viewModel.uiState.value.isLoading)

    }

    @Test
    fun `fetchUsers should handle error and update uiState`() = runTest {
        coEvery { userRepository.getUsers(any()) } throws Exception(MOCK_ERROR)

        viewModel.fetchUsers(expectedNumberOfResults)

        assertEquals(MOCK_ERROR, viewModel.uiState.value.errorMessage)
        assertFalse(viewModel.uiState.value.isLoading)
    }

    @Test
    fun `fetchUsers should handle unknown error and update uiState`() = runTest {
        coEvery { userRepository.getUsers(any()) } throws Exception()

        viewModel.fetchUsers(expectedNumberOfResults)

        assertEquals(UsersViewModel.UNKNOWN_ERROR, viewModel.uiState.value.errorMessage)
        assertFalse(viewModel.uiState.value.isLoading)
    }

    companion object {
        val expectedNumberOfResults = 1
        val expectedUsers = listOf(
            UserResponse(
                name = NameResponse("Mr", "John", "Doe"),
                gender = "Male",
                location = LocationResponse(
                    street = StreetResponse(123, "Main St"),
                    city = "New York",
                    state = "NY",
                    country = "USA",
                    postcode = "10001",
                    coordinates = CoordinatesResponse("40.7128", "-74.0060"),
                    timezone = TimezoneResponse("-05:00", "Eastern Standard Time")
                ),
                email = "william.henry.harrison@example-pet-store.com",
                picture = PictureResponse("large_url", "medium_url", "thumbnail_url")
            )
        )
        val MOCK_ERROR = "Mock Error"
    }
}
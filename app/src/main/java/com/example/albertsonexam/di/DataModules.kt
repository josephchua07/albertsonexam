package com.example.albertsonexam.di

import com.example.albertsonexam.data.source.RandomUserService
import com.example.albertsonexam.data.UserRepository
import com.example.albertsonexam.data.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    }

    @Provides
    @Singleton
    fun provideRandomUserService(retrofit: Retrofit): RandomUserService =
        retrofit.create(RandomUserService::class.java)

    @Provides
    @Singleton
    fun provideUserRepository(service: RandomUserService): UserRepository =
        UserRepositoryImpl(service)

}
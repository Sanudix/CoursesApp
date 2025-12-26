package com.example.di

import com.example.data.remote.api.CoursesApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://drive.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<CoursesApi> {
        get<Retrofit>().create(CoursesApi::class.java)
    }
}
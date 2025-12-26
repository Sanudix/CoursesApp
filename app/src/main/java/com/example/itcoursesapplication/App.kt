package com.example.itcoursesapplication

import android.app.Application
import com.example.di.networkModule
import com.example.di.repositoryModule
import com.example.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}
package com.example.tenmin

import android.app.Application
import com.example.tenmin.di.appModule
import com.example.tenmin.di.networkModule
import com.example.tenmin.di.repositoryModule
import com.example.tenmin.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidLogger()
      androidContext(this@App)
      modules(
        appModule,
        networkModule,
        repositoryModule,
        viewModelModule,
        // useCaseModule,
      )
    }
  }
}
package com.example.tenmin.di

import com.example.tenmin.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
  viewModel { MainViewModel(get()) }
}
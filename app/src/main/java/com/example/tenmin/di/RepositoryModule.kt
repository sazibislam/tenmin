package com.example.tenmin.di

import com.example.tenmin.data.repositoryImpl.HomeRepositoryImpl
import com.example.tenmin.domain.repository.HomeRepository
import org.koin.dsl.module

/**
 * Repositories module
 * This DI module will be responsible of providing repositories dependencies
 * which need to be live as long as app is living
 * @constructor Create empty Repositories module
 */
val repositoryModule = module {
  single<HomeRepository> { HomeRepositoryImpl(get()) }
}
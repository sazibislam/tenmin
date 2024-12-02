package com.example.tenmin.di

import com.example.tenmin.data.remote.source.HomeRemote
import com.example.tenmin.data.remote.source.HomeRemoteImpl
import org.koin.dsl.module

/**
 * Network module
 * This DI module will be responsible of providing network dependencies
 * which need to be live as long as app is living
 * @constructor Create empty Network module
 */
val networkModule = module {
  single<HomeRemote> { HomeRemoteImpl(get()) }
}
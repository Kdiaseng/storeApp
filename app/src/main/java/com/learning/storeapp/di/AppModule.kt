package com.learning.storeapp.di

import com.learning.storeapp.data.api.RetrofitConfig
import com.learning.storeapp.data.local.datasource.AppDatabase
import com.learning.storeapp.data.local.datasource.ProductLocalDataSource
import com.learning.storeapp.data.repository.ProductRepository
import com.learning.storeapp.data.repository.datasource.ProductRemoteDataSource
import com.learning.storeapp.ui.viewModels.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import android.app.Application
import org.koin.android.ext.koin.androidApplication

val appModule = module {

    fun providerDataBase(application: Application) =
        AppDatabase.getInstance(application).productDao()

    single { RetrofitConfig.retrofitProductService }
    single { ProductRemoteDataSource(get()) }
    single { ProductLocalDataSource(providerDataBase(androidApplication())) }
    single { ProductRepository(get(), get()) }
    viewModel { ProductsViewModel(get()) }
}
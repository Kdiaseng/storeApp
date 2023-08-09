package com.learning.storeapp.di

import com.learning.storeapp.data.api.RetrofitConfig
import com.learning.storeapp.data.repository.ProductRepository

import com.learning.storeapp.data.repository.datasource.ProductRemoteDataSource
import com.learning.storeapp.ui.viewModels.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RetrofitConfig.retrofitProductService }
    single { ProductRemoteDataSource(get()) }
    single { ProductRepository(get()) }
    viewModel { ProductsViewModel(get()) }
}
package com.example.adaprobation02.data.remote.di

import com.example.adaprobation02.data.remote.IAdaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object oApiModule {
    @Provides
    fun C_GETxProvideAdaApi(
        // Potential dependencies of this type
    ): IAdaApi {

        val oLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(oLoggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://dev.ada-soft.com:44340/AdaStandard/API2PSMaster/V5/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(IAdaApi::class.java)
    }
}
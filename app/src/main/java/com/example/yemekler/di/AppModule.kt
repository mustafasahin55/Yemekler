package com.example.todoapp.di

import android.content.Context
import androidx.room.Room
import com.example.rehberuygulamasi.retrofit.ApiUtils
import com.example.yemekler.data.datasource.YemeklerDataSource
import com.example.yemekler.data.repository.YemeklerRepository
import com.example.yemekler.retrofit.YemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule
{

    @Provides
    @Singleton
    fun provideYemeklerDao():YemeklerDao{
        return ApiUtils.getYemeklerDao()
    }

    @Provides
    @Singleton
    fun provideYemeklerDataSource(YemeklerDao:YemeklerDao):YemeklerDataSource{
        return YemeklerDataSource(YemeklerDao)
    }

    @Provides
    @Singleton
    fun provideYemeklerRepository(YemeklerDataSource:YemeklerDataSource): YemeklerRepository{
        return YemeklerRepository(YemeklerDataSource)
    }



}
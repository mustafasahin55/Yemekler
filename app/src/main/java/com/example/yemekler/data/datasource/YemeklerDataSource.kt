package com.example.yemekler.data.datasource

import com.example.yemekler.data.entity.yemek
import com.example.yemekler.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class YemeklerDataSource@Inject constructor(var yemeklerDao: YemeklerDao)

{
    suspend fun tumYemekleriGetir():List<yemek> = withContext(Dispatchers.IO){

        return@withContext yemeklerDao.tumYemekleriGetir().yemek
    }



}
package com.example.yemekler.data.datasource

import android.util.Log
import com.example.yemekler.data.entity.yemek
import com.example.yemekler.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class YemeklerDataSource@Inject constructor(var yemeklerDao: YemeklerDao)

{
    suspend fun tumYemekleriGetir():List<yemek> = withContext(Dispatchers.IO){


        var yemek = yemeklerDao.tumYemekleriGetir().yemekler

       // Log.e("YDS","${yemeklerDao.tumYemekleriGetir().yemekler}")

        return@withContext yemek
    }



}
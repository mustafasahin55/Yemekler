package com.example.rehberuygulamasi.retrofit

import com.example.yemekler.retrofit.RetrofitClient
import com.example.yemekler.retrofit.YemeklerDao

class ApiUtils {
    companion object{

        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getYemeklerDao(): YemeklerDao{
            return RetrofitClient.getClient(BASE_URL).create(YemeklerDao::class.java)
        }
    }
}
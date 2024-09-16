package com.example.yemekler.retrofit

import com.example.yemekler.data.entity.YemeklerCevap
import retrofit2.http.GET

interface YemeklerDao {
    //http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun tumYemekleriGetir():YemeklerCevap

}
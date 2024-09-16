package com.example.yemekler.data.repository

import com.example.yemekler.data.datasource.YemeklerDataSource
import com.example.yemekler.data.entity.yemek
import javax.inject.Inject

class YemeklerRepository@Inject constructor(var yemeklerDataSource:YemeklerDataSource) {

    suspend fun tumYemekleriGetir():List<yemek> = yemeklerDataSource.tumYemekleriGetir()
}
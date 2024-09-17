package com.example.yemekler.data.repository

import com.example.yemekler.data.datasource.YemeklerDataSource
import com.example.yemekler.data.entity.sYemekler
import com.example.yemekler.data.entity.yemek
import javax.inject.Inject

class YemeklerRepository(var yemeklerDataSource:YemeklerDataSource) {

    suspend fun tumYemekleriGetir():List<yemek> = yemeklerDataSource.tumYemekleriGetir()
    suspend fun sepeteEkle(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int, kullanici_adi:String)=yemeklerDataSource.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    suspend fun sepetiGetir(kullanici_adi:String):List<sYemekler> = yemeklerDataSource.sepetiGetir(kullanici_adi)

    suspend fun sil(sepet_yemek_id:Int,kullanici_adi:String)=yemeklerDataSource.sil(sepet_yemek_id,kullanici_adi)
}
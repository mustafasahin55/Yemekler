package com.example.yemekler.data.datasource

import android.util.Log
import com.example.yemekler.data.entity.sYemekler
import com.example.yemekler.data.entity.sepetYemekler
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


    suspend fun sepeteEkle( yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int, kullanici_adi:String){
        var x = yemeklerDao.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
        Log.e("YDS_sepeteekle","${x.success}")
    }

    suspend fun sepetiGetir(kullanici_adi:String):List<sYemekler> = withContext(Dispatchers.IO){
        val result = yemeklerDao.sepetiGetir(kullanici_adi).sepet_yemekler

        Log.e("succes","${yemeklerDao.sepetiGetir(kullanici_adi).sepet_yemekler::class}")
        return@withContext result ?: emptyList()

    }


}
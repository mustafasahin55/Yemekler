package com.example.yemekler.data.datasource

import android.util.Log
import com.example.yemekler.data.entity.sYemekler
import com.example.yemekler.data.entity.sepetYemekler
import com.example.yemekler.data.entity.yemek
import com.example.yemekler.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class YemeklerDataSource @Inject constructor(var yemeklerDao : YemeklerDao)
{
	suspend fun tumYemekleriGetir() : List<yemek> = withContext(Dispatchers.IO) {


		var yemek = yemeklerDao.tumYemekleriGetir().yemekler

		// Log.e("YDS","${yemeklerDao.tumYemekleriGetir().yemekler}")

		return@withContext yemek
	}


	suspend fun sepeteEkle(
		yemek_adi : String ,
		yemek_resim_adi : String ,
		yemek_fiyat : Int ,
		yemek_siparis_adet : Int ,
		kullanici_adi : String
	)
	{
		val sepetYemekler = try {
			yemeklerDao.sepetiGetir(kullanici_adi).sepet_yemekler
		} catch (e: Exception) {
			Log.e("YDS_sepeteEkle", "Sepet getirilemedi: ${e.message}")
			emptyList<sYemekler>() // Eğer veri yoksa boş bir liste döndür
		}

		val mevcutYemek = sepetYemekler.find { it.yemek_adi == yemek_adi }
		val mevcutYemekAdet = mevcutYemek?.yemek_siparis_adet ?: 0
		if (mevcutYemek != null)
		{
			// Aynı isme sahip yemek varsa onu sil
			yemeklerDao.sil(mevcutYemek.sepet_yemek_id , kullanici_adi)
			Log.e("YDS_sepeteekle" , "Mevcut yemek silindi: ${mevcutYemek.yemek_adi}")


		}


		var x = yemeklerDao.sepeteEkle(
			yemek_adi ,
			yemek_resim_adi ,
			yemek_fiyat ,
			yemek_siparis_adet+mevcutYemekAdet ,
			kullanici_adi
		)
		Log.e("YDS_sepeteekle" , "${x.success}")


	}

	suspend fun sepetiGetir(kullanici_adi : String) : List<sYemekler> =
		withContext(Dispatchers.IO) {

			try
			{
				val result = yemeklerDao.sepetiGetir(kullanici_adi)?.sepet_yemekler
				return@withContext result ?: emptyList() // Boşsa boş liste döndür
			} catch (e : Exception)
			{            Log.e("YDS_sepetiGetir", "Hata: ${e.message}")

				return@withContext emptyList()
			}


		}

	suspend fun sil(sepet_yemek_id : Int , kullanici_adi : String)
	{
		var x = yemeklerDao.sil(sepet_yemek_id , kullanici_adi)
		Log.e("YDS_sil" , "${x.success}")

	}


}
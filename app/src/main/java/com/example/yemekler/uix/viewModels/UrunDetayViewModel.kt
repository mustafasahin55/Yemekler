package com.example.yemekler.uix.viewModels;


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemekler.data.entity.sYemekler
import com.example.yemekler.data.repository.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UrunDetayViewModel@Inject constructor(var yemeklerRepo: YemeklerRepository): ViewModel(){
	var sepetYemeklerListesi = MutableLiveData<List<sYemekler>>()

	fun sepeteEkle(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int, kullanici_adi:String){
		CoroutineScope(Dispatchers.Main).launch {
			yemeklerRepo.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
		}
	}


	fun sepetiGetir(kullanici_adi : String)
	{
		CoroutineScope(Dispatchers.Main).launch {

			sepetYemeklerListesi.value = yemeklerRepo.sepetiGetir(kullanici_adi)


		}

	}

}

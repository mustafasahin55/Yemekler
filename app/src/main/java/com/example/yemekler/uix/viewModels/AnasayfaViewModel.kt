package com.example.yemekler.uix.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemekler.data.entity.yemek
import com.example.yemekler.data.repository.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel@Inject constructor(var yemeklerRepo:YemeklerRepository):ViewModel() {

    var tumYemeklerListesi = MutableLiveData<List<yemek>>()

    init {
        tumYemekleriGetir()
    }

    fun tumYemekleriGetir(){
        CoroutineScope(Dispatchers.Main).launch {
            tumYemeklerListesi.value = yemeklerRepo.tumYemekleriGetir()
        }
    }

}
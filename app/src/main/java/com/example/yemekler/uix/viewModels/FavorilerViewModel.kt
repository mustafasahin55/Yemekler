package com.example.yemekler.uix.viewModels

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yemekler.data.entity.yemek
import com.example.yemekler.data.repository.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class FavorilerViewModel@Inject constructor(var yemeklerRepo : YemeklerRepository) : ViewModel()
{
	val tumYemeklerListesi = MutableLiveData<List<yemek>>()

	init
	{
		tumYemekleriGetir()
	}

	fun tumYemekleriGetir()
	{
		CoroutineScope(Dispatchers.Main).launch {
			try
			{
				tumYemeklerListesi.value = yemeklerRepo.tumYemekleriGetir()
			} catch (e : Exception)
			{
				// Hata yönetimi
			}
		}
	}
}


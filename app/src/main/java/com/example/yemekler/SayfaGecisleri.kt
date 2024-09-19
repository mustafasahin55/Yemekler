package com.example.yemekler

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.yemekler.data.entity.yemek
import com.example.yemekler.uix.viewModels.AnasayfaViewModel
import com.example.yemekler.uix.viewModels.FavorilerViewModel
import com.example.yemekler.uix.viewModels.SepetViewModel
import com.example.yemekler.uix.viewModels.UrunDetayViewModel
import com.example.yemekler.uix.views.Anasayfa
import com.example.yemekler.uix.views.FavoritesScreen
import com.example.yemekler.uix.views.Sepet
import com.example.yemekler.uix.views.UrunDetay
import com.google.gson.Gson

@Composable
fun SayfaGecisleri(anasayfaViewModel : AnasayfaViewModel,urunDetayViewModel : UrunDetayViewModel,sepetViewModel:SepetViewModel,FavorilerViewModel: FavorilerViewModel)
{

	val navController = rememberNavController()

	NavHost(navController = navController , startDestination = "Anasayfa" ){
		composable("anasayfa"){
			Anasayfa(navController = navController , anasayfaViewModel = anasayfaViewModel)
		}


		composable("detaySayfa/{yemek}", arguments = listOf(
			navArgument("yemek"){type = NavType.StringType}

		)){
			val json = it.arguments?.getString("yemek")
			val n = Gson().fromJson(json, yemek::class.java)
			UrunDetay(n,urunDetayViewModel,navController)

		}

		composable("sepet"){
			Sepet(sepetViewModel,navController)
		}

		composable("fav"){
			FavoritesScreen(navController,FavorilerViewModel)
		}



	}


}
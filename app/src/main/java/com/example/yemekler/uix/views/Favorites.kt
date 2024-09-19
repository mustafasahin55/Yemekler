package com.example.yemekler.uix.views


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomAppBar
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.yemekler.uix.viewModels.FavorilerViewModel
import com.google.gson.Gson
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(navController:NavController,viewModel: FavorilerViewModel){
	val context = LocalContext.current
	val tumYemeklerListe =
		viewModel.tumYemeklerListesi.observeAsState(initial = emptyList())
	LaunchedEffect(key1 = true) {
		viewModel.tumYemekleriGetir()

	}

	Scaffold(
		topBar = {
			androidx.compose.material3.TopAppBar(title = {

				// Top Section (Back button and Title)
				Row(
					verticalAlignment = Alignment.CenterVertically ,

					modifier = androidx.compose.ui.Modifier.fillMaxWidth()
				) {
					IconButton(onClick = { navController.popBackStack() }) {
						Icon(
							imageVector = Icons.Default.ArrowBack ,
							contentDescription = "Back"
						)
					}
					androidx.compose.material.Text(
						text = "Favorites" ,
						fontSize = 24.sp ,
						fontWeight = FontWeight.Bold ,
						modifier = androidx.compose.ui.Modifier
							.weight(1f)
							.padding(start = 8.dp)

					)
				}


			})
		},
		content = { paddingValues ->
			LazyColumn(
				modifier = androidx.compose.ui.Modifier
					.padding(paddingValues)
					.padding(top = 20.dp, end = 30.dp)
					.height((100 * tumYemeklerListe.value.size).dp)
			) {
				val itemCount = tumYemeklerListe.value.size


				items(itemCount / 2 + itemCount % 2 , itemContent = {
					Row(modifier = androidx.compose.ui.Modifier.fillMaxWidth()) {
						// İlk öğe
						val firstItemIndex = it * 2
						var isFav1 = readFromFile(context,tumYemeklerListe.value[firstItemIndex].yemek_adi)
						var isFav2 = readFromFile(context,tumYemeklerListe.value[firstItemIndex+1].yemek_adi)
						if(isFav1){

							FoodCard(tumYemeklerListe.value[firstItemIndex] , onClick = {
								val yemekJson =
									Gson().toJson(tumYemeklerListe.value[firstItemIndex])
								navController.navigate("detaySayfa/$yemekJson")
							})
						}
						if(isFav2){
							if (firstItemIndex + 1 < itemCount)
							{
								FoodCard(tumYemeklerListe.value[firstItemIndex + 1] , onClick = {
									val yemekJson =
										Gson().toJson(tumYemeklerListe.value[firstItemIndex + 1])
									navController.navigate("detaySayfa/$yemekJson")
								})
							}
							else
							{
								Spacer(modifier = androidx.compose.ui.Modifier.weight(1f)) // Eğer ikinci öğe yoksa boş alan bırakıyoruz
							}
						}
						// İkinci öğe varsa, onu ekliyoruz

					}


				})
			}
		},

	)
}

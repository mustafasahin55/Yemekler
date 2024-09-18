package com.example.yemekler.uix.views

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.yemekler.R
import com.example.yemekler.data.entity.sYemekler
import com.example.yemekler.data.entity.yemek
import com.example.yemekler.data.entity.yemekTurleri
import com.example.yemekler.ui.theme.dark20
import com.example.yemekler.ui.theme.dark50
import com.example.yemekler.ui.theme.dark80
import com.example.yemekler.ui.theme.gray80
import com.example.yemekler.ui.theme.mid
import com.example.yemekler.ui.theme.orange
import com.example.yemekler.ui.theme.orange50
import com.example.yemekler.ui.theme.orange80
import com.example.yemekler.ui.theme.regular
import com.example.yemekler.ui.theme.semiBold
import com.example.yemekler.uix.viewModels.AnasayfaViewModel
import com.google.gson.Gson
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController : NavController , anasayfaViewModel : AnasayfaViewModel)
{
	var sepetYemeklerListe =
		anasayfaViewModel.sepetYemeklerListesi.observeAsState(initial = emptyList())

	LaunchedEffect(true) {
		anasayfaViewModel.sepetiGetir("mustafa")


	}

	val activity = (LocalContext.current as Activity)
	val yemekTurleri = remember { mutableListOf<yemekTurleri>() }
	var y1 = yemekTurleri(1 , "Burger" , "burger")
	var y2 = yemekTurleri(2 , "Donat" , "donat")
	var y3 = yemekTurleri(3 , "Pizza" , "pizza")
	var y4 = yemekTurleri(4 , "Mexican" , "mexican")
	var y5 = yemekTurleri(5 , "Asian" , "asian")
	yemekTurleri.add(y1)
	yemekTurleri.add(y2)
	yemekTurleri.add(y3)
	yemekTurleri.add(y4)
	yemekTurleri.add(y5)
	val restoran = remember { mutableListOf<String>() }
	val r1 = "mcdonals"
	val r2 = "starbuck"
	restoran.add(r1)
	restoran.add(r2)

	var searchText = remember { mutableStateOf("") }
	val selectedIndex = remember { mutableStateOf(- 1) }

	val yemekler = remember { mutableListOf<yemek>() }
	var yemek1 = yemek(1 , "Hamburger" , "ayran" , 20)
	var yemek2 = yemek(2 , "Hamburger" , "ayran" , 20)

	yemekler.add(yemek1)
	yemekler.add(yemek2)

	val tumYemeklerListe =
		anasayfaViewModel.tumYemeklerListesi.observeAsState(initial = emptyList())
	LaunchedEffect(key1 = true) {
		anasayfaViewModel.tumYemekleriGetir()

	}



	Scaffold(bottomBar = { BottomBar(navController,sepetYemeklerListe) } ,
		topBar = { topBar() }


	) { paddingValues ->
		LazyColumn(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues)
		) {

			item {
				Spacer(modifier = Modifier.height(10.dp))
				Text(
					text = "What would you like to order" ,
					fontFamily = semiBold ,
					lineHeight = 30.sp ,
					color = dark80 ,
					fontSize = 30.sp ,
					modifier = Modifier.padding(start = 35.dp , end = 90.dp)
				)
			}
			item {
				Spacer(modifier = Modifier.height(10.dp))

				Row(
					verticalAlignment = Alignment.Top ,
					horizontalArrangement = Arrangement.SpaceBetween ,
					modifier = Modifier.fillMaxWidth()
				) {
					Row(
						verticalAlignment = Alignment.CenterVertically ,
						horizontalArrangement = Arrangement.SpaceBetween ,
						modifier = Modifier

							.padding(start = 30.dp , end = 0.dp)
							.background(Color(0xFFFCFCFD) , RoundedCornerShape(12.dp))
							.border(
								1.dp ,
								shape = RoundedCornerShape(12.dp) ,
								color = Color(0xFFEFEFEF)
							)
							.width(280.dp)
							.height(50.dp)
							.clickable {
								// Tıklama ile arama işlemi başlatabilirsiniz
								// searchOperation(searchText.text)
							}


					) {
						Image(
							painter = painterResource(id = R.drawable.searchicon) ,
							contentDescription = "" , modifier = Modifier.padding(start = 20.dp)
						)

						TextField(
							value = searchText.value ,
							onValueChange = { searchText.value = it } ,
							label = {
								Text(
									text = "Find for food or restaurant.." , fontSize = 12.sp
								)
							} ,
							modifier = Modifier ,

							colors = TextFieldDefaults.textFieldColors(
								containerColor = Color.Transparent ,
								focusedLabelColor = dark20 ,
								unfocusedLabelColor = dark20 ,
								unfocusedIndicatorColor = Color.White ,
								focusedIndicatorColor = Color.Transparent
							) ,


							)


					}
					val painter1 = rememberAsyncImagePainter(
						model = ImageRequest.Builder(LocalContext.current)
							.data("file:///android_asset/filter.svg")
							.decoderFactory(SvgDecoder.Factory())
							.build()
					)


					Image(
						painter = painter1 ,
						contentDescription = "" ,
						modifier = Modifier
							.size(120.dp , 50.dp)
							.padding(end = 10.dp)
					)

				}

			}

			item {
				LazyRow(
					modifier = Modifier
						.fillMaxWidth()
						.padding(16.dp)
				) {
					items(yemekTurleri.size) {
						var y = yemekTurleri[it]
						FoodCategoryCard(
							name = y.yemekTurAdi ,
							svgPath = y.yemekTurResim ,
							isSelected = selectedIndex.value == y.id ,
							onClick = { selectedIndex.value = y.id })
					}
				}


			}
			item {
				Row(
					modifier = Modifier.padding(start = 30.dp) ,
					horizontalArrangement = Arrangement.SpaceBetween ,
					verticalAlignment = Alignment.CenterVertically
				) {

					Text(
						text = "Featured Restaurants" ,
						lineHeight = 18.sp ,
						color = dark80 ,
						fontSize = 18.sp ,
						fontFamily = semiBold
					)
					Text(
						text = "View All >" ,
						lineHeight = 14.sp ,
						color = orange ,
						fontSize = 14.sp ,
						fontFamily = semiBold ,
						modifier = Modifier.padding(start = 90.dp)
					)
				}

			}
			item {
				LazyRow(
					modifier = Modifier
						.fillMaxWidth()
						.padding(start = 15.dp)
				) {
					items(restoran.size) {
						var resim = restoran[it]
						Image(
							modifier = Modifier
								.size(330.dp , height = 280.dp)
								.clipToBounds()
								.padding(end = 0.dp) ,
							painter = painterResource(
								id = activity.resources.getIdentifier(
									resim ,
									"drawable" ,
									activity.packageName
								)
							) ,
							contentDescription = ""
						)

					}

				}
				Text(
					text = "Popular Items" ,
					lineHeight = 18.sp ,
					color = dark80 ,
					fontSize = 18.sp ,
					fontFamily = semiBold ,
					modifier = Modifier.padding(start = 30.dp)
				)


			}

			item {
				Spacer(modifier = Modifier.height(10.dp))

				LazyColumn(
					modifier = Modifier
						.padding(end = 30.dp)
						.height((100 * tumYemeklerListe.value.size).dp)
				) {
					val itemCount = tumYemeklerListe.value.size


					items(itemCount / 2 + itemCount % 2 , itemContent = {
						Row(modifier = Modifier.fillMaxWidth()) {
							// İlk öğe
							val firstItemIndex = it * 2

							FoodCard(tumYemeklerListe.value[firstItemIndex] , onClick = {
								val yemekJson =
									Gson().toJson(tumYemeklerListe.value[firstItemIndex])
								navController.navigate("detaySayfa/$yemekJson")
							})

							// İkinci öğe varsa, onu ekliyoruz
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
								Spacer(modifier = Modifier.weight(1f)) // Eğer ikinci öğe yoksa boş alan bırakıyoruz
							}
						}


					})
				}


			}

		}


	}

}


@Composable
fun BottomBar(navController : NavController , sepetYemeklerListe : State<List<sYemekler>>)
{
	val bottomBarSecim = remember { mutableStateOf(0) }



	BottomAppBar(modifier = Modifier.height(70.dp) ,
		content = {
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.background(Color.Transparent)
					.padding(start = 10.dp , end = 10.dp) ,
				verticalAlignment = Alignment.CenterVertically ,
				horizontalArrangement = Arrangement.SpaceBetween
			) {

				IconButton(onClick = { bottomBarSecim.value = 0 }) {
					Icon(
						painter = painterResource(id = R.drawable.home) ,
						contentDescription = "" ,
						modifier = Modifier.size(24.dp) ,
						tint = if (bottomBarSecim.value == 0) orange80 else dark20
					)

				}
				IconButton(onClick = { }) {
					Icon(
						painter = painterResource(id = R.drawable.konum) ,
						contentDescription = "" ,
						modifier = Modifier.size(24.dp) ,
						tint = dark20
					)

				}

				Box(modifier = Modifier) {
					IconButton(onClick = {
						navController.navigate("sepet")
						bottomBarSecim.value = 2
					}) {
						Icon(
							painter = painterResource(id = R.drawable.sepet) ,
							contentDescription = "" ,
							modifier = Modifier
								.size(24.dp)
								.align(Alignment.Center) ,
							tint = if (bottomBarSecim.value == 2) orange80 else dark20
						)

					}

					Box(
						modifier = Modifier
							.size(20.dp)
							.align(Alignment.TopEnd) // Sağ üst köşeye hizala
							.background(
								Color(0xFFFFC107) ,
								RoundedCornerShape(7.dp)
							) , // Arka plan sarı ve yuvarlak
						contentAlignment = Alignment.Center
					) {
						Text(
							text = "${sepetYemeklerListe.value.size}" ,
							color = Color.White ,
							fontSize = 12.sp ,
							fontWeight = FontWeight.Bold,
							lineHeight = 12.sp,
							modifier = Modifier.padding(bottom = 0.dp)
						)
					}
				}



				IconButton(onClick = { /*TODO*/ }) {
					Icon(
						painter = painterResource(id = R.drawable.fav) ,
						contentDescription = "" ,
						modifier = Modifier.size(24.dp) ,
						tint = dark20

					)

				}
				IconButton(onClick = { /*TODO*/ }) {
					Icon(
						painter = painterResource(id = R.drawable.bildirim) ,
						contentDescription = "" ,
						modifier = Modifier.size(24.dp) ,
						tint = dark20
					)

				}

			}

		}

	)

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun topBar()
{
	TopAppBar(title = {

		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(end = 12.dp) ,
			verticalAlignment = Alignment.CenterVertically ,
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			Image(
				painter = painterResource(id = R.drawable.foldmenubutton) ,
				contentDescription = "" , modifier = Modifier.padding(top = 22.dp)
			)

			Column(
				verticalArrangement = Arrangement.Center ,
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Row {
					Text(text = "Deliver to" , fontSize = 12.sp , fontFamily = mid , color = gray80)
					Icon(
						painter = painterResource(id = R.drawable.arrow_down) ,
						contentDescription = "" , tint = gray80
					)
				}

				Text(
					text = "34418 Nilüfer Sokak" ,
					fontSize = 12.sp ,
					color = orange ,
					lineHeight = 12.sp
				)

			}
			Image(painter = painterResource(id = R.drawable.minipp) , contentDescription = "")

		}


	})
}

@Composable
fun FoodCategoryCard(name : String , svgPath : String , isSelected : Boolean , onClick : () -> Unit)
{
	val backgroundColor = if (isSelected) Color(0xFFFF7043) else Color.White
	val textColor = if (isSelected) Color.White else Color(0xFF67666D)

	Column(
		modifier = Modifier
			.padding(start = 10.dp , end = 10.dp)
			.width(70.dp)
			.height(120.dp)
			.background(backgroundColor , RoundedCornerShape(80.dp))
			.padding(vertical = 4.dp)
			.clickable {
				onClick.invoke()
			} ,
		horizontalAlignment = Alignment.CenterHorizontally ,
		verticalArrangement = Arrangement.Top
	) {
		val painter = rememberAsyncImagePainter(
			model = ImageRequest.Builder(LocalContext.current)
				.data("file:///android_asset/$svgPath.svg")
				.decoderFactory(SvgDecoder.Factory())
				.build()
		)


		Image(
			painter = painter ,
			contentDescription = name ,
			modifier = Modifier.size(60.dp)
		)


		Spacer(modifier = Modifier.height(14.dp))

		Text(
			text = name ,
			fontFamily = regular ,
			fontSize = 11.sp ,
			fontWeight = FontWeight.Bold ,
			color = textColor ,
			lineHeight = 11.sp
		)
	}
}

@Composable
fun FoodCard(y : yemek , onClick : () -> Unit)
{
	Card(
		shape = RoundedCornerShape(16.dp) ,
		modifier = Modifier
			.padding(start = 30.dp , top = 0.dp , bottom = 10.dp)
			.height(200.dp)
			.width(160.dp)
			.clickable(onClick = onClick) ,
		elevation = 4.dp
	) {
		Column(
			modifier = Modifier
				.padding(8.dp) ,
			verticalArrangement = Arrangement.Top ,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Box(
				modifier = Modifier
					.size(150.dp) // Kartın boyutunu ayarlayabilirsin
					.clip(RoundedCornerShape(16.dp)) // Köşeleri yuvarlama
					.background(Color.White) // Arka plan rengi,

			) {
				// Arka plan görseli
				val url = "http://kasimadalan.pe.hu/yemekler/resimler/${y.yemek_resim_adi}"
				GlideImage(
					imageModel = url ,
					modifier = Modifier.align(Alignment.Center) ,
					contentScale = ContentScale.Crop ,
				)


				// Fiyat etiketi
				Text(
					text = "${y.yemek_fiyat}₺" ,
					color = Color.White ,

					modifier = Modifier
						.align(Alignment.TopStart) // Sol üstte hizala
						.padding(8.dp)
						.background(
							color = orange80 ,
							shape = RoundedCornerShape(2.dp)
						)
						.padding(horizontal = 0.dp , vertical = 0.dp)
						.border(0.dp , Color.Transparent , RoundedCornerShape(10.dp))
						.shadow(8.dp , CircleShape)
				)

				// Favori butonu
				Icon(
					imageVector = Icons.Default.FavoriteBorder ,
					contentDescription = "Favorite" ,
					tint = dark50 ,
					modifier = Modifier
						.align(Alignment.TopEnd) // Sağ üstte hizala
						.padding(8.dp)
						.size(24.dp)
						.clickable { }
				)

				// Puanlama kısmı
				Row(
					verticalAlignment = Alignment.CenterVertically ,
					modifier = Modifier
						.align(Alignment.BottomStart) // Sol altta hizala
						.padding(start = 28.dp)
						.background(
							color = orange50 ,
							shape = RoundedCornerShape(12.dp)
						)
						.shadow(8.dp , CircleShape)
						.padding(horizontal = 0.dp , vertical = 0.dp)
						.border(0.dp , Color.Transparent , RoundedCornerShape(10.dp))
				) {
					Icon(
						imageVector = Icons.Default.Star ,
						contentDescription = "Rating" ,
						tint = Color(0xFFFFD700) , // Yıldız rengi
						modifier = Modifier.size(16.dp)
					)
					Spacer(modifier = Modifier.width(4.dp))
					Text(text = "4.5" , color = Color.White)
					Spacer(modifier = Modifier.width(4.dp))
					Text(text = "(25+)" , color = Color.White)
				}
			}
			Spacer(modifier = Modifier.height(4.dp))
			Text(
				text = y.yemek_adi , color = Color.White , modifier = Modifier
					.padding()
					.background(
						color = orange ,
						shape = RoundedCornerShape(5.dp)
					)
					.shadow(8.dp , CircleShape)
			)

		}
	}
}

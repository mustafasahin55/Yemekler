package com.example.yemekler.uix.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.yemekler.data.entity.yemek
import com.example.yemekler.ui.theme.dark50
import com.example.yemekler.ui.theme.dark80
import com.example.yemekler.ui.theme.gray50
import com.example.yemekler.ui.theme.gray80
import com.example.yemekler.ui.theme.mid
import com.example.yemekler.ui.theme.orange
import com.example.yemekler.ui.theme.semiBold
import com.example.yemekler.uix.viewModels.UrunDetayViewModel
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun UrunDetay(
	y : yemek ,
	urunDetayViewModel : UrunDetayViewModel ,
	navController : NavHostController
)
{

	var adet = remember {
		mutableIntStateOf(1)
	}



	Scaffold { paddingValues ->


		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(paddingValues)
				.padding(16.dp) , verticalArrangement = Arrangement.Top ,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Box(modifier = Modifier.fillMaxWidth()) {

				val url = "http://kasimadalan.pe.hu/yemekler/resimler/${y.yemek_resim_adi}"

				GlideImage(
					imageModel = url ,
					modifier = Modifier
						.align(Alignment.Center)
						.size(180.dp) ,
					contentScale = ContentScale.Crop ,
				)
				// Sol üst köşeye geri butonu
				IconButton(
					onClick = { navController.popBackStack() } ,
					modifier = Modifier
						.align(Alignment.TopStart)
						.padding(8.dp)
				) {
					Icon(
						imageVector = Icons.Default.ArrowBack ,
						contentDescription = "Back" ,
						tint = Color.Black ,
						modifier = Modifier.size(24.dp)
					)
				}
				IconButton(
					onClick = { /* Favori butonuna tıklama işlemi */ } ,
					modifier = Modifier
						.align(Alignment.TopEnd)
						.padding(8.dp)
				) {
					Icon(
						imageVector = Icons.Default.FavoriteBorder ,
						contentDescription = "Favorite" ,
						tint = Color.Black ,
						modifier = Modifier.size(24.dp)
					)
				}

			}





			Spacer(modifier = Modifier.height(8.dp))

			// Başlık ve derecelendirme
			Row(
				verticalAlignment = Alignment.CenterVertically ,
				modifier = Modifier.fillMaxWidth()
			) {
				Text(
					text = "${y.yemek_adi}" ,
					fontSize = 30.sp ,
					fontFamily = semiBold ,
					color = dark80 ,
					lineHeight = 30.sp
				)

			}
			Row(
				verticalAlignment = Alignment.CenterVertically ,
				modifier = Modifier.padding(top = 4.dp)
			) {
				Icon(
					imageVector = Icons.Default.Star ,
					contentDescription = "Rating" ,
					tint = Color(0xFFFFC107) ,
					modifier = Modifier
						.size(24.dp)
						.padding(end = 2.dp)
				)
				Text(text = "4.5  ")

				Text(text = "(30+)" , color = gray80)
				Text(
					text = "See Review" ,
					color = orange ,
					style = TextStyle(textDecoration = TextDecoration.Underline) ,
					modifier = Modifier.padding(start = 8.dp)
				)
				Spacer(modifier = Modifier.fillMaxWidth(1f))

			}

			Spacer(modifier = Modifier.height(8.dp))

			// Fiyat ve miktar kontrolü
			Row(
				verticalAlignment = Alignment.CenterVertically ,
				modifier = Modifier.fillMaxWidth()
			) {
				Text(
					text = "${y.yemek_fiyat}₺" ,

					color = orange ,
					fontFamily = semiBold ,
					fontSize = 30.sp
				)
				Spacer(modifier = Modifier.weight(1f))
				QuantitySelector(adet)
			}

			Spacer(modifier = Modifier.height(8.dp))

			// Açıklama
			Text(
				text = "Brown the beef better. Lean ground beef – I like to use 85% lean angus. Garlic – use fresh chopped. Spices – chili powder, cumin, onion powder." ,
				color = gray50

			)

			Spacer(modifier = Modifier.height(16.dp))
			Row() {
				Text(
					text = "Choice of Add On" ,
					color = dark50 ,
					fontFamily = mid ,
					fontSize = 20.sp ,
					lineHeight = 20.sp
				)
				Spacer(modifier = Modifier.fillMaxWidth(1f))
			}
			// Ekstra malzeme seçimi
			Spacer(modifier = Modifier.height(4.dp))
			AddOnOption("Pepper Julienned" , "+$2.30" , true)
			AddOnOption("Baby Spinach" , "+$4.70" , false)
			AddOnOption("Mushroom" , "+$2.50" , false)

			Spacer(modifier = Modifier.height(16.dp))

			AddToCartButton(y , urunDetayViewModel , adet , navController)
		}
	}
}

@Composable
fun QuantitySelector(adet : MutableState<Int>)
{
	var a = adet
	Row(
		verticalAlignment = Alignment.CenterVertically
	) {
		val painter = rememberAsyncImagePainter(
			model = ImageRequest.Builder(LocalContext.current)
				.data("file:///android_asset/artir.svg")
				.decoderFactory(SvgDecoder.Factory())
				.build()
		)
		val painter2 = rememberAsyncImagePainter(
			model = ImageRequest.Builder(LocalContext.current)
				.data("file:///android_asset/azalt.svg")
				.decoderFactory(SvgDecoder.Factory())
				.build()
		)


		Image(
			painter = painter2 ,
			contentDescription = "" ,
			modifier = Modifier
				.size(30.dp)
				.clickable {
					if (adet.value > 1)
					{
						adet.value -= 1
					}
					else
					{
						adet.value = 1
					}

				}
		)
		Text(text = "${adet.value}" , modifier = Modifier.padding(start = 10.dp , end = 10.dp))
		Image(
			painter = painter ,
			contentDescription = "" ,
			modifier = Modifier
				.size(30.dp)
				.clickable {
					adet.value += 1

				}

		)
		Spacer(modifier = Modifier.width(20.dp))
	}

}

@Composable
fun AddOnOption(name : String , price : String , isSelected : Boolean)
{
	Row(
		verticalAlignment = Alignment.CenterVertically ,
		horizontalArrangement = Arrangement.SpaceBetween ,
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 4.dp)
	) {
		Text(
			text = name ,
			fontSize = 14.sp ,
			fontFamily = mid ,
			color = dark80 ,
			modifier = Modifier.padding(start = 2.dp)
		)
		Row(verticalAlignment = Alignment.CenterVertically) {

			Text(
				text = price ,
				color = dark80 ,
				fontFamily = mid ,
				fontSize = 16.sp ,
				lineHeight = 16.sp ,
				style = MaterialTheme.typography.body2 ,
				modifier = Modifier.padding(start = 8.dp)
			)
			RadioButton(

				colors = RadioButtonDefaults.colors(
					selectedColor = orange ,
					unselectedColor = gray50
				) ,
				selected = isSelected ,
				onClick = { /* Seçenek tıklaması */ }
			)
		}


	}
}

@Composable
fun AddToCartButton(
	y : yemek ,
	urunDetayViewModel : UrunDetayViewModel ,
	adet : MutableState<Int> ,
	navController : NavHostController
)
{


	Button(
		onClick = {
			urunDetayViewModel.sepeteEkle(
				y.yemek_adi ,
				y.yemek_resim_adi ,
				y.yemek_fiyat ,
				adet.value ,
				"mustafa"
			)
			navController.navigate("anasayfa")
			urunDetayViewModel.sepetiGetir("mustafa")

		} ,
		shape = RoundedCornerShape(36.dp) , // Yuvarlak kenarlar
		colors = ButtonDefaults.buttonColors(backgroundColor = orange) , // Buton turuncu rengi
		modifier = Modifier
			.width(190.dp)
			.height(60.dp) // Buton yüksekliği
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically ,
			horizontalArrangement = Arrangement.Start ,
			modifier = Modifier
				.fillMaxWidth(1f)
				.padding(all = 0.dp)
		) {
			// Yuvarlak simge
			Box(
				modifier = Modifier
					.size(45.dp) // Simgenin boyutu
					.background(Color.White , CircleShape) , // Beyaz arka plan ve yuvarlak şekil
				contentAlignment = Alignment.Center // Simgeyi ortala
			) {

				val p = rememberAsyncImagePainter(
					model = ImageRequest.Builder(LocalContext.current)
						.data("file:///android_asset/sepet.svg")
						.decoderFactory(SvgDecoder.Factory())
						.build()
				)


				Image(
					painter = p ,
					contentDescription = "" ,
					modifier = Modifier.size(20.dp)
				)

			}
			Spacer(modifier = Modifier.width(8.dp)) // Simge ile metin arasındaki boşluk
			Text(
				text = "ADD TO CART" ,
				color = Color.White ,
			)

		}
	}
}


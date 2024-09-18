package com.example.yemekler.uix.views

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
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ButtonDefaults
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.IconButton
import androidx.compose.material.TabRowDefaults.Divider
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextField
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.yemekler.R
import com.example.yemekler.data.entity.sYemekler
import com.example.yemekler.ui.theme.gray20
import com.example.yemekler.ui.theme.gray50
import com.example.yemekler.ui.theme.mid
import com.example.yemekler.ui.theme.orange80
import com.example.yemekler.uix.viewModels.SepetViewModel

@Composable
fun Sepet(sepetViewModel : SepetViewModel , navController : NavHostController)
{

	var sepetYemeklerListe =
		sepetViewModel.sepetYemeklerListesi.observeAsState(initial = emptyList())

	LaunchedEffect(true) {
		sepetViewModel.sepetiGetir("mustafa")


	}
	val toplam = sepetYemeklerListe.value.sumOf { it.yemek_fiyat * it.yemek_siparis_adet }

	Scaffold(topBar = { topBarSepet(navController) }) { paddingValues ->

		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues)
				.padding(start = 20.dp , end = 20.dp) ,
			horizontalAlignment = Alignment.CenterHorizontally ,
			verticalArrangement = Arrangement.Bottom
		) {


			LazyColumn(modifier = Modifier.height(250.dp)) {

				if (sepetYemeklerListe.value.isEmpty())
				{
					item {
						Text(text = "Sepetiniz Boş")
					}
				}
				else
				{
					items(sepetYemeklerListe.value.size , itemContent = {

						val sepetYemek = sepetYemeklerListe.value[it]
						//Log.e("sepetYemeklerListe", sepetYemek.yemek_adi)


						CartItem(
							imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemek_resim_adi}" ,
							itemName = sepetYemek.yemek_adi ,
							description = "Spicy chicken, beef" ,
							price = "${sepetYemek.yemek_fiyat}₺" ,
							quantity = sepetYemek.yemek_siparis_adet ,
							sepet_yemek_id = sepetYemek.sepet_yemek_id ,
							sepetViewModel ,
							sepetYemek

						)
						Div()


					})
				}


			}


			// Cart Items


			Spacer(modifier = Modifier.height(20.dp))

			// Promo Code Section
			PromoCodeSection()

			Spacer(modifier = Modifier.height(16.dp))

			// Price Breakdown
			PriceBreakdown(
				subtotal = "₺$toplam " ,
				taxAndFees = "₺${toplam * 0.1}" ,
				delivery = "₺24.5" ,
				total = "₺${toplam  + 24.5}"
			)


			// Checkout Button
			Button(
				onClick = { /* Handle checkout */ } ,
				modifier = Modifier
					.height(60.dp)
					.width(275.dp)
					.clip(RoundedCornerShape(30.dp)) ,
				colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF7E47))
			) {
				Text(text = "CHECKOUT" , color = Color.White , fontSize = 16.sp)
			}
		}

	}
}


@Composable
fun CartItem(
	imageUrl : String ,
	itemName : String ,
	description : String ,
	price : String ,
	quantity : Int ,
	sepet_yemek_id : Int ,
	sepetViewModel : SepetViewModel ,
	sepetYemek : sYemekler
)
{

	Row(
		verticalAlignment = Alignment.CenterVertically ,
		modifier = Modifier.fillMaxWidth()
	) {
		Image(
			painter = rememberAsyncImagePainter(imageUrl) ,
			contentDescription = itemName ,
			modifier = Modifier
				.size(60.dp)
				.clip(RoundedCornerShape(10.dp))
		)

		Spacer(modifier = Modifier.width(16.dp))
		Box(modifier = Modifier.fillMaxWidth()) {
			Column(modifier = Modifier

			) {
				Text(text = itemName , fontWeight = FontWeight.Bold)
				Text(text = description , color = Color.Gray)
				Text(text = price , color = Color(0xFFFF7E47) , fontWeight = FontWeight.Bold)
			}

			Row(
				modifier = Modifier
					.align(Alignment.BottomEnd)
					.padding(top = 20.dp , end = 20.dp) ,
				horizontalArrangement = Arrangement.spacedBy(0.dp) ,
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
						.size(20.dp)
						.clickable {
							if (quantity >= 2){
							sepetViewModel.sepeteEkle(
								yemek_adi = sepetYemek.yemek_adi ,
								yemek_resim_adi = sepetYemek.yemek_resim_adi ,
								yemek_fiyat = sepetYemek.yemek_fiyat ,
								yemek_siparis_adet = - 1 ,
								kullanici_adi = "mustafa"
							)}
							if (quantity<2){
								sepetViewModel.sil(sepet_yemek_id , "mustafa")

							}
						}
				)
				Text(
					text = quantity.toString() ,
					modifier = Modifier.padding(start = 10.dp , end = 10.dp)
				)
				Image(
					painter = painter ,
					contentDescription = "" ,
					modifier = Modifier
						.size(20.dp)
						.clickable {
							sepetViewModel.sepeteEkle(
								yemek_adi = sepetYemek.yemek_adi ,
								yemek_resim_adi = sepetYemek.yemek_resim_adi ,
								yemek_fiyat = sepetYemek.yemek_fiyat ,
								yemek_siparis_adet = 1 ,
								kullanici_adi = "mustafa"
							)
						}
				)

			}

			IconButton(
				onClick = { sepetViewModel.sil(sepet_yemek_id , "mustafa") } , modifier = Modifier
					.align(Alignment.TopEnd)
					.size(60.dp)
					.padding(bottom = 40.dp , end = 0.dp)
			) {
				Icon(
					painter = painterResource(id = R.drawable.delete) ,
					contentDescription = "" ,
					tint = orange80
				)
			}


		}


	}
}

@Composable
fun PromoCodeSection()
{
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp , vertical = 8.dp)

			.height(64.dp)
			.border(1.dp , gray50 , shape = RoundedCornerShape(60.dp))
			.background(Color.Transparent) ,
		verticalAlignment = Alignment.CenterVertically ,
		horizontalArrangement = Arrangement.spacedBy(8.dp)

	) {
		TextField(
			value = "" ,
			onValueChange = {} ,
			placeholder = { Text("Promo Code") } ,
			modifier = Modifier
				.weight(1f)
				.height(56.dp)
				.clip(RoundedCornerShape(12.dp)) ,
			shape = RoundedCornerShape(12.dp) ,
			colors = TextFieldDefaults.textFieldColors(
				backgroundColor = Color.Transparent ,
				focusedIndicatorColor = Color.Transparent ,
				unfocusedIndicatorColor = Color.Transparent
			) ,
			singleLine = true
		)
		Button(
			onClick = { /* Apply Promo Code action */ } ,
			modifier = Modifier
				.padding(end = 10.dp)
				.height(50.dp)
				.width(100.dp)
				.clip(RoundedCornerShape(50.dp)) ,
			colors = ButtonDefaults.buttonColors(
				backgroundColor = Color(0xFFFF754C) // Orange-ish color
			)
		) {
			Text("Apply" , color = Color.White)
		}
	}
}


@Composable
fun PriceBreakdown(subtotal : String , taxAndFees : String , delivery : String , total : String)
{
	Column {
		BreakdownRow(label = "Subtotal" , amount = subtotal)

		Div()
		BreakdownRow(label = "Tax and Fees" , amount = taxAndFees)
		Div()
		BreakdownRow(label = "Delivery" , amount = delivery)
		Div()
		BreakdownRow(label = "Total" , amount = total , isTotal = true)
		Div()
	}
}

@Composable
fun BreakdownRow(label : String , amount : String , isTotal : Boolean = false)
{
	Row(
		modifier = Modifier.fillMaxWidth() ,
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		Text(
			text = label ,
			fontFamily = mid ,
			color = Color.Black ,
			fontSize = 19.sp
		)
		Row {
			Text(
				text = amount ,
				fontFamily = mid ,
				color = Color.Black ,
				fontSize = 19.sp
			)
			Text(
				text = " TRY" ,
				fontFamily = mid ,
				color = Color.Gray ,
				fontSize = 16.sp
			)
		}

	}
}

@Composable
fun Div()
{
	Divider(
		color = gray20 ,
		thickness = 1.dp , // Çizginin kalınlığı
		modifier = Modifier.padding(horizontal = 0.dp , vertical = 14.dp) // İsteğe bağlı boşluk
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topBarSepet(navController : NavHostController)
{
	TopAppBar(title = {

		// Top Section (Back button and Title)
		Row(
			verticalAlignment = Alignment.CenterVertically ,

			modifier = Modifier.fillMaxWidth()
		) {
			IconButton(onClick = { navController.popBackStack() }) {
				Icon(
					imageVector = Icons.Default.ArrowBack ,
					contentDescription = "Back"
				)
			}
			Text(
				text = "Cart" ,
				fontSize = 24.sp ,
				fontWeight = FontWeight.Bold ,
				modifier = Modifier
					.weight(1f)
					.padding(start = 8.dp)

			)
		}


	})
}

package com.example.yemekler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.yemekler.ui.theme.YemeklerTheme
import com.example.yemekler.uix.viewModels.AnasayfaViewModel
import com.example.yemekler.uix.viewModels.FavorilerViewModel
import com.example.yemekler.uix.viewModels.SepetViewModel
import com.example.yemekler.uix.viewModels.UrunDetayViewModel
import com.example.yemekler.uix.views.Anasayfa
import com.example.yemekler.uix.views.Sepet
import com.example.yemekler.uix.views.UrunDetay
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val anasayfaViewModel: AnasayfaViewModel by viewModels()
    private val sepetViewModel: SepetViewModel by viewModels()
    private val urunDetayViewModel: UrunDetayViewModel by viewModels()
    private val FavorilerViewModel : FavorilerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YemeklerTheme {
                SayfaGecisleri(anasayfaViewModel,urunDetayViewModel,sepetViewModel,FavorilerViewModel)
            }
        }
    }
}

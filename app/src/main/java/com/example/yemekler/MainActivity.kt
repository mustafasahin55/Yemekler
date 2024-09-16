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
import com.example.yemekler.uix.views.Anasayfa
import com.example.yemekler.uix.views.Sepet
import com.example.yemekler.uix.views.UrunDetay
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        val anasayfaViewModel = AnasayfaViewModel by viewModels()


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YemeklerTheme {
                Anasayfa(anasayfaViewModel = anasayfaViewModel)
            }
        }
    }
}


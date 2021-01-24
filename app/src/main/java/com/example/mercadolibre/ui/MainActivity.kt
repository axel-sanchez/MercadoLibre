package com.example.mercadolibre.ui

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mercadolibre.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity principal de nuestra aplicación
 * @author Axel Sanchez
 */
@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
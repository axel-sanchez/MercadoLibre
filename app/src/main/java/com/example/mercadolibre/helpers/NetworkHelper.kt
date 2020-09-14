package com.example.mercadolibre.helpers

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import org.koin.standalone.KoinComponent
import java.util.*

object NetworkHelper: KoinComponent {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = Objects.requireNonNull(cm).activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}
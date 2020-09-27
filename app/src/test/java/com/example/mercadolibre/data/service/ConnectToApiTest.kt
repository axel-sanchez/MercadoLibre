package com.example.mercadolibre.data.service

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConnectToApiTest {

    private var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var service = retrofit.create(ApiService::class.java)

    @Test
    fun getSearch() = runBlocking {
        foo()
    }

    private suspend fun foo() {
        coroutineScope {
            launch {
                assertTrue(service.search("Lenovo").isSuccessful)
            }
        }
    }
}
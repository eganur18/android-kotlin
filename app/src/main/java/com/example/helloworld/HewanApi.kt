package com.example.helloworld

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +         //capital semua artinya constanta
        "indraazimi/galeri-hewan/static-api/"                //ini url dari static api json
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)                                                      //url nya
    .build()

//terjemahin end point(API) dengan method nya
//contoh API 1 method nya apa, begitu seterusnya

interface HewanApiService { //karena interface punya fungsi untuk menampilkan endpoint ke method
    @GET("static-api.json")           //memanggil end point, kenapa disini hanya potongan url ga url lengkap ? supaya disaat pindah domain, yg diubah hanya BASE_URL diatas
    suspend fun getHewan(): String //balikan bentuk string
}

object HewanApi {
    val service: HewanApiService by lazy {
        retrofit.create(HewanApiService::class.java)        //untuk create hewanapiservice
    }
}
package com.example.helloworld

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +         //capital semua artinya constanta
        "indraazimi/galeri-hewan/static-api/"                //ini url dari static api json

private val moshi = Moshi.Builder()                         //ini untuk build moshi dibawah
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))                //moshi dilakukan untuk membuat json jd POJO Plain Old Java Object
    .baseUrl(BASE_URL)                                                      //url nya
    .build()

//terjemahin end point(API) dengan method nya
//contoh API 1 method nya apa, begitu seterusnya

interface HewanApiService { //karena interface punya fungsi untuk menampilkan endpoint ke method
    @GET("static-api.json")           //memanggil end point, kenapa disini hanya potongan url ga url lengkap ? supaya disaat pindah domain, yg diubah hanya BASE_URL diatas
//    suspend fun getHewan(): String //balikan bentuk string
    suspend fun getHewan(): List<Hewan> //Karena sudah pakai moshi maka json tadi tinggal di panggil saja
}

object HewanApi {
    val service: HewanApiService by lazy {
        retrofit.create(HewanApiService::class.java)        //untuk create hewanapiservice
    }
    //method untuk pull gambar dr internet
    fun getHewanUrl(nama: String) = "$BASE_URL$nama.jpg"     //gethewan bernama string dan dia akan mengembalikan string

}
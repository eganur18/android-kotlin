package com.example.helloworld

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class MainViewModel : ViewModel() {
    /*
        liveData digunakan untuk melakukan collect data secara asynchron
        kegunaan untuk mengambil data dr internet, untuk mensiasati cepat atau lamanya proses itu
     */
//        val data = initData()
    val data = MutableLiveData<List<Hewan>>()
    init {
//        Log.d("MVVM", "viewModel di init")
        // melakukan proses yang asinkron dengan coroutine
//        viewModelScope.launch {
//            delay(3000L)
//            data.postValue(initData()) //kalau update background pakai post, tp kalau dr thread utama pakai value
//        }
        retrieveDataFromServer() //panggil di init
    }

    private fun retrieveDataFromServer() {              //karna ambil data dr internet maka buat coroutine blocking
        viewModelScope.launch(Dispatchers.IO) {
            try {           //karna ambil data dr internet maka harus ada try
                val result = HewanApi.service.getHewan()
//                Log.d("MainViewModel", result)
                data.postValue(result)
            } catch (e: Exception) {        //Jika gagal maka tulis juga error nya kenapa
                Log.d("MainViewModel", "Gagal: ${e.message}")
            }
        }
    }

    fun getData(): LiveData<List<Hewan>> = data

    /*override fun onCleared() {
        Log.d("MVVM", "ViewModel di-cleared")
        super.onCleared()
    }*/

}

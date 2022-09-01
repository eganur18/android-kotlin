package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val ViewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MVVM", "onCreate dijalankan")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val divider = DividerItemDecoration(this, RecyclerView.VERTICAL)
        binding.recyclerView.addItemDecoration(divider) // membuat pembatas garis list item
        binding.recyclerView.adapter = MainAdapter(ViewModel.data)
        binding.recyclerView.setHasFixedSize(true) //agar tidak berubah ukuran
    }

    override fun onStart() {
        Log.d("MVVM", "onStart dijalankan")
        super.onStart()
    }

    override fun onStop() {
        Log.d("MVVM", "onStop dijalankan")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("MVVM", "onDestroy dijalankan")
        super.onDestroy()
    }
}
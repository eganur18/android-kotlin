package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
//        Log.d("MVVM", "onCreate dijalankan")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val divider = DividerItemDecoration(this, RecyclerView.VERTICAL)
        binding.recyclerView.addItemDecoration(divider) // membuat pembatas garis list item
        val adapter = MainAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true) //agar tidak berubah ukuran

        ViewModel.getData().observe(this) {
            adapter.updateData(it)
        }

    }

    /*override fun onStart() {
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
    }*/
}
package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var haloTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        haloTextView = findViewById(R.id.haloTextView)
//        haloTextView.text = "0"
        var count: Int = 0

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
//            haloTextView.text = "Assalamu'alaikum"
            count++
            haloTextView.text = "$count"
//            toggleText()
        }
    }
//    private fun toggleText() {
//        if(haloTextView.text == "Hello World!")
//            haloTextView.text = "Assalamu'alaikum"
//        else
//            haloTextView.text = "Hello World!"
//        }
//    }
}
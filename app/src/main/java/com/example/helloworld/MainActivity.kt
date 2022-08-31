package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var count = 0
        binding.minButton.setOnClickListener {

            if(count <= 0){
                count = 0
                binding.qtyTextView.text = "$count"
            } else {
                count--
                binding.qtyTextView.text = "$count"
            }
        }

        binding.plusButton.setOnClickListener {
            count++
            binding.qtyTextView.text = "$count"
        }

        binding.orderButton.setOnClickListener { order() }
    }

    private fun order() {

        val orderQty = binding.qtyTextView.text.toString()
        val pemesan = binding.nameEditText.text.toString()
        val isTop1 = binding.wcCheckBox.isChecked
        var opsi1: String = "ya"
        var addCost1 = "7500"
        if(!isTop1) {
            opsi1 = "tidak"
            addCost1 = "0"
        }
        val isTop2 = binding.cCheckBox2.isChecked
        var opsi2: String = "ya"
        var addCost2 = "5000"
        val hargaDasar = "10000"
        if(!isTop2) {
            opsi2 = "tidak"
            addCost2 = "0"
        }

        val harga = orderQty.toFloat() * (hargaDasar.toFloat() + addCost1.toFloat() + addCost2.toFloat())

        binding.BuyerTextView.text = getString(R.string.name_input, pemesan)
        binding.QTTextView.text = getString(R.string.qty_out, orderQty)
        binding.textView7.text = getString(R.string.topping_order_label)
        binding.opsiTextView1.text = getString(R.string.WCtopping_out, opsi1)
        binding.opsiTextView2.text = getString(R.string.Ctopping_out, opsi2)
        binding.hargaTextView.text = getString(R.string.harga_out, harga)

    }

}
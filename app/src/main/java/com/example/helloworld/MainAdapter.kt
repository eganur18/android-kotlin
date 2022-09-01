package com.example.helloworld

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld.databinding.ListItemBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() { //private val ditambahkan utk mendapatkan data

    private val data = ArrayList<Hewan>()

    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) { //perlu layout, maka perlu ditambahkan dalam kurung itu

        fun bind(hewan: Hewan) = with(binding) {
            imageView.setImageResource(hewan.gambarResId)
            textView.text = hewan.nama
            textView2.text = hewan.namaLatin

            root.setOnClickListener {
                val message = root.context.getString(R.string.x_diklik, hewan.nama) //karena getstring dari context dan main activity turunan dr context maka kita harus akses dahulu
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }
        }
    }
    fun updateData(newData: List<Hewan>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { //view holder butuh inflate, dan inflate perlu inflater
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}
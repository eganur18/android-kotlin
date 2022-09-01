package com.example.helloworld

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld.databinding.ListItemBinding

class MainAdapter(private val data: List<Hewan>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() { //private val ditambahkan utk mendapatkan data

    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) { //perlu layout, maka perlu ditambahkan dalam kurung itu

        fun bind(hewan: Hewan) = with(binding) {
            imageView.setImageResource(hewan.gambarResId)
            textView.text = hewan.nama
            textView2.text = hewan.namaLatin
        }
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
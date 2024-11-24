package com.example.ermayuni.kabupatenkotariauapp

import KabKota
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ermayuni.kabupatenkotariauapp.databinding.ItemListKabkotaBinding

class ListKabKotaAdapter(
    private val listKabKota: ArrayList<KabKota>
) : RecyclerView.Adapter<ListKabKotaAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: KabKota)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListKabkotaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val kabKota = listKabKota[position]
        with(holder.binding) {
            Glide.with(imgItemPhoto.context)
                .load(kabKota.gambar)
                .apply(RequestOptions().override(55, 55))
                .into(imgItemPhoto)

            tvItemName.text = kabKota.kabupaten_kota
            tvItemDetail.text = "Pusat Pemerintahan: ${kabKota.pusat_pemerintahan}"

            holder.itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(kabKota)
            }
        }
    }

    override fun getItemCount(): Int = listKabKota.size

    inner class ListViewHolder(val binding: ItemListKabkotaBinding) :
        RecyclerView.ViewHolder(binding.root)
}

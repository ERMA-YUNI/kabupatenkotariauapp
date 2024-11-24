package com.example.ermayuni.kabupatenkotariauapp

import KabKota
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ermayuni.kabupatenkotariauapp.databinding.ItemCardKabkotaBinding

class CardKabKotaAdapter(
    private val listKabKota: ArrayList<KabKota>
) : RecyclerView.Adapter<CardKabKotaAdapter.CardViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: KabKota)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemCardKabkotaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val kabKota = listKabKota[position]

        // Set image using Glide
        Glide.with(holder.itemView.context)
            .load(kabKota.gambar)
            .into(holder.binding.imgItemPhoto)

        // Set text values
        holder.binding.tvItemName.text = kabKota.kabupaten_kota
        holder.binding.tvItemDetail.text = "Pusat Pemerintahan: \n${kabKota.pusat_pemerintahan}"

        // Share button click listener
        holder.binding.btnSetShare.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Share Data: ${kabKota.kabupaten_kota}",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Item click listener
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(kabKota)
        }
    }

    override fun getItemCount(): Int = listKabKota.size

    inner class CardViewHolder(val binding: ItemCardKabkotaBinding) :
        RecyclerView.ViewHolder(binding.root)
}

/*
 * Copyright (c) 2019 - Present id.redlyst@gmail.com / https://github.com/redlyst/
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 *
 */

package id.red.kelasdicoding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.red.kelasdicoding.R
import id.red.kelasdicoding.model.Kelas

class ListKelasAdapter(val listKelas: ArrayList<Kelas>) : RecyclerView.Adapter<ListKelasAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (judul, deskripsi, thumbImg, kelasOleh) = listKelas[position]

        val circularProgressDrawable = CircularProgressDrawable(holder.itemView.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(holder.itemView.context)
            .load(thumbImg)
//            .apply(RequestOptions().override(65, 65))
            .apply(RequestOptions().placeholder(circularProgressDrawable))
            .into(holder.thumbImg)
        holder.tvJudul.text = judul
        holder.tvDeskripsi.text = deskripsi
        holder.tvKelasOleh.text = kelasOleh
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listKelas[holder.adapterPosition]) }

    }

    override fun getItemCount(): Int {
        return listKelas.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvJudul: TextView = itemView.findViewById(R.id.tv_judul)
        var tvDeskripsi: TextView = itemView.findViewById(R.id.tv_deskripsi)
        var thumbImg: ImageView = itemView.findViewById(R.id.thumb_imgview)
        var tvKelasOleh: TextView = itemView.findViewById(R.id.tv_kelas_oleh)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Kelas)
    }
}
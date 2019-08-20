/*
 * Copyright (c) 2019 - Present id.redlyst@gmail.com / https://github.com/redlyst/
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 *
 */

package id.red.kelasdicoding

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.red.kelasdicoding.adapter.ListKelasAdapter
import id.red.kelasdicoding.model.Kelas
import id.red.kelasdicoding.model.KelasData

class MainActivity : AppCompatActivity() {
    private lateinit var rvKelas: RecyclerView
    private var list: ArrayList<Kelas> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvKelas = findViewById(R.id.rv_kelas)
        rvKelas.setHasFixedSize(true)


        list.addAll(KelasData.listData)
        showRecyclerList()
    }

    private fun showSelectedKelas(kelas: Kelas) {
        val moveWithDataIntent = Intent(this@MainActivity, DetailActivity::class.java)
        moveWithDataIntent.putExtra(DetailActivity.X_judul, kelas.judul)
        moveWithDataIntent.putExtra(DetailActivity.X_deskripsi, kelas.detail_deskripsi)
//        moveWithDataIntent.putExtra(DetailActivity.X_thumb_img, kelas.thumb_img)
        moveWithDataIntent.putExtra(DetailActivity.X_kelas_oleh, kelas.kelas_oleh)
        moveWithDataIntent.putExtra(DetailActivity.X_detail_img, kelas.detail_img)
        moveWithDataIntent.putExtra(DetailActivity.X_referral, kelas.referral)
        moveWithDataIntent.putExtra(DetailActivity.X_reviewer_img, kelas.reviewer_img)
        moveWithDataIntent.putExtra(DetailActivity.X_reviewer_name, kelas.reviewer_name)
        moveWithDataIntent.putExtra(DetailActivity.X_reviewer_title, kelas.reviewer_title)
        moveWithDataIntent.putExtra(DetailActivity.X_reviewer_from, kelas.reviewer_from)
        moveWithDataIntent.putExtra(DetailActivity.X_reviewer_img_1, kelas.reviewer_img_1)
        moveWithDataIntent.putExtra(DetailActivity.X_reviewer_name_1, kelas.reviewer_name_1)
        moveWithDataIntent.putExtra(DetailActivity.X_reviewer_title_1, kelas.reviewer_title_1)
        moveWithDataIntent.putExtra(DetailActivity.X_reviewer_from_1, kelas.reviewer_from_1)
        moveWithDataIntent.putExtra(DetailActivity.X_url_kelas, kelas.url_kelas)

        startActivity(moveWithDataIntent)
    }

    private fun showRecyclerList() {
        rvKelas.layoutManager = LinearLayoutManager(this)
        val listKelasAdapter = ListKelasAdapter(list)
        rvKelas.adapter = listKelasAdapter

        listKelasAdapter.setOnItemClickCallback(object : ListKelasAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Kelas) {
                showSelectedKelas(data)
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.about, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_about -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }
}

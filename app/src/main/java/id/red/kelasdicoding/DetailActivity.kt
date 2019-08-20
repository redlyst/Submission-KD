/*
 * Copyright (c) 2019 - Present id.redlyst@gmail.com / https://github.com/redlyst/
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 *
 */

package id.red.kelasdicoding

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private var myClipboard: ClipboardManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        myClipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

        val judul = intent.getStringExtra(X_judul)
        val deskripsi = intent.getStringExtra(X_deskripsi)
//        val thumb_img = intent.getStringExtra(X_thumb_img)
        val kelas_oleh = intent.getStringExtra(X_kelas_oleh)
        val detail_img = intent.getStringExtra(X_detail_img)
        val referral = intent.getStringExtra(X_referral)
        val reviewer_img = intent.getStringExtra(X_reviewer_img)
        val reviewer_name = intent.getStringExtra(X_reviewer_name)
        val reviewer_title = intent.getStringExtra(X_reviewer_title)
        val reviewer_from = intent.getStringExtra(X_reviewer_from)
        val reviewer_img_1 = intent.getStringExtra(X_reviewer_img_1)
        val reviewer_name_1 = intent.getStringExtra(X_reviewer_name_1)
        val reviewer_title_1 = intent.getStringExtra(X_reviewer_title_1)
        val reviewer_from_1 = intent.getStringExtra(X_reviewer_from_1)

        tv_judul.text = judul
        tv_deskripsi.text = deskripsi
        et_referral_code.setText(referral)

        tv_kelas_oleh.text = kelas_oleh


        val btnCopy: Button = findViewById(R.id.btnCopy)
        btnCopy.setOnClickListener(this)


        val btnBrowser: Button = findViewById(R.id.btnBrowser)
        btnBrowser.setOnClickListener(this)

        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(applicationContext)
            .load(detail_img)
            .apply(RequestOptions().placeholder(circularProgressDrawable))
            .into(detail_imgview)

        Glide.with(applicationContext)
            .load(reviewer_img)
            .into(reviewer_imgview)

        tv_reviewer_name.text = reviewer_name
        tv_reviewer_from.text = reviewer_from
        tv_reviewer_title.text = reviewer_title


        Glide.with(applicationContext)
            .load(reviewer_img_1)
            .into(reviewer_imgview_1)

        tv_reviewer_name_1.text = reviewer_name_1
        tv_reviewer_from_1.text = reviewer_from_1
        tv_reviewer_title_1.text = reviewer_title_1

        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = judul
            (supportActionBar as ActionBar).setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnCopy -> {

                val myClip: ClipData
                val text = et_referral_code.text
                myClip = ClipData.newPlainText("text", text)
                myClipboard?.setPrimaryClip(myClip)

                Toast.makeText(this, "Referral URL Copied", Toast.LENGTH_SHORT).show()
            }
            R.id.btnBrowser -> {

                val url_kelas = intent.getStringExtra(X_url_kelas)
                val urlKelas = url_kelas
                val openBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(urlKelas))
                startActivity(openBrowser)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val X_judul = "X_judul"
        const val X_deskripsi = "X_deskripsi"
        //        const val X_thumb_img = "X_thumb_img"
        const val X_kelas_oleh = "X_kelas_oleh"
        const val X_detail_img = "X_detail_img"
        const val X_referral = "X_referral"
        const val X_reviewer_img = "X_reviewer_img"
        const val X_reviewer_name = "X_reviewer_name"
        const val X_reviewer_title = "X_reviewer_title"
        const val X_reviewer_from = "X_reviewer_from"
        const val X_reviewer_img_1 = "X_reviewer_img_1"
        const val X_reviewer_name_1 = "X_reviewer_name_1"
        const val X_reviewer_title_1 = "X_reviewer_title_1"
        const val X_reviewer_from_1 = "X_reviewer_from_1"
        const val X_url_kelas = "X_url_kelas"
    }
}

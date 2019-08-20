/*
 * Copyright (c) 2019 - Present id.redlyst@gmail.com / https://github.com/redlyst/
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 *
 */

package id.red.kelasdicoding

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {
    private var title: String = "About"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        if (supportActionBar != null) {
            (supportActionBar as ActionBar).title = title
            (supportActionBar as ActionBar).setDisplayHomeAsUpEnabled(true)
        }


        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(applicationContext)
            .load("https://redlyst.id/profile_img.jpg")
            .apply(RequestOptions().placeholder(circularProgressDrawable))
            .into(profile_img)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}

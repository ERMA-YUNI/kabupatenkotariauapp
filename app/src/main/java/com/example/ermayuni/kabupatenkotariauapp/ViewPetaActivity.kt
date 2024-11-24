package com.example.ermayuni.kabupatenkotariauapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.example.ermayuni.kabupatenkotariauapp.databinding.ActivityViewPetaBinding

class ViewPetaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPetaBinding

    companion object {
        const val EXTRA_URL_PETA = "extra_url_peta"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPetaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val urlPeta = intent.getStringExtra(EXTRA_URL_PETA)

        // Initialize GlideToVectorYou request builder
        val requestBuilder = GlideToVectorYou
            .init()
            .with(this)
            .requestBuilder

        // Load the SVG image using GlideToVectorYou
        requestBuilder
            .load(urlPeta)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.baseline_image_24)
                    .error(R.drawable.baseline_image_24)
                    .fitCenter()
            )
            .into(binding.imgItemPhoto)
    }
}

package com.example.kurticonlinebanking

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.example.kurticonlinebanking.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")// fixing own loading screen
@Suppress("DEPRECATION")//this is implemented to solve handler.java
class SplashScreen : AppCompatActivity() {

    private val screentime: Long = 10000
    private val textinterval: Long = 2500 // 0.5 seconds
    private lateinit var splashbinding: ActivitySplashScreenBinding
    private val textList = listOf(
        "Maximize your savings with our financial tips and tricks!",
        "Discover new ways to grow your money!",
        "Managing your finances made easy with our expert advice!",
        "Explore smart banking strategies for a prosperous future!",
        "Enjoy a wealth of financial wisdom at your fingertips!",
        "Create a secure financial future with our valuable insights!",
        "Unlock the art of saving and investing with our guidance!",
        "Delight in financial success with our expert recommendations!"
    )

    private var currentTextIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashbinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashbinding.root)

        loadGif()
        startMainActivityWithDelay()
        updateTextWithDelay()
    }

    private fun loadGif(){
        Glide.with(this)
            .asGif()
            .load(R.drawable.splash_logo)
            .listener(object : RequestListener<GifDrawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable,
                    model: Any,
                    target: Target<GifDrawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    resource.setLoopCount(1)
                    return false
                }
            })
            .into(splashbinding.gifImageView)
    }

    private fun startMainActivityWithDelay() {
        Handler().postDelayed({
            startActivity(Intent(this, Login::class.java))
            finish()
        }, screentime)
    }

    private fun updateTextWithDelay(){
        val textHandler = Handler()
        textHandler.postDelayed(object : Runnable {
            override fun run() {
               splashbinding.textView.text = textList[currentTextIndex]
                currentTextIndex = (currentTextIndex + 1) % textList.size
                textHandler.postDelayed(this, textinterval)
            }
        }, textinterval)
    }

}


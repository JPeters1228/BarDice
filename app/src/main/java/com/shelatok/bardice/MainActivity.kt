package com.shelatok.bardice

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    fun onStartButtonClick(view: View) {
        val gameIntent = Intent(this, GameActivity::class.java)
        startActivity(gameIntent)
    }
}

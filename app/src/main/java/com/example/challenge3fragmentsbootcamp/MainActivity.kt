package com.example.challenge3fragmentsbootcamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity(), ListFragment.CryptoSelectListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCryptoSelected(crypto: Crypto){
        findViewById<ImageView>(R.id.iv_go_back).visibility = View.VISIBLE
        findViewById<TextView>(R.id.toolbar_title).text = crypto.nombre
        findNavController(R.id.main_navigation_container)
            .navigate(ListFragmentDirections.actionListFragmentToDetailsFragment(crypto))
    }

    fun clickBack(@Suppress("UNUSED_PARAMETER") view: View?) {
        findViewById<TextView>(R.id.toolbar_title).text = "Criptomonedas"
        findViewById<ImageView>(R.id.iv_go_back).visibility = View.GONE
        onBackPressed()
    }

}
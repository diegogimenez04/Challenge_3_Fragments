package com.example.challenge3fragmentsbootcamp.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challenge3fragmentsbootcamp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
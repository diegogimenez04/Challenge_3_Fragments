package com.example.challenge3fragmentsbootcamp.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge3fragmentsbootcamp.Crypto
import com.example.challenge3fragmentsbootcamp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.krRecycler.layoutManager= LinearLayoutManager(this)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val adapter = KrAdapter()
        binding.krRecycler.adapter = adapter

        viewModel.krList.observe(this) {
            adapter.submitList(it)
            handleEmptyView(it, binding)
        }
    }

    private fun handleEmptyView(it: MutableList<Crypto>, binding: ActivityMainBinding) {
        if (it.isEmpty())
            binding.krEmptyView.visibility = View.VISIBLE
        else
            binding.krEmptyView.visibility = View.GONE
    }
}
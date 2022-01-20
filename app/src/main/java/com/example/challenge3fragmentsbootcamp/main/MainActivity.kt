package com.example.challenge3fragmentsbootcamp.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge3fragmentsbootcamp.Crypto
import com.example.challenge3fragmentsbootcamp.DetailsFragment
import com.example.challenge3fragmentsbootcamp.R
import com.example.challenge3fragmentsbootcamp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var detailFragment: DetailsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.krRecycler.layoutManager= LinearLayoutManager(this)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val adapter = KrAdapter()
        binding.krRecycler.adapter = adapter

        /*detailFragment =
            supportFragmentManager.findFragmentById(R.id.details_fragment) as DetailsFragment
*/
        adapter.onItemClickListener = {
//            detailFragment.setCryptoData(it)
        }

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
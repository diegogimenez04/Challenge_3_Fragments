package com.example.challenge3fragmentsbootcamp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailsFragment : Fragment() {
    private lateinit var imageView: ImageView
    private lateinit var name: TextView
    private lateinit var abrv: TextView
    private lateinit var price: TextView
    private lateinit var date: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_details, container, false)

        imageView = rootView.findViewById(R.id.d_crypto_image)
        name = rootView.findViewById(R.id.d_crypto_name)
        abrv = rootView.findViewById(R.id.d_crypto_abrv)
        price = rootView.findViewById(R.id.d_crypto_price)
        date = rootView.findViewById(R.id.d_crypto_date)

        return rootView
    }

    fun setCryptoData(crypto: Crypto){
        Glide.with(this)
            .load(crypto.imagen.path)
            .into(imageView)

        name.text = crypto.nombre
        abrv.text = crypto.abreviatura
        price.text = crypto.precio.toString()
        date.text = crypto.fecha_creacion

    }
}
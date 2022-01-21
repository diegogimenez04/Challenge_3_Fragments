package com.example.challenge3fragmentsbootcamp

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.card.MaterialCardView

class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()

    private lateinit var imageView: ImageView
    private lateinit var name: TextView
    private lateinit var abrv: TextView
    private lateinit var price: TextView
    private lateinit var date: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_details, container, false)

        val returnButton = rootView.findViewById<MaterialCardView>(R.id.return_button)
        returnButton.setOnClickListener {
            requireActivity().findViewById<ImageView>(R.id.iv_go_back).visibility = View.GONE
            requireActivity().findViewById<TextView>(R.id.toolbar_title).text = getString(R.string.criptomonedas)
            requireActivity().onBackPressed()
        }

        val crypto = args.crypto

        imageView = rootView.findViewById(R.id.d_crypto_image)
        name = rootView.findViewById(R.id.d_crypto_name)
        abrv = rootView.findViewById(R.id.d_crypto_abrv)
        price = rootView.findViewById(R.id.d_crypto_price)
        date = rootView.findViewById(R.id.d_crypto_date)

        setCryptoData(crypto)

        return rootView
    }

    fun setCryptoData(crypto: Crypto){
        Glide.with(this)
            .load(crypto.imagen.path)
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            })
            .error(R.drawable.ic_baseline_image_not_supported_24)
            .into(imageView)

        name.text = crypto.nombre
        abrv.text = crypto.abreviatura
        price.text = getString(R.string.usd_461_5, crypto.precio)
        date.text = getString(R.string.date, crypto.fecha_creacion)

    }
}
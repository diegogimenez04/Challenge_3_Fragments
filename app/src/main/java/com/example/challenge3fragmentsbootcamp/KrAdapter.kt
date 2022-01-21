package com.example.challenge3fragmentsbootcamp

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import com.example.challenge3fragmentsbootcamp.Crypto
import com.example.challenge3fragmentsbootcamp.databinding.ListItemBinding

import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


private val TAG = KrAdapter::class.java.simpleName

class KrAdapter : ListAdapter<Crypto, KrAdapter.EqViewHolder>(DiffCallBack) {

    companion object DiffCallBack : DiffUtil.ItemCallback<Crypto>() {
        override fun areItemsTheSame(oldItem: Crypto, newItem: Crypto): Boolean {
            return oldItem.nombre == newItem.nombre
        }

        override fun areContentsTheSame(oldItem: Crypto, newItem: Crypto): Boolean {
            return oldItem == newItem
        }
    }

    lateinit var onItemClickListener: (Crypto) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KrAdapter.EqViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context))
        return EqViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KrAdapter.EqViewHolder, position: Int) {
        val crypto: Crypto = getItem(position)
        holder.bind(crypto)
    }

    inner class EqViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(crypto: Crypto) {
            binding.cryptoName.text = crypto.nombre
            binding.cryptoAbrv.text = crypto.abreviatura

            val image = crypto.imagen.path
            Glide.with(binding.root)
                .load(image)
                .listener(object : RequestListener<Drawable> {
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
                .into(binding.cryptoImage)

            binding.root.setOnClickListener {
                if (::onItemClickListener.isInitialized) {
                    onItemClickListener(crypto)
                } else {
                    Log.d(TAG, "onItemClickListener not initialized")
                }
            }

            binding.executePendingBindings()
        }
    }
}
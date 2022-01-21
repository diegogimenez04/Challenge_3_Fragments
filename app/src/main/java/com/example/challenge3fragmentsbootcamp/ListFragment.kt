package com.example.challenge3fragmentsbootcamp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge3fragmentsbootcamp.main.MainViewModel
import java.lang.ClassCastException

class ListFragment : Fragment() {

    interface CryptoSelectListener {
        fun onCryptoSelected(crypto: Crypto)
    }

    private lateinit var cryptoSelectListener: CryptoSelectListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        cryptoSelectListener = try {
            context as CryptoSelectListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement CryptoSelectListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_list, container, false)

        val recycler = rootView.findViewById<RecyclerView>(R.id.kr_recycler)
        recycler.layoutManager = LinearLayoutManager(requireActivity())

        val adapter = KrAdapter()
        recycler.adapter = adapter

        adapter.onItemClickListener = {
            cryptoSelectListener.onCryptoSelected(it)
        }

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.reloadCryptos()

        viewModel.krList.observe(requireActivity()) {
            adapter.submitList(it)
            handleEmptyView(it, rootView)
        }

        return rootView
    }

    private fun handleEmptyView(it: MutableList<Crypto>, view: View) {
        view.findViewById<TextView>(R.id.empty_view).visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
        /*if (it.isEmpty())
            view.findViewById<TextView>(R.id.empty_view).visibility = View.VISIBLE
        else
            view.findViewById<TextView>(R.id.empty_view).visibility = View.GONE*/
    }
}
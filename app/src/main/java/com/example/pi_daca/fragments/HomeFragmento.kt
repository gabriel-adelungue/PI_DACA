package com.example.pi_daca.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pi_daca.R
import com.example.pi_daca.data.ReportsCardObject
import com.example.pi_daca.data.reportCardData
import com.example.pi_daca.databinding.*

class HomeFragmento : Fragment() {
    lateinit var binding: HomeFragmentoBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = HomeFragmentoBinding.inflate(layoutInflater)

        fun refreshUi(list: List<reportCardData>){
            binding.containerHome.removeAllViews()

            list.forEach{
                val cardBinding = CardBinding.inflate(layoutInflater)

                cardBinding.titleReport.text = it.title
                cardBinding.descReport.text = it.desc
                binding.containerHome.addView(cardBinding.root)
            }
        }


        return binding.root
    }
}
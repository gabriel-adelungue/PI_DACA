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
import com.example.pi_daca.databinding.ActivityFaqBinding
import com.example.pi_daca.databinding.CardItemBinding
import com.example.pi_daca.databinding.HomeFragmentoBinding
import com.example.pi_daca.databinding.ReportCardBinding

class HomeFragmento : Fragment() {
    lateinit var binding: HomeFragmentoBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = HomeFragmentoBinding.inflate(inflater)

        fun updateCard(){
            binding.containerHome.removeAllViews()

            ReportsCardObject.listReports.forEach{
                val card = ReportCardBinding.inflate(layoutInflater)

                card.titleReport.text = it.title
                card.descReport.text = it.desc

                binding.containerHome.addView(card.root)
            }
        }

        return binding.root
    }

}
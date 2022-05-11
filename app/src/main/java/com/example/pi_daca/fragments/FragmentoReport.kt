package com.example.pi_daca.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pi_daca.R
import com.example.pi_daca.activitys.FormActivity
import com.example.pi_daca.databinding.FragmentoReportBinding


class FragmentoReport : Fragment() {
    lateinit var binding: FragmentoReportBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    binding = FragmentoReportBinding.inflate(layoutInflater)

    binding.buttonInfra.setOnClickListener{

        val intent = Intent(this, FormActivity::class.java)



    }
        return binding.root
    }
}
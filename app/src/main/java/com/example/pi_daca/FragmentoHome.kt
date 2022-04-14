package com.example.pi_daca

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pi_daca.databinding.FragmentoHomeBinding

// teste versionamento
// teste caco

class FragmentoHome : Fragment() {
    lateinit var binding: FragmentoHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentoHomeBinding.inflate(inflater)

        return binding.root
    }



}
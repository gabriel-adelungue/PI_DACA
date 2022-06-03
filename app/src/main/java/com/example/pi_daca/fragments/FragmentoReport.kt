package com.example.pi_daca.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pi_daca.activitys.FormActivity
import com.example.pi_daca.databinding.FragmentoReportBinding


class FragmentoReport : Fragment() {
    lateinit var binding: FragmentoReportBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    binding = FragmentoReportBinding.inflate(layoutInflater)


        binding.buttonInfra.setOnClickListener{
            val intent = Intent (getActivity(), FormActivity::class.java)
            intent.putExtra("categoria", binding.buttonInfra.text.toString())
            getActivity()?.startActivity(intent)
        }

        binding.buttonMaintance.setOnClickListener{
            val intent = Intent (getActivity(), FormActivity::class.java)
            intent.putExtra("categoria", binding.buttonMaintance.text.toString())
            getActivity()?.startActivity(intent)
        }

        binding.buttonClean.setOnClickListener{
            val intent = Intent (getActivity(), FormActivity::class.java)
            intent.putExtra("categoria", binding.buttonClean.text.toString())
            getActivity()?.startActivity(intent)
        }

        binding.buttonAdm.setOnClickListener{
            val intent = Intent (getActivity(), FormActivity::class.java)
            intent.putExtra("categoria", binding.buttonAdm.text.toString())
            getActivity()?.startActivity(intent)
        }

        binding.buttonLab.setOnClickListener{
            val intent = Intent (getActivity(), FormActivity::class.java)
            intent.putExtra("categoria", binding.buttonLab.text.toString())
            getActivity()?.startActivity(intent)
        }

        binding.buttonFood.setOnClickListener{
            val intent = Intent (getActivity(), FormActivity::class.java)
            intent.putExtra("categoria", binding.buttonFood.text.toString())
            getActivity()?.startActivity(intent)
        }




        return binding.root
    }
}

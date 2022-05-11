package com.example.pi_daca.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pi_daca.data.reportCardData
import com.example.pi_daca.databinding.ActivityFormBinding
import com.example.pi_daca.databinding.HomeFragmentoBinding

class FormActivity : AppCompatActivity() {
    lateinit var binding: ActivityFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
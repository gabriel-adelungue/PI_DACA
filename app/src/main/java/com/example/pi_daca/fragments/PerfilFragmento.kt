package com.example.pi_daca.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pi_daca.activitys.LoginActivity
import com.example.pi_daca.databinding.PerfilFragmentoBinding
import com.google.firebase.auth.FirebaseAuth

class PerfilFragmento : Fragment() {

    private lateinit var binding: PerfilFragmentoBinding

    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PerfilFragmentoBinding.inflate(layoutInflater)
        setupFireBase()
        binding.logoutbtn.setOnClickListener(){
            firebaseAuth.signOut()
            validUser()
        }
        return binding.root

    }


    private fun setupFireBase(){
        firebaseAuth = FirebaseAuth.getInstance()
        validUser()
    }

    private fun validUser(){

        val firebaseUser = firebaseAuth.currentUser

        if (firebaseUser != null){
            val email = firebaseUser.email

            binding.emailTv.text = email
        }
        else{
            val i = Intent(null, LoginActivity::class.java)
            startActivity(i)
        }
    }





}
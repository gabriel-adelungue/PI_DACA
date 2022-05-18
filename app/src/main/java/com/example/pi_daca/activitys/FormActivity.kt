package com.example.pi_daca.activitys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.pi_daca.databinding.ActivityFormBinding
import com.example.pi_daca.data.reportCardData
import com.example.pi_daca.data.ReportsCardObject
import com.example.pi_daca.databinding.HomeFragmentoBinding
import com.example.pi_daca.fragments.HomeFragmento
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class FormActivity : AppCompatActivity() {
    lateinit var binding: ActivityFormBinding
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFireBase()

        binding.buttonEnviarForm.setOnClickListener{
            insert()
            val cardReport = reportCardData(title=binding.editCategoriaForm.text.toString(),
            desc=binding.editDescricaoForm.text.toString())

            ReportsCardObject.listReports.add(cardReport)


            backToHome();
        }
    }

    fun insert(){
        val report = reportCardData(title=binding.editCategoriaForm.text.toString(),
            desc=binding.editDescricaoForm.text.toString())
        val newNode = database.child("reports").push()
        report.id = newNode.key
        newNode.setValue(report)
    }

    fun setupFireBase(){
        val user = getCurrentUser()

        if(user != null){
            database = FirebaseDatabase.getInstance().reference.child(user.uid)
        }
    }

    fun getCurrentUser(): FirebaseUser?{
        return FirebaseAuth.getInstance().currentUser
    }

    fun backToHome() {
        val i = Intent(this, HomeFragmento::class.java)
        startActivity(i)

    }
}


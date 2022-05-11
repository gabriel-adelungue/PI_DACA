package com.example.pi_daca.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.pi_daca.databinding.ActivityFormBinding
import com.example.pi_daca.data.reportCardData
import com.example.pi_daca.data.ReportsCardObject
import com.example.pi_daca.databinding.HomeFragmentoBinding
import com.google.firebase.database.DatabaseReference

class FormActivity : AppCompatActivity() {
    lateinit var binding: ActivityFormBinding
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonEnviarForm.setOnClickListener{
            insert()
            val cardReport = reportCardData(title=binding.editCategoriaForm.text.toString(),
            desc=binding.editDescricaoForm.text.toString())

            ReportsCardObject.listReports.add(cardReport)


            val i = Intent(this, HomeFragmentoBinding::class.java)
            startActivity(i)
        }
    }

    fun insert(){
        val report = reportCardData(title=binding.editCategoriaForm.text.toString(),
            desc=binding.editDescricaoForm.text.toString())
        val newNode = database.child("reports").push()
        report.id = newNode.key
        newNode.setValue(report)
    }
}


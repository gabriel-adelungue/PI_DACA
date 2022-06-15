package com.example.pi_daca.activitys

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.pi_daca.databinding.ActivityFormBinding
import com.example.pi_daca.data.reportCardData
import com.example.pi_daca.data.ReportsCardObject
import com.example.pi_daca.databinding.HomeFragmentoBinding
import com.example.pi_daca.fragments.FragmentoReport
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

        val categoria = intent.getStringExtra("categoria")
        binding.editCategoriaForm.setEnabled(false)
        val descricao = intent.getStringExtra("descricao")
        val localizacao = intent.getStringExtra("localizacao")
        val data = intent.getStringExtra("data")
        val status = intent.getStringExtra("status")

        binding.editCategoriaForm.setText(categoria)
        binding.editDescricaoForm.setText(descricao)
        binding.editLocalizacaoForm.setText(localizacao)
        binding.editDataForm.setText(data)
        binding.textStatus.setText(status)

        if (binding.editDescricaoForm.length() > 0) {

            binding.buttonEnviarForm.visibility = View.INVISIBLE
            binding.editLocalizacaoForm.setEnabled(false)
            binding.editDescricaoForm.setEnabled(false)
            binding.editDataForm.setEnabled(false)
        } else {
            binding.textStatus.visibility = View.INVISIBLE
        }


        setupFireBase()

        binding.buttonEnviarForm.setOnClickListener{
            insert()
            val intent = Intent (this, FragmentoReport::class.java)
            startActivity(intent)

        }
    }

    fun insert(){
        val report = reportCardData(title=binding.editCategoriaForm.text.toString(),
            desc=binding.editDescricaoForm.text.toString(), loc = binding.editLocalizacaoForm.text.toString(), data = binding.editDataForm.text.toString(),
            status= "Pendente")
        val newNode = database.child("reports").push()
        report.id = newNode.key
        newNode.setValue(report)
    }

    fun setupFireBase(){
        val user = getCurrentUser()

        if(user != null){
            database = FirebaseDatabase.getInstance().reference
        }
    }

    fun getCurrentUser(): FirebaseUser?{
        return FirebaseAuth.getInstance().currentUser
    }

}


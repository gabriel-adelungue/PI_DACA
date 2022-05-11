package com.example.pi_daca.activitys

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pi_daca.databinding.ActivityFormBinding
import com.example.pi_daca.data.reportCardData
import com.example.pi_daca.data.ReportsCardObject
import com.example.pi_daca.databinding.HomeFragmentoBinding

class FormActivity : AppCompatActivity() {
    lateinit var binding: ActivityFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonEnviarForm.setOnClickListener{
            val cardReport = reportCardData(binding.editCategoriaForm.text.toString(),
                binding.editDescricaoForm.text.toString())

            ReportsCardObject.listReports.add(cardReport)

            //DESTINADO PARA TESTES, AO CLICAR NO BOTÃO ADICIONA O REPORT AO ARRAY E VOLTA PARA HOME PARA A VISUALIZAÇÃO
            val i = Intent(this, HomeFragmentoBinding::class.java)
            startActivity(i)

            //finish()

        }
    }
}
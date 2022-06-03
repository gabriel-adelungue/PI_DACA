package com.example.pi_daca.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.pi_daca.R
import com.example.pi_daca.activitys.FormActivity
import com.example.pi_daca.data.ReportsCardObject
import com.example.pi_daca.data.reportCardData
import com.example.pi_daca.databinding.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class HomeFragmento : Fragment() {
    lateinit var binding: HomeFragmentoBinding
    lateinit var database: DatabaseReference
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = HomeFragmentoBinding.inflate(layoutInflater)

        val user = getCurrentUser()
        if(user != null){
           val name = user.displayName
            binding.welcomeNameId.text = "Bem-vindo, ${name}"
        }

        setupFireBase()
        return binding.root
    }
    fun refreshUi(list: List<reportCardData>){
        binding.containerHome.removeAllViews()

        list.forEach{
            val cardBinding = CardBinding.inflate(layoutInflater)
            cardBinding.root.setOnClickListener{
                val intent = Intent(getActivity(), FormActivity::class.java)
                intent.putExtra("categoria", cardBinding.titleReport.text.toString())
                intent.putExtra("descricao", cardBinding.descReport.text.toString())

                getActivity()?.startActivity(intent)
            }
            cardBinding.titleReport.text = it.title
            cardBinding.descReport.text = it.desc
            binding.containerHome.addView(cardBinding.root)
        }
    }

    fun setupFireBase(){
        val user = getCurrentUser()

        if(user != null){
            database = FirebaseDatabase.getInstance().reference

            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = arrayListOf<reportCardData>()
                    snapshot.child("reports").children.forEach(){
                        val map = it.value as HashMap<String, Any>

                        val id = it.key
                        val title = map.get("title") as String
                        val desc = map.get("desc") as String

                        val report = reportCardData(id, title, desc)
                        list.add(report)
                    }
                    refreshUi(list)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Main Activity", "setupFirebase", error.toException())
                    Toast.makeText(context, "Falha ao Conectar no Servidor",
                        Toast.LENGTH_LONG).show()
                }
            }
            database.addValueEventListener(valueEventListener)
        }
    }

    fun getCurrentUser(): FirebaseUser?{
        return FirebaseAuth.getInstance().currentUser
    }
}



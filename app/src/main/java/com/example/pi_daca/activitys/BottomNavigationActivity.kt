package com.example.pi_daca.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.pi_daca.R
import com.example.pi_daca.data.reportCardData
import com.example.pi_daca.databinding.ActivityBottomNavigationBinding
import com.example.pi_daca.fragments.FragmentoReport
import com.example.pi_daca.fragments.HomeFragmento
import com.example.pi_daca.fragments.PerfilFragmento
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseReference
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashMap

class BottomNavigationActivity : AppCompatActivity() {
    lateinit var binding: ActivityBottomNavigationBinding
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menuHome -> {
                    val frag = HomeFragmento()
                    supportFragmentManager.beginTransaction().replace(R.id.container, frag).commit()
                }
                R.id.menuReport -> {
                    val frag = FragmentoReport()
                    supportFragmentManager.beginTransaction().replace(R.id.container, frag).commit()
                }
                R.id.menuPerfil -> {
                    val frag = PerfilFragmento()
                    supportFragmentManager.beginTransaction().replace(R.id.container, frag).commit()
                }
                else -> {
                    val i = Intent(this, FaqActivity::class.java)
                    startActivity(i)
                    //val frag = FaqActivity()
                    //supportFragmentManager.beginTransaction().replace(R.id.container, frag).commit()
                }
            }
            true
        }

        if(getCurrentUser()==null){
            val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build())

            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setLogo(R.drawable.ic_logo_pro_report)
                    .setTheme(R.style.LoginTheme)
                    .build(), 1)
        }else{
            setupFireBase()
        }
    }

    fun getCurrentUser(): FirebaseUser?{
        return FirebaseAuth.getInstance().currentUser
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode ==  RESULT_OK){
            Toast.makeText(this, "Usuário Autenticado!", Toast.LENGTH_LONG).show()
            setupFireBase()
        }else{
            finishAffinity()
        }
    }

    fun setupFireBase(){

        val user = getCurrentUser()

        if(user != null){
            database = FirebaseDatabase.getInstance().reference

            val valueEventListener = object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = arrayListOf<reportCardData>()
                    snapshot.child("reports").children.forEach(){
                        val map = it.value as HashMap<String, Any>

                        val id = it.key
                        val title = map.get("title") as String
                        val desc = map.get("desc") as String
                        val loc = map.get("loc") as String
                        val data = map.get("data") as String
                        val status = map.get("status") as String

                        val report = reportCardData(id, title, desc, loc, data, status)
                        list.add(report)
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Main Activity", "setupFirebase", error.toException())
                    Toast.makeText(this@BottomNavigationActivity, "Falha ao Conectar no Servidor",
                        Toast.LENGTH_LONG).show()
                }
            }
            database.addValueEventListener(valueEventListener)
        }
    }

}
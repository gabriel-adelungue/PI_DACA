package com.example.pi_daca.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pi_daca.R
import com.example.pi_daca.databinding.ActivityBottomNavigationBinding
import com.example.pi_daca.databinding.HomeFragmentoBinding
import com.example.pi_daca.fragments.FragmentoReport
import com.example.pi_daca.fragments.HomeFragmento
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class BottomNavigationActivity : AppCompatActivity() {
    lateinit var binding: ActivityBottomNavigationBinding
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
            val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())
            startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(), 1)
        }

    }

    fun getCurrentUser(): FirebaseUser?{
        return FirebaseAuth.getInstance().currentUser
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode ==  RESULT_OK){
            Toast.makeText(this, "Usuário Autenticado!", Toast.LENGTH_LONG).show()
        }else{
            finishAffinity()
        }
    }

}
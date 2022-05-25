package com.example.pi_daca.activitys

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.pi_daca.R
import com.example.pi_daca.fragments.PerfilFragmento
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding

    private lateinit var actionBar:ActionBar

    private lateinit var progressDialog:ProgressDialog

    private lateinit var firebaseAuth: FirebaseAuth

    private var email=""
    private var password=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "logar"

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("login")
        progressDialog.setMessage("logando...")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()
        validUser()

        binding.noAccount.setOnclickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
        }

        binding.loginBtn.setOnclickListener{
            validData()
        }

    }

    private fun validData(){

        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailEt.setError("Email no formato invalido")

        }
        else if (TextUtils.isEmpty(password)){
            binding.passwordEt.error = "Coloque a senha"
        }
        else{
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener {

            progressDialog.dismiss()

            val firebaseUser = firebaseAuth.currentUser
            val email = firebaseUser!!.email
            Toast.makeText(this,"Login sucess $email", Toast.LENGTH_SHORT).show()
            val frag = PerfilFragmento()
            supportFragmentManager.beginTransaction().replace(R.id.container, frag).commit()
        }
            .addOnFailureListener{ e->
                progressDialog.dismiss()

                Toast.makeText(this,"falha de login com o email ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun validUser() {

        val firebaseUser = firebaseAuth.currentUser

        if(firebaseUser != null){
            val frag = PerfilFragmento()
            supportFragmentManager.beginTransaction().replace(R.id.container, frag).commit()
        }
    }

}
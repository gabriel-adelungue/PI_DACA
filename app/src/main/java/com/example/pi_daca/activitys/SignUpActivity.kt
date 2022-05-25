package com.example.pi_daca.activitys

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.pi_daca.R
import com.example.pi_daca.databinding.ActivitySignUpBinding
import com.example.pi_daca.fragments.PerfilFragmento
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var actionBar: ActionBar

    private lateinit var progressDialog: ProgressDialog

    private lateinit var firebaseAuth: FirebaseAuth

    private var email=""
    private var password=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title ="Cadastro"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)


        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Por favor, aguarde!")
        progressDialog.setMessage("Criando conta...")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.singUpBtn.setOnClickListener{
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
        else if(password.length < 6){
            binding.passwordEt.error = "Senha teve ter no minino 6 caracteres"
        }
        else{
            firebaseCadastro()
        }
    }

    private fun firebaseCadastro(){

        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                progressDialog.dismiss()

                val fireBaseUser = firebaseAuth.currentUser
                val email = fireBaseUser!!.email
                Toast.makeText(this,"Conta criada com sucesso $email", Toast.LENGTH_SHORT).show()

                val frag = PerfilFragmento()
                supportFragmentManager.beginTransaction().replace(R.id.container, frag).commit()

            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(this,"Cadastro invalido ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
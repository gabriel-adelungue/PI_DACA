package com.example.pi_daca.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pi_daca.R
import com.example.pi_daca.databinding.ActivityBottomNavigationBinding
import com.example.pi_daca.fragments.FragmentoReport
import com.example.pi_daca.fragments.HomeFragmento

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
                    val frag = HomeFragmento()
                    supportFragmentManager.beginTransaction().replace(R.id.container, frag).commit()
                }
            }

            true
        }
    }

}
package com.arasco.proje

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.arasco.proje.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun topla(view: View){
        var sayi1 = binding.sayi1.text.toString().toDoubleOrNull()
        var sayi2 = binding.sayi2.text.toString().toDoubleOrNull()

        if (sayi1 != null && sayi2 != null){
            val result = sayi1 + sayi2
            binding.textView.text = result.toString()
        }else{
            binding.textView.text = "Lütfen geçerli bir sayı girin"
        }
    }

    fun fark(view: View){
        var sayi1 = binding.sayi1.text.toString().toDoubleOrNull()
        var sayi2 = binding.sayi2.text.toString().toDoubleOrNull()

        if (sayi1 != null && sayi2 != null){
            val result = sayi1 - sayi2
            binding.textView.text = result.toString()
        }else{
            binding.textView.text = "Lütfen geçerli bir sayı girin"
        }
    }

    fun carp(view: View){
        var sayi1 = binding.sayi1.text.toString().toDoubleOrNull()
        var sayi2 = binding.sayi2.text.toString().toDoubleOrNull()

        if (sayi1 != null && sayi2 != null){
            val result = sayi1 * sayi2
            binding.textView.text = result.toString()
        }else{
            binding.textView.text = "Lütfen geçerli bir sayı girin"
        }
    }

    fun bol(view: View){
        var sayi1 = binding.sayi1.text.toString().toDoubleOrNull()
        var sayi2 = binding.sayi2.text.toString().toDoubleOrNull()

        if (sayi1 != null && sayi2 != null){
            if (sayi2 != 0.0){
                val result = sayi1 / sayi2
                binding.textView.text = result.toString()
            }else{
                binding.textView.text = "0'a bölünme hatası"
            }
        }else{
            binding.textView.text = "Lütfen geçerli bir sayı girin"
        }
    }
}

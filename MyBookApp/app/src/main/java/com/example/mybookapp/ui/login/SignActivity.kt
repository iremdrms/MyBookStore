package com.example.mybookapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mybookapp.databinding.ActivitySignBinding

class SignActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }



}
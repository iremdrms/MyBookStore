package com.example.mybookapp.ui.login.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mybookapp.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpFragment : Fragment() {


    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        binding.btnRegisterSignUp.setOnClickListener {
            signUp()
        }

        binding.tVRegisterSignUp.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }

    fun signUp() {

        with(binding) {
            val email = etRegisterEmail.text.toString()
            val password = etRegisterPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {

                    if (it.isSuccessful) {
                        Toast.makeText(requireContext(), "Kayıt Başarılı!", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            it.exception?.localizedMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "E-mail ve Şifre Girin!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }


}
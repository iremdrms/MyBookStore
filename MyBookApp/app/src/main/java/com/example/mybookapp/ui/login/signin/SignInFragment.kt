package com.example.mybookapp.ui.login.signin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mybookapp.MainActivity
import com.example.mybookapp.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSignInBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        binding.btnSignIn.setOnClickListener {
            signIn()
        }

        binding.tVSignUp.setOnClickListener {
            val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
            findNavController().navigate(action)
        }


    }

    private fun signIn() {

        binding.btnSignIn.isClickable = false

        with(binding) {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

                    if (it.isSuccessful) {
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "${it.exception?.localizedMessage}",
                            Toast.LENGTH_SHORT
                        ).show()
                        btnSignIn.isClickable = true
                    }
                }.addOnFailureListener {
                    Toast.makeText(
                        requireContext(),
                        "Geçersiz e-mail veya şifre!",
                        Toast.LENGTH_SHORT
                    ).show()
                    btnSignIn.isClickable = true

                }
            } else {
                btnSignIn.isClickable = true
                Toast.makeText(requireContext(), "E-mail ve şifre boş olamaz!", Toast.LENGTH_SHORT)
                    .show()
            }


        }

    }
}
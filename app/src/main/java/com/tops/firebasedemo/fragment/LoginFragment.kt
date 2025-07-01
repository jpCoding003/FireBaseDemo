package com.tops.firebasedemo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.tops.firebasedemo.R
import com.tops.firebasedemo.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var auth : FirebaseAuth
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
            auth = Firebase.auth
//        auth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnlogin.setOnClickListener {
            loginUser()
        }
        binding.btnsignup.setOnClickListener {
           findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

    fun loginUser(){
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task->
            if (task.isSuccessful){
                findNavController().navigate(R.id.action_loginFragment_to_addDataFragment)
            }else{
                Toast.makeText(requireContext(),
                    "Authentication failed.",Toast.LENGTH_SHORT,).show()
            }
        }
    }
}
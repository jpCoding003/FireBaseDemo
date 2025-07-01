package com.tops.firebasedemo.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.tops.firebasedemo.R
import com.tops.firebasedemo.databinding.FragmentAddDataBinding

private const val TAG = "AddDataFragment"
class AddDataFragment : Fragment() {

    private lateinit var binding: FragmentAddDataBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth


    private var firstname = binding.etFirstName.text.toString().trim()
    private var lastname = binding.etLastName.text.toString().trim()
    private var birthdate = binding.etDateOfBirth.text.toString().trim()
    private var address = binding.etAddress.text.toString().trim()
    private var email = binding.etEmail.text.toString().trim()
    private var phonenumber = binding.etContactNumber.text.toString().trim()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddDataBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        binding.btnSubmit.setOnClickListener {

            var personalData = mutableMapOf(
                "firstname" to firstname,
                "lastname" to lastname,
                "birthdate" to birthdate,
                "address" to address,
                "email" to email,
                "phonenumber" to phonenumber,
            )

            db.collection("users").add(personalData)
                .addOnSuccessListener {

                Toast.makeText(context, "Data Added Succesfully ", Toast.LENGTH_LONG).show()
                    Log.i(TAG, " Data Uploaded==> ${personalData}")

                }.addOnFailureListener {

                    Toast.makeText(context, "Data Upload Failed  ", Toast.LENGTH_LONG).show()

                }

            findNavController().navigate(R.id.action_loginFragment_to_addDataFragment)
        }

        binding.btnlogout.setOnClickListener {
            Firebase.auth.signOut()
            findNavController().navigate(R.id.action_addDataFragment_to_loginFragment)
            activity?.finish()
        }
    }
}
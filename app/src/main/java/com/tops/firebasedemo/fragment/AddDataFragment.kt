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
        db = FirebaseFirestore.getInstance()

        binding.btnSubmit.setOnClickListener {
             var firstname = binding.etFirstName.text.toString().trim()
             var lastname = binding.etLastName.text.toString().trim()
             var birthdate = binding.etDateOfBirth.text.toString().trim()
             var address = binding.etAddress.text.toString().trim()
             var email = binding.etEmail.text.toString().trim()
             var phonenumber = binding.etContactNumber.text.toString().trim()

            var personalData = mutableMapOf(
                "firstname" to firstname,
                "lastname" to lastname,
                "birthdate" to birthdate,
                "address" to address,
                "email" to email,
                "phonenumber" to phonenumber,
            )

            db.collection("users").add(personalData)
                .addOnSuccessListener {documentReference ->
                Toast.makeText(context, "Data Added Succesfully ", Toast.LENGTH_LONG).show()
                    Log.i(TAG, "ID: ${documentReference.id} Data Uploaded==> ${personalData}")
                }.addOnFailureListener {e->
                    Log.i(TAG, "Failed to Upload")
                    Log.w(TAG, "Error adding document", e)
                    Toast.makeText(context, "Data Upload Failed  ", Toast.LENGTH_LONG).show()
                }

            findNavController().navigate(R.id.action_addDataFragment_to_blankFragment)
        }

        binding.btnlogout.setOnClickListener {
            Firebase.auth.signOut()
            findNavController().navigate(R.id.action_addDataFragment_to_loginFragment)
        }
    }
}
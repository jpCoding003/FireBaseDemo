package com.tops.firebasedemo.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tops.firebasedemo.R
import com.tops.firebasedemo.databinding.FragmentCallingApiBinding
import com.tops.firebasedemo.model.Recipe
import com.tops.firebasedemo.services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CallingApiFragment : Fragment() {
    private lateinit var binding: FragmentCallingApiBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCallingApiBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getResponseData()

        binding.BackToHome.setOnClickListener {
            findNavController().navigate(R.id.action_callingApiFragment_to_blankFragment)
        }

    }

    private fun getResponseData() {
        val call: Call<Recipe> = RetrofitClient.service.getData()
        call.enqueue(object : Callback<Recipe>{
            override fun onResponse(
                call: Call<Recipe>,
                response: Response<Recipe>
            ) {
                if (response.isSuccessful){
                    val apibody: Recipe? = response.body()
//                    seperateData(apibody)

                    binding.tv1.text= apibody?.name
                    binding.tv2.setText(apibody?.id.toString())
                    Log.i("RESPONSE", "DATA== $apibody")
                }
            }

            override fun onFailure(
                call: Call<Recipe>,
                t: Throwable
            ) {
                Log.i("Error", t.message.toString())
            }

        })
    }

}
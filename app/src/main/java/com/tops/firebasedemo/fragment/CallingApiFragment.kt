package com.tops.firebasedemo.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tops.firebasedemo.R
import com.tops.firebasedemo.adapter.MyAdapter
import com.tops.firebasedemo.databinding.FragmentCallingApiBinding
import com.tops.firebasedemo.model.Recipe
import com.tops.firebasedemo.model.RecipeRoot
import com.tops.firebasedemo.services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CallingApiFragment : Fragment() {

    private lateinit var binding: FragmentCallingApiBinding
    private lateinit var adapter: MyAdapter

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
        binding.progressBar.visibility = View.VISIBLE
        val call: Call<RecipeRoot> = RetrofitClient.service.getData()

        call.enqueue(object : Callback<RecipeRoot>{
            override fun onResponse(
                call: Call<RecipeRoot?>,
                response: Response<RecipeRoot?>
            ) {
                if (response.isSuccessful) {
                    // ✅ Safely extract list of recipes from RecipeRoot
                    val recipeList: MutableList<Recipe> = response.body()?.recipes?.toMutableList() ?: mutableListOf()

                    // ✅ Set up RecyclerView with the adapter
                    adapter = MyAdapter(recipeList){recipe, i ->
                        adapter.deleteItem( i )
                    }

                    binding.rvData.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvData.adapter = adapter
                    binding.progressBar.visibility = View.GONE

                    Log.i("RESPONSE", "Ingredients: $recipeList")
                }
            }

            override fun onFailure(
                call: Call<RecipeRoot?>,
                t: Throwable
            ) {
                Log.i("Error", t.message.toString())
            }
        })

    }

}
package com.tops.firebasedemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tops.firebasedemo.databinding.ItemRowBinding
import com.tops.firebasedemo.model.Recipe
import com.tops.firebasedemo.model.RecipeRoot

class MyAdapter(private val food: MutableList<Recipe>): RecyclerView.Adapter<MyAdapter.FoodViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodViewHolder {
       val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FoodViewHolder,
        position: Int
    ) {
        val foodItem = food[position]
        holder.binding.itemID.text = (position+1).toString()
        holder.binding.itemName.text = foodItem.name
        // You can also display foodItem.ingredients.joinToString(", ") if needed
        
    }

    override fun getItemCount(): Int {
    return food.size
    }

    class FoodViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root) {
    }
}
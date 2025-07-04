package com.tops.firebasedemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tops.firebasedemo.databinding.ItemRowBinding
import com.tops.firebasedemo.model.Recipe
import com.tops.firebasedemo.model.RecipeRoot

class MyAdapter(private val food: MutableList<Recipe>, private val onDeleteClick: (Recipe, Int)-> Unit): RecyclerView.Adapter<MyAdapter.FoodViewHolder>() {
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
        holder.binding.itemID.text = foodItem.id.toString()
        holder.binding.itemName.text = foodItem.name
        // You can also display foodItem.ingredients.joinToString(", ") if needed

        Picasso.get().load(foodItem.image).into(holder.binding.thumbnail)

        holder.binding.btnDelete.setOnClickListener {
            val pos = holder.adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                onDeleteClick(foodItem, pos)
            }
        }
    }

    fun deleteItem( position: Int) {
        food.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, food.size - position)
    }

    override fun getItemCount(): Int {
    return food.size
    }

    class FoodViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root) {
    }
}
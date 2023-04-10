package com.example.foodrecipeapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.foodrecipeapp.R
import com.example.foodrecipeapp.models.Category

class UpperAdapter(
    private val recipelist: ArrayList<Category>,
    private val context: Context,
    private val userClickEvents: UserClickEvents
) : RecyclerView.Adapter<UpperAdapter.UpperViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpperViewHolder {
        return UpperViewHolder(
            LayoutInflater.from(context).inflate(R.layout.upper_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UpperViewHolder, position: Int) {
        val recipes = recipelist[position]
        holder.strCategory.text = recipes.strCategory
        Glide.with(context).load(recipes.strCategoryThumb).into(holder.recipeImage)

        holder.itemView.setOnClickListener {
            userClickEvents.onUserClick(recipes)
        }
    }

    override fun getItemCount(): Int {
        return recipelist.size
    }


    inner class UpperViewHolder(itemView: View) : ViewHolder(itemView) {

        val recipeImage: ImageView = itemView.findViewById(R.id.RecipeImage)
        val strCategory: TextView = itemView.findViewById(R.id.RecipeTitle)
    }

    interface UserClickEvents {
        fun onUserClick(category: Category)
    }
}
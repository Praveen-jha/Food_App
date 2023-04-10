package com.example.foodrecipeapp

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.foodrecipeapp.adapters.UpperAdapter
import com.example.foodrecipeapp.models.Category
import com.example.foodrecipeapp.recipeApi.RecipeInterface
import com.example.foodrecipeapp.recipeApi.RecipeUtilities
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), UpperAdapter.UserClickEvents {

    private var list = ArrayList<Category>()
    private lateinit var adapter: UpperAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        adapter = UpperAdapter(list,this,this)
        upperRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        upperRecyclerView.adapter = adapter

        val recipeApi = RecipeUtilities.getInstance().create(RecipeInterface::class.java)
        GlobalScope.launch (Dispatchers.Main){
            val result = recipeApi.getAllRecipes()
            if (result.body() != null){
                Log.d("MyTag","${result.body()}")
                list.addAll(result.body()!!.categories)
                adapter.notifyDataSetChanged()
                progressBar.visibility= View.GONE
            }
        }
    }

    override fun onUserClick(category: Category) {
       middleTextView.text = category.strCategory
        Glide.with(this).load(category.strCategoryThumb).into(middleImageView)
        detail.text = category.strCategoryDescription
    }

}
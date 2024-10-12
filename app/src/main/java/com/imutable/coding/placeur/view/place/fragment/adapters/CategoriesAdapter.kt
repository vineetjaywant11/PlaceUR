package com.imutable.coding.placeur.view.place.fragment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.imutable.coding.placeur.databinding.ViewholderCategoriesBinding
import com.imutable.coding.placeur.model.Category

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>() {

    private var categoryList = emptyList<Category>()
    private lateinit var context: Context
    lateinit var itemBinding: ViewholderCategoriesBinding

    class MyViewHolder(itemBinding: ViewholderCategoriesBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        itemBinding =
            ViewholderCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = categoryList[position]
        itemBinding.imgCategory.load(currentItem.categorySecondaryImageUrl) {
            crossfade(true)
            crossfade(10)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun setData(categories: List<Category>) {
        this.categoryList = categories
        notifyDataSetChanged()
    }
}
package com.imutable.coding.placeur.view.place.fragment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.imutable.coding.placeur.databinding.ViewholderTopCardBinding
import com.imutable.coding.placeur.model.Category

class TopFeedAdapter : RecyclerView.Adapter<TopFeedAdapter.MyViewHolder>() {

    private var categoryList = emptyList<Category>()
    private lateinit var context: Context
    lateinit var itemBinding: ViewholderTopCardBinding

    class MyViewHolder(itemBinding: ViewholderTopCardBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopFeedAdapter.MyViewHolder {
        context = parent.context
        itemBinding =
            ViewholderTopCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: TopFeedAdapter.MyViewHolder, position: Int) {
        val currentItem = categoryList[position]

        itemBinding.topFeedCategoryLabel.text = currentItem.categoryName
        itemBinding.imgTopFeed.load(currentItem.categoryImageUrl) {
            crossfade(true)
            crossfade(10)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun setData(categories: List<Category>) {
        this.categoryList = categories
        notifyDataSetChanged() //fixme using DiffUtils
    }
}
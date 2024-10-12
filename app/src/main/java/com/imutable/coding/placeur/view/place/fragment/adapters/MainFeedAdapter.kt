package com.imutable.coding.placeur.view.place.fragment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.imutable.coding.placeur.databinding.ViewholderFeedCardBinding
import com.imutable.coding.placeur.model.Place

class MainFeedAdapter() : RecyclerView.Adapter<MainFeedAdapter.MyViewHolder>() {

    private var feedList = ArrayList<Place>()
    private lateinit var context: Context
    lateinit var itemBinding: ViewholderFeedCardBinding

    class MyViewHolder(itemBinding: ViewholderFeedCardBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainFeedAdapter.MyViewHolder {
        context = parent.context
        itemBinding =
            ViewholderFeedCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)

    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = feedList[position]

        itemBinding.feedCardCategoryName.text = currentItem.placeName

        itemBinding.feedCardCategoryValue.text = currentItem.categoryId.toString()
        itemBinding.feedCardRating.rating = currentItem.placeRating

        itemBinding.imgMainFeed.load(currentItem.placeImageUrl) {
            crossfade(true)
            crossfade(100)
        }
    }

    override fun getItemCount(): Int {
        return feedList.size
    }

    fun setData(places: List<Place>) {

        val diffCallback = MainFeedDiffCallback(feedList, places)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        feedList.clear()
        feedList.addAll(places)
        diffResult.dispatchUpdatesTo(this)

    }

    class MainFeedDiffCallback(private val oldList: List<Place>, private val newList: List<Place>) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].placeName === newList.get(newItemPosition).placeName
        }

        override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
            val (_, value, name) = oldList[oldPosition]
            val (_, value1, name1) = newList[newPosition]

            return name == name1 && value == value1
        }

        @Nullable
        override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
            return super.getChangePayload(oldPosition, newPosition)
        }
    }
}
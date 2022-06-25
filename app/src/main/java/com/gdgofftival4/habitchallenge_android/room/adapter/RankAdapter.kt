package com.gdgofftival4.habitchallenge_android.room.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdgofftival4.habitchallenge_android.R
import com.gdgofftival4.habitchallenge_android.room.model.RankUiResponse

class RankAdapter(
    private val onItemClick: (userId: String) -> Unit
) : RecyclerView.Adapter<RankHolderPage>() {

    private val itemList = ArrayList<RankUiResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_rank, parent, false)
        return RankHolderPage(view, onItemClick)
    }

    override fun onBindViewHolder(holder: RankHolderPage, position: Int) {
        val viewHolder: RankHolderPage = holder
        viewHolder.onBind(itemList[position], position == 0)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun getItem(position: Int): RankUiResponse {
        return itemList[position]
    }


    fun addAll(items: List<RankUiResponse>) {
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}
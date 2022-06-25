package com.gdgofftival4.habitchallenge_android.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdgofftival4.habitchallenge_android.R
import com.gdgofftival4.habitchallenge_android.home.model.RoomUiResponse

class RoomAdapter(
    private val onItemClick: (rooId: String) -> Unit
) : RecyclerView.Adapter<RoomHolderPage>() {

    private val itemList = ArrayList<RoomUiResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_room, parent, false)
        return RoomHolderPage(view, onItemClick)
    }

    override fun onBindViewHolder(holder: RoomHolderPage, position: Int) {
        val viewHolder: RoomHolderPage = holder
        viewHolder.onBind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun getItem(position: Int): RoomUiResponse {
        return itemList[position]
    }


    fun addAll(items: List<RoomUiResponse>) {
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}
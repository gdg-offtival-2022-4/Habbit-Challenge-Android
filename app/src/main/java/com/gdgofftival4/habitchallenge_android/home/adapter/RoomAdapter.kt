package com.gdgofftival4.habitchallenge_android.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdgofftival4.habitchallenge_android.R
import com.gdgofftival4.habitchallenge_android.home.model.Rooms
import kotlinx.coroutines.NonDisposableHandle.parent

class RoomAdapter(
    private val onClick: (position: Int) -> Unit
) : RecyclerView.Adapter<RoomHolderPage>() {

    private val itemList = ArrayList<Rooms>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_room, parent, false)
        return RoomHolderPage(view, onClick)
    }

    override fun onBindViewHolder(holder: RoomHolderPage, position: Int) {
        val viewHolder: RoomHolderPage = holder
        viewHolder.onBind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun getItem(position: Int): Rooms {
        return itemList[position]
    }


    fun addItem(item: Rooms) {
        itemList.add(item)
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}
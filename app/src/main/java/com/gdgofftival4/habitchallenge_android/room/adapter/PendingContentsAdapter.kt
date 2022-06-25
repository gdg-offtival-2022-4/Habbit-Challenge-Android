package com.gdgofftival4.habitchallenge_android.room.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdgofftival4.habitchallenge_android.R
import com.gdgofftival4.habitchallenge_android.room.model.RecordResponse

class PendingContentsAdapter(
    private val onItemClick: (postId: String) -> Unit
) : RecyclerView.Adapter<PendingContentsHolderPage>() {

    private val itemList = ArrayList<RecordResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingContentsHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_pending_contents, parent, false)
        return PendingContentsHolderPage(view, onItemClick)
    }

    override fun onBindViewHolder(holder: PendingContentsHolderPage, position: Int) {
        val viewHolder: PendingContentsHolderPage = holder
        viewHolder.onBind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun getItem(position: Int): RecordResponse {
        return itemList[position]
    }


    fun addAll(items: List<RecordResponse>) {
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}
package com.gdgofftival4.habitchallenge_android.addroom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdgofftival4.habitchallenge_android.R
import com.gdgofftival4.habitchallenge_android.addroom.model.CategoryUiModel

class CategoryAdapter(
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<CategoryHolderPage>() {

    private val itemList = ArrayList<CategoryUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        return CategoryHolderPage(view, onItemClick)
    }

    override fun onBindViewHolder(holder: CategoryHolderPage, position: Int) {
        val viewHolder: CategoryHolderPage = holder
        viewHolder.onBind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun getItem(position: Int): CategoryUiModel {
        return itemList[position]
    }

    fun addAll(items: List<CategoryUiModel>) {
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}
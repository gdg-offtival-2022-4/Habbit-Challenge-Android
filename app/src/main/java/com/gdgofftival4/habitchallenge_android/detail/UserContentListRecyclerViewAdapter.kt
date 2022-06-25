package com.gdgofftival4.habitchallenge_android.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.gdgofftival4.habitchallenge_android.R
import com.gdgofftival4.habitchallenge_android.databinding.ItemUserContentBinding
import com.gdgofftival4.habitchallenge_android.databinding.ItemUserContentDateBinding
import com.gdgofftival4.habitchallenge_android.extension.setImageUri

class UserContentListRecyclerViewAdapter(
    private val itemWidth: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<UserContentItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_DATE) {
            DetailDateViewHolder(parent)
        } else {
            DetailContentViewHolder(parent, itemWidth)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items.getOrNull(position)) {
            is UserContentItem.UserContentDateUiModel -> {
                (holder as? DetailDateViewHolder)?.onBind(item)
            }
            is UserContentItem.UserContentContentUiModel -> {
                (holder as? DetailContentViewHolder)?.onBind(item)
            }
            else -> {
                // Nothing to do
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items.getOrNull(position) ?: -1) {
            is UserContentItem.UserContentDateUiModel -> {
                VIEW_TYPE_DATE
            }
            is UserContentItem.UserContentContentUiModel -> {
                VIEW_TYPE_CONTENT
            }
            else -> {
                -1
            }
        }
    }

    fun replaceAll(items: List<UserContentItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class DetailDateViewHolder(
        parent: ViewGroup,
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_user_content_date, parent, false)
    ) {
        private val binding = ItemUserContentDateBinding.bind(itemView)

        fun onBind(date: UserContentItem.UserContentDateUiModel) {
            binding.root.text = date.date
        }
    }

    class DetailContentViewHolder(
        parent: ViewGroup,
        itemWidth: Int,
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_user_content, parent, false)
    ) {
        private val binding = ItemUserContentBinding.bind(itemView)

        init {
            with(binding.root.layoutParams) {
                width = itemWidth
                height = itemWidth
            }
        }

        fun onBind(content: UserContentItem.UserContentContentUiModel) {
            binding.root.setOnClickListener {
                content.onContentClick.invoke()
            }
            binding.imageDetailContent.setImageUri(content.imageUri)
            when (content.state) {
                UserContentState.ACCEPT -> {
                    binding.imageDetailContentStatus.isVisible = false
                }
                UserContentState.REJECT -> {
                    with(binding.imageDetailContentStatus) {
                        isVisible = true
                        setImageResource(R.drawable.ic_reject_badge)
                    }
                }
                UserContentState.PENDING -> {
                    with(binding.imageDetailContentStatus) {
                        isVisible = true
                        setImageResource(R.drawable.ic_pending_badge)
                    }
                }
            }
        }
    }

    companion object {
        const val VIEW_TYPE_DATE = 0
        const val VIEW_TYPE_CONTENT = 1
    }
}
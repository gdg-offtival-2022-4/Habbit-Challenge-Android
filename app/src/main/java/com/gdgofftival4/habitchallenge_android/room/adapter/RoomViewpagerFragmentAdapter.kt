package com.gdgofftival4.habitchallenge_android.room.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gdgofftival4.habitchallenge_android.room.ItemFragment
import com.gdgofftival4.habitchallenge_android.room.RankFragment

class RoomViewpagerFragmentAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    var fragmentList = mutableListOf(RankFragment(), ItemFragment())

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemId(position: Int): Long {
        // generate new id
        return fragmentList[position].hashCode().toLong()
    }

    fun setFragment(position: Int, refresh: Fragment){
        fragmentList[position] = refresh
    }

    override fun containsItem(itemId: Long): Boolean {
        // false if item is changed
        return fragmentList.find { it.hashCode().toLong() == itemId } != null
    }
}

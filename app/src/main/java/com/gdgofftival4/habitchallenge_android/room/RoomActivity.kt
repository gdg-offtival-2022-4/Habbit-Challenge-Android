package com.gdgofftival4.habitchallenge_android.room

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.databinding.ActivityRoomBinding
import com.gdgofftival4.habitchallenge_android.room.adapter.RoomViewpagerFragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class RoomActivity : BaseBindingActivity<ActivityRoomBinding>(ActivityRoomBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewPager: ViewPager2 = binding.viewPager
        val tabLayout: TabLayout = binding.tabLayout

        val viewpagerFragmentAdapter = RoomViewpagerFragmentAdapter(this)
        viewPager.adapter = viewpagerFragmentAdapter

        val tabTitles = listOf("Rank", "Item")
        // 2. TabLayout과 ViewPager2를 연결하고, TabItem의 메뉴명을 설정한다.
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}
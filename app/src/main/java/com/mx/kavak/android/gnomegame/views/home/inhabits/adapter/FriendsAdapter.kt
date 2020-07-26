package com.mx.kavak.android.gnomegame.views.home.inhabits.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mx.kavak.android.domain.models.Inhabitant
import com.mx.kavak.android.gnomegame.views.home.inhabits.detail.FriendsFragment

@SuppressLint("WrongConstant")
class FriendsAdapter(fm: FragmentManager,
                     private val friends: List<Inhabitant>):
    FragmentPagerAdapter(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {

        if (position == count - 1) {
            return if (friends.size % 2 == 0)
                FriendsFragment(friends[position * 2], friends[(position * 2) + 1])
            else {
                FriendsFragment(friends[position * 2], null)
            }
        }

       return FriendsFragment(friends[position * 2], friends[(position * 2) + 1])
    }

        override fun getCount(): Int =
            if (friends.size % 2 == 0) friends.size / 2
            else (friends.size + 1) / 2



}
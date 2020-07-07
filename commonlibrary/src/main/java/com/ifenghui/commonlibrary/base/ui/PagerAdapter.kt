package com.ifenghui.commonlibrary.base.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

@Suppress("DEPRECATION")
class PagerAdapter (fm: FragmentManager, private val fragment_list: List<Fragment>?) : FragmentPagerAdapter(fm) {
    private var titleList: List<String>? = null

    fun setTitleList(titleList: List<String>?) {
        this.titleList = titleList
    }

    override fun getItem(arg0: Int): Fragment {
        // TODO Auto-generated method stub
        return fragment_list!![arg0]
    }

    override fun getCount(): Int {
        // TODO Auto-generated method stub
        return fragment_list?.size ?: 0
    }


    override fun getPageTitle(position: Int): CharSequence? {
        try {
            return if (titleList != null && (titleList?.size?:0)>0) {
                titleList!![position]
            } else ""
        } catch (e: Exception) {
        }
        return super.getPageTitle(position)
    }
}
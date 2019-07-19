package com.cheng.httpproject.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import com.cheng.httpproject.R
import com.cheng.httpproject.ui.activity.StoreListActivity
import com.cheng.httpproject.ui.fragment.PlexureStoreListFragment

class PlexurePagerAdapter(private val storeListActivity: StoreListActivity, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    val PAGE_NUM = 2
    var fragments: Array<PlexureStoreListFragment?> = arrayOfNulls(PAGE_NUM)

    val TAB_TITLES = arrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
    )

    override fun getItem(position: Int): Fragment {
        val fragment = PlexureStoreListFragment()
        fragments[position] = fragment

        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)

        fragments[position] = null
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return storeListActivity.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return PAGE_NUM
    }

    fun getFragment(position: Int): PlexureStoreListFragment? {
        return fragments[position]
    }

}
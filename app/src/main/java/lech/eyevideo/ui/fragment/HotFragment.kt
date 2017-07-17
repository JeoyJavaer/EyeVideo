package lech.eyevideo.ui.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_hot.*
import lech.eyevideo.R
import lech.eyevideo.ui.adapter.HotFragmentAdapter
import lech.library.base.BaseFragment

/**
 * Created by Android_61 on 2017/7/13.
 * Description
 * Others
 */
class HotFragment : BaseFragment() {
    val titles = arrayListOf("周排行", "月排行", "总排行")
    val strategies = arrayListOf("weekly","monthly","historical")

    override fun getLayoutView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hot, container, false)
    }

    override fun init() {
        val fragments = arrayListOf<RankFragment>()

        val fragmentWeek = RankFragment()
        val bundleWeek = Bundle()
        bundleWeek.putString("strategy", strategies[0])
        fragmentWeek.arguments=bundleWeek
        fragments.add(fragmentWeek)


        val fragmentMonth = RankFragment()
        val bundleMonth = Bundle()
        bundleMonth.putString("strategy", strategies[1])
        fragmentMonth.arguments=bundleMonth
        fragments.add(fragmentMonth)


        val fragmentTotal = RankFragment()
        val bundleTotal = Bundle()
        bundleTotal.putString("strategy", strategies[2])
        fragmentTotal.arguments=bundleTotal
        fragments.add(fragmentTotal)

        val adapter = HotFragmentAdapter(titles, fragments, fragmentManager!!)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit=2

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.setupWithViewPager(viewPager)

    }
}
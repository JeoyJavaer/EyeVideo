package lech.eyevideo.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import lech.eyevideo.ui.fragment.RankFragment

/**
 * Created by Android_61 on 2017/7/17.
 * Description
 * Others
 */
class HotFragmentAdapter(titles:List<String>, fragments:List<RankFragment>, fm:FragmentManager) :FragmentStatePagerAdapter(fm){

    var mTitles:List<String> ?=null
    var mFragments :List<Fragment> ?=null
    init {
        mTitles=titles
        mFragments=fragments
    }

    override fun getItem(position: Int): Fragment {
       return mFragments?.get(position)!!
    }

    override fun getCount()=if (mFragments!=null) mFragments!!.size else 0

    override fun getPageTitle(position: Int): CharSequence {
        return mTitles!![position]
    }
}
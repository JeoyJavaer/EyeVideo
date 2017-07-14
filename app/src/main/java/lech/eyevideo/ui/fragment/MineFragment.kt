package lech.eyevideo.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lech.eyevideo.R
import lech.library.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_mine.*
import lech.eyevideo.ui.activity.AdviceActivity
import lech.eyevideo.ui.activity.CacheActivity
import lech.eyevideo.ui.activity.WatchHistoryActivity

/**
 * Created by Android_61 on 2017/7/13.
 * Description
 * Others
 */
class MineFragment : BaseFragment() {

    override fun getLayoutView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_mine, container, false)


    }

    override fun init() {
        tvCache.setOnClickListener {
            val startIntent = Intent(context, CacheActivity::class.java)
            startIntent.putExtra("title","我的缓存")
            startActivity(startIntent)
        }

        tvWatch.setOnClickListener {
            val startIntent = Intent(context, WatchHistoryActivity::class.java)
            startIntent.putExtra("title","观看记录")
            startActivity(startIntent)
        }

        tvAdvise.setOnClickListener {
            val startIntent = Intent(context, AdviceActivity::class.java)
            startIntent.putExtra("title","意见反馈")
            startActivity(startIntent)
        }

    }
}
package lech.eyevideo.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_dicovery.*
import lech.eyevideo.R
import lech.eyevideo.mvp.contract.DiscoveryContact
import lech.eyevideo.mvp.model.domain.DiscoveryBean
import lech.eyevideo.mvp.presenter.DiscoveryPresenterImpl
import lech.eyevideo.ui.activity.DiscoveryDetailActivity
import lech.eyevideo.ui.adapter.DiscoveryAdapter
import lech.library.base.BaseFragment

/**
 * Created by Android_61 on 2017/7/13.
 * Description
 * Others
 */
class DiscoveryFragment : BaseFragment(), DiscoveryContact.View {

    var mPresenter: DiscoveryPresenterImpl? = null
    var mAdapter: DiscoveryAdapter? = null
    var mListData: MutableList<DiscoveryBean> = arrayListOf()

    override fun setData(beans: MutableList<DiscoveryBean>) {

        mListData.addAll(beans)
        mAdapter?.notifyDataSetChanged()
    }

    override fun getLayoutView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recyclerview, container, false)
    }

    override fun init() {
        mPresenter = DiscoveryPresenterImpl(context, this)
        mPresenter?.start()

        mAdapter = DiscoveryAdapter(R.layout.item_discovery, mListData)
        recycleView.layoutManager = GridLayoutManager(context, 2)
        recycleView.adapter = mAdapter!!
        mAdapter!!.setOnItemClickListener { _, _, position ->

            val intent: Intent = Intent(context, DiscoveryDetailActivity::class.java)
            val discoveryBean = mListData[position]
            intent.putExtra("name", discoveryBean.name)
            startActivity(intent)
        }
    }

}
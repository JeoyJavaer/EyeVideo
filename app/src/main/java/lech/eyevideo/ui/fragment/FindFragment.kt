package lech.eyevideo.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_find.*
import lech.eyevideo.R
import lech.eyevideo.mvp.contract.FindContact
import lech.eyevideo.mvp.model.domain.FindBean
import lech.eyevideo.mvp.presenter.FindPresenterImpl
import lech.eyevideo.ui.activity.FindDetailActivity
import lech.eyevideo.ui.adapter.FindAdapter
import lech.library.base.BaseFragment

/**
 * Created by Android_61 on 2017/7/13.
 * Description
 * Others
 */
class FindFragment : BaseFragment(), FindContact.View {

    var mPresenter: FindPresenterImpl? = null
    var mAdapter: FindAdapter? = null
    var mListData: MutableList<FindBean> = arrayListOf()

    override fun setData(beans: MutableList<FindBean>) {

        mListData.addAll(beans)
        mAdapter?.notifyDataSetChanged()
    }

    override fun getLayoutView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recyclerview, container, false)
    }

    override fun init() {
        mPresenter = FindPresenterImpl(context, this)
        mPresenter?.start()

        mAdapter = FindAdapter(R.layout.item_find, mListData)
        recycleView.layoutManager = GridLayoutManager(context, 2)
        recycleView.adapter = mAdapter!!
        mAdapter!!.setOnItemClickListener { _, _, position ->

            val intent: Intent = Intent(context, FindDetailActivity::class.java)
            val discoveryBean = mListData[position]
            intent.putExtra("name", discoveryBean.name)
            intent.putExtra("title", discoveryBean.name)
            startActivity(intent)
        }
    }

}
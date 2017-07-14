package lech.eyevideo.ui.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lech.eyevideo.R
import lech.eyevideo.mvp.contract.HomeContract
import lech.eyevideo.mvp.model.domain.HomeBean
import lech.eyevideo.mvp.model.domain.ItemListBean
import lech.eyevideo.mvp.presenter.HomePresenterImpl
import lech.library.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import lech.eyevideo.ui.adapter.HomeAdapter
import java.util.regex.Pattern

/**
 * Created by Android_61 on 2017/7/13.
 * Description
 * Others
 */
class HomeFragment :BaseFragment(),HomeContract.View, SwipeRefreshLayout.OnRefreshListener {


    var mIsRefresh:Boolean=false
    var mPresenter:HomePresenterImpl?=null
    var mList= arrayListOf<ItemListBean>()
    var mAdapter: HomeAdapter?=null
    var data:String?=null

    override fun showData(bean: HomeBean) {
        val regex="[^09]"
        val p = Pattern.compile(regex)
        val matcher = p.matcher(bean.nextPageUrl)
        data = matcher.replaceAll("").subSequence(1, matcher.replaceAll("").length - 1).toString()
        if (mIsRefresh) {
            mIsRefresh=false
            swipeRefreshLayout.isRefreshing=false
            if (mList.size > 0) {
                mList.clear()
            }
        }

        bean.issueList!!
                .flatMap { it.itemList!! }
                .filter { it.type.equals("video") }
                .forEach { mList.add(it) }
        mAdapter?.notifyDataSetChanged()

    }

    override fun getLayoutView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun init() {
        mPresenter= HomePresenterImpl(context,this)
        mPresenter?.start()
        recycleView.layoutManager=LinearLayoutManager(context)
        mAdapter= HomeAdapter(R.layout.item_home,mList)
        recycleView.adapter=mAdapter
        swipeRefreshLayout.setOnRefreshListener(this)
        recycleView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager :LinearLayoutManager= recyclerView?.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPosition == mList.size) {
                    if (data != null) {
                        mPresenter?.moreData(data!!)
                    }
                }
            }
        })
    }

    override fun onRefresh() {
        if (!mIsRefresh) {
            mIsRefresh=true
            mPresenter?.start()
        }
    }

}
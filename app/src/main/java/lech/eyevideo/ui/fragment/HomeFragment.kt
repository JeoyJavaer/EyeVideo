package lech.eyevideo.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
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
import lech.eyevideo.mvp.model.domain.VideoBean
import lech.eyevideo.ui.activity.VideoDetailActivity
import lech.eyevideo.ui.adapter.HomeAdapter
import lech.library.base.adapter.QuickAdapter
import lech.library.delegate.Preference
import java.util.regex.Pattern

/**
 * Created by Android_61 on 2017/7/13.
 * Description
 * Others
 */
class HomeFragment :BaseFragment(),HomeContract.View, SwipeRefreshLayout.OnRefreshListener, QuickAdapter.OnItemClickListener {

    override fun onItemClick(adapter: QuickAdapter<*, *>?, view: View?, position: Int) {

        val bean = mList[position]
        //跳转视频详情页
        val intent : Intent = Intent(context, VideoDetailActivity::class.java)
        val photo = bean.data?.cover?.feed
        val category = bean.data?.category
        val desc = bean.data?.description
        val duration = bean.data?.duration
        val playUrl = bean.data?.playUrl
        val blurred = bean.data?.cover?.blurred
        val collect = bean.data?.consumption?.collectionCount
        val share = bean.data?.consumption?.shareCount
        val reply = bean.data?.consumption?.replyCount
        val time = System.currentTimeMillis()
        val videoBean  = VideoBean(photo,bean.data!!.title!!,desc!!,duration,playUrl!!,category!!,blurred!!,collect ,share ,reply,time)
        //TODO  完成注释部分
//        var url = Preference().getString(playUrl)
//        if(url.equals("")){
//            var count = SPUtils.getInstance(context!!,"beans").getInt("count")
//            if(count!=-1){
//                count = count.inc()
//            }else{
//                count = 1
//            }
//            SPUtils.getInstance(context!!,"beans").put("count",count)
//            SPUtils.getInstance(context!!,"beans").put(playUrl!!,playUrl)
//            ObjectSaveUtils.saveObject(context!!,"bean$count",videoBean)
//        }
        intent.putExtra("data",videoBean as Parcelable)
        context.startActivity(intent)
    }


    var mIsRefresh:Boolean=false
    var mPresenter:HomePresenterImpl?=null
    var mList= arrayListOf<ItemListBean>()
    var mAdapter: HomeAdapter?=null
    var data:String?=null

    override fun showData(bean: HomeBean) {
        val regex="[^0-9]"
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

        bean.issueList
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

        mAdapter!!.setOnItemClickListener(this)

        recycleView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager :LinearLayoutManager= recyclerView?.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPosition == mList.size-1) {
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
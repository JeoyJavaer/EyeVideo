package lech.eyevideo.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import lech.eyevideo.R
import lech.eyevideo.mvp.contract.HotContract
import lech.eyevideo.mvp.model.domain.HotBean
import lech.eyevideo.mvp.presenter.HotPresenterImpl
import lech.eyevideo.ui.adapter.RankingAdapter
import lech.library.base.BaseFragment
import kotlinx.android.synthetic.main.recyclerview.view.*
import lech.eyevideo.mvp.model.domain.VideoBean
import lech.eyevideo.ui.activity.VideoDetailActivity

/**
 * Created by Android_61 on 2017/7/14.
 * Description
 * Others
 */
class RankFragment :BaseFragment(),HotContract.View{
    var mPresenter:HotPresenterImpl ?=null
    var mAdapter : RankingAdapter ?=null
    var mDataList:ArrayList<HotBean.ItemListBean.DataBean> = arrayListOf()


    override fun showData(bean: HotBean) {
        if (mDataList.size>0) mDataList.clear()

        bean.itemList?.forEach {
           it.data.let { it1 ->
                mDataList.add(it1!!)
           }
        }

        mAdapter?.notifyDataSetChanged()
    }

    lateinit var category:String

    override fun getLayoutView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.recyclerview,container,false)
    }

    override fun init() {
        category= arguments.getString("strategy")
        mPresenter= HotPresenterImpl(context,this)
        mAdapter= RankingAdapter(R.layout.item_ranking,mDataList)
        mAdapter?.setOnItemClickListener { _, _, position ->
            val intent: Intent = Intent(context, VideoDetailActivity::class.java)
            val dataBean = mDataList[position]

            val desc = dataBean.description
            val playUrl = dataBean.playUrl
            val blurred = dataBean.cover?.blurred
            val collect = dataBean.consumption?.collectionCount
            val share = dataBean.consumption?.shareCount
            val reply = dataBean.consumption?.replyCount
            val time = System.currentTimeMillis()
            val photoUrl = dataBean.cover?.feed.toString()
            val category = dataBean.category
            val title = dataBean.title
            val duration = dataBean.duration
            val videoBean = VideoBean(photoUrl, title!!, desc!!, duration, playUrl!!, category!!, blurred!!, collect, share, reply, time)
//            val url = SPUtils.getInstance(context!!,"beans").getString(playUrl!!)
//            if(url.equals("")){
//                var count = SPUtils.getInstance(context!!,"beans").getInt("count")
//                if(count!=-1){
//                    count = count.inc()
//                }else{
//                    count = 1
//                }
//                SPUtils.getInstance(context!!,"beans").put("count",count)
//                SPUtils.getInstance(context!!,"beans").put(playUrl!!,playUrl)
//                ObjectSaveUtils.saveObject(context!!,"bean$count",videoBean)
//            }
            intent.putExtra("data", videoBean as Parcelable)
            startActivity(intent)
        }

        recycleView.layoutManager=LinearLayoutManager(context)
        recycleView.adapter=mAdapter

        mPresenter!!.requestData(category)


    }
}
package lech.eyevideo.ui.activity

import android.content.Intent
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_find_detail.*
import lech.eyevideo.R
import lech.eyevideo.mvp.contract.FindDetailContact
import lech.eyevideo.mvp.model.domain.HotBean
import lech.eyevideo.mvp.model.domain.VideoBean
import lech.eyevideo.mvp.presenter.FindDetailPresenterImpl
import lech.eyevideo.ui.adapter.RankingAdapter
import java.util.regex.Pattern

class FindDetailActivity : BaseTitleActivity(), FindDetailContact.View {
    var mPresenter: FindDetailPresenterImpl? = null
    var mAdapter: RankingAdapter? = null
    var mListData: MutableList<HotBean.ItemListBean.DataBean> = arrayListOf()
    var mIsRefreshing = false
    var mStart = 10
    lateinit var name: String
    lateinit var data: String

    override fun setData(bean: HotBean) {
        val regEx = "[^0-9]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(bean.nextPageUrl as CharSequence?)
        data = m.replaceAll("").subSequence(1, m.replaceAll("").length - 1).toString()
        if (mIsRefreshing) {
            mIsRefreshing = false
            swipeRefreshLayout.isRefreshing = false

            if (mListData.size > 0) {
                mListData.clear()
            }
        }

        bean.itemList?.forEach {
            it.data?.let { it1 -> mListData.add(it1) }

        }
        mAdapter?.notifyDataSetChanged()


    }


    override fun getLayoutID(): Int {
        return R.layout.activity_find_detail
    }


    override fun init() {
        name = intent.getStringExtra("name")

        recycleView.layoutManager = LinearLayoutManager(this)
        mAdapter = RankingAdapter(R.layout.item_ranking, mListData)
        mAdapter?.setOnItemClickListener { _, _, position ->
            val intent:Intent=Intent(this,VideoDetailActivity::class.java)
            val dataBean = mListData[position]

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
            val videoBean  = VideoBean(photoUrl,title!!,desc!!,duration,playUrl!!,category!!,blurred!!,collect ,share ,reply,time)
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
            intent.putExtra("data",videoBean as Parcelable)
            startActivity(intent)

        }


        recycleView.adapter = mAdapter!!
        mPresenter = FindDetailPresenterImpl(this, this)
        mPresenter?.requestData(name, "date")

        recycleView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = recyclerView?.layoutManager as LinearLayoutManager
                val position = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && position == mListData.size - 1) {
                    if (data != null) {
                        mPresenter?.requestMoreData(mStart,name,"date")
                        mStart=mStart.plus(10)
                    }
                }


            }
        })


        swipeRefreshLayout.setOnRefreshListener {
            if (!mIsRefreshing) {
                mIsRefreshing=true
                mPresenter?.requestData(name,"date")
            }
        }

    }


}

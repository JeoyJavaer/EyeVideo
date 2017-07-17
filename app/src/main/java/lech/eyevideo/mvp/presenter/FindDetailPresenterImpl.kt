package lech.eyevideo.mvp.presenter

import android.content.Context
import lech.eyevideo.mvp.contract.FindDetailContact
import lech.eyevideo.mvp.model.FindDetailModel
import lech.eyevideo.mvp.model.domain.HotBean
import lech.library.extension.applySchedulers

/**
 * Created by Android_61 on 2017/7/17.
 * Description
 * Others
 */
class FindDetailPresenterImpl(context: Context, view: FindDetailContact.View) : FindDetailContact.Presenter {


    var mContext: Context? = null
    var mView: FindDetailContact.View? = null
    val mModel: FindDetailModel by lazy {
        FindDetailModel()
    }


    init {
        mContext = context
        mView = view
    }


    override fun start() {}


    override fun requestData(category: String, strategy: String) {
        val loadData = mModel.loadData(mContext!!, category, strategy)
        loadData!!.applySchedulers().subscribe { bean: HotBean ->
            mView?.setData(bean)

        }
    }

    override fun requestMoreData(start: Int, category: String, strategy: String) {
        val observable = mContext.let { mModel.loadMoreData(mContext!!, start, category, strategy) }
        observable!!.applySchedulers().subscribe { bean: HotBean ->
            mView?.setData(bean)
        }
    }


}
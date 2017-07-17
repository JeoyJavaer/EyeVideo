package lech.eyevideo.mvp.presenter

import android.content.Context
import lech.eyevideo.mvp.contract.HotContract
import lech.eyevideo.mvp.model.HotModel
import lech.eyevideo.mvp.model.domain.HotBean
import lech.library.extension.applySchedulers

/**
 * Created by Android_61 on 2017/7/17.
 * Description
 * Others
 */
class HotPresenterImpl(context: Context, view: HotContract.View) : HotContract.Presenter {
    var mContext: Context? = null
    var mView: HotContract.View? = null
    val mModel: HotModel by lazy { HotModel() }

    init {
        mContext = context
        mView = view
    }

    override fun start() {

    }

    override fun requestData(category: String) {
        mModel.loadData(mContext!!, category)?.applySchedulers()?.subscribe { bean: HotBean ->
            mView?.showData(bean)
        }

    }
}
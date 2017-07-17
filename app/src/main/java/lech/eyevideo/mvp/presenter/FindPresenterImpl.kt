package lech.eyevideo.mvp.presenter

import android.content.Context
import io.reactivex.Observable
import lech.eyevideo.mvp.contract.FindContact
import lech.eyevideo.mvp.model.domain.FindBean
import lech.eyevideo.mvp.model.FindModel
import lech.library.extension.applySchedulers

/**
 * Created by Android_61 on 2017/7/17.
 * Description
 * Others
 */
class FindPresenterImpl(context: Context, view: FindContact.View) : FindContact.Presenter {

    var mContext: Context? = null
    var mView: FindContact.View? = null
    val mModel: FindModel by lazy {
        FindModel()
    }

    init {
        mContext = context
        mView = view
    }

    override fun start() {
        requestData()
    }

    override fun requestData() {
        val observable: Observable<MutableList<FindBean>> = mModel.loadData(mContext!!)
        observable.applySchedulers().subscribe { beas: MutableList<FindBean> ->
            mView?.setData(beas)
        }

    }
}
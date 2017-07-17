package lech.eyevideo.mvp.presenter

import android.content.Context
import io.reactivex.Observable
import lech.eyevideo.mvp.contract.DiscoveryContact
import lech.eyevideo.mvp.model.domain.DiscoveryBean
import lech.eyevideo.mvp.model.DiscoveryModel
import lech.library.extension.applySchedulers

/**
 * Created by Android_61 on 2017/7/17.
 * Description
 * Others
 */
class DiscoveryPresenterImpl(context: Context, view: DiscoveryContact.View) : DiscoveryContact.Presenter {

    var mContext: Context? = null
    var mView: DiscoveryContact.View? = null
    val mModel: DiscoveryModel by lazy {
        DiscoveryModel()
    }

    init {
        mContext = context
        mView = view
    }

    override fun start() {
        requestData()
    }

    override fun requestData() {
        val observable: Observable<MutableList<DiscoveryBean>> = mModel.loadData(mContext!!)
        observable.applySchedulers().subscribe { beas: MutableList<DiscoveryBean> ->
            mView?.setData(beas)
        }

    }
}
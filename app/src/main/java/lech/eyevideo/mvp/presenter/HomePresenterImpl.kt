package lech.eyevideo.mvp.presenter

import android.content.Context
import io.reactivex.Observable
import lech.eyevideo.mvp.contract.HomeContract
import lech.eyevideo.mvp.model.HomeModel
import lech.eyevideo.mvp.model.domain.HomeBean
import lech.library.extension.applySchedulers

/**
 * Created by Android_61 on 2017/7/13.
 * Description
 * Others
 */
class HomePresenterImpl(context: Context,view:HomeContract.View):HomeContract.Presenter {
    var mContext:Context?=null
    var mView:HomeContract.View ?=null
    val mModel:HomeModel by lazy { HomeModel() }

    init {
        mView=view
        mContext=context
    }
    override fun start() {
            requestData()
    }

    override fun requestData() {
        val  observable:Observable<HomeBean>?=mContext?.let { mModel.loadData(it,true,"0")  }
        observable?.applySchedulers()?.subscribe { homeBean:HomeBean ->
            mView?.showData(homeBean)
        }
    }

    fun  moreData(data:String){
        val  observable:Observable<HomeBean>?=mContext?.let { mModel.loadData(it,false,data)  }
        observable?.applySchedulers()?.subscribe { homeBean:HomeBean ->
            mView?.showData(homeBean)
        }
    }

}
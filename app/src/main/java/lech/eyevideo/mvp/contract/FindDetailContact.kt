package lech.eyevideo.mvp.contract

import lech.eyevideo.base.BasePresenter
import lech.eyevideo.base.BaseView
import lech.eyevideo.mvp.model.domain.HotBean

/**
 * Created by Android_61 on 2017/7/17.
 * Description
 * Others
 */
interface FindDetailContact {

    interface View : BaseView<Presenter> {
        fun setData(data: HotBean)
    }

    interface Presenter : BasePresenter {
        fun requestData(category: String, strategy: String)
        fun requestMoreData(start: Int, category: String, strategy: String)
    }
}
package lech.eyevideo.mvp.contract

import lech.eyevideo.base.BasePresenter
import lech.eyevideo.base.BaseView
import lech.eyevideo.mvp.model.domain.HomeBean

/**
 * Created by Android_61 on 2017/7/13.
 * Description
 * Others
 */
interface HomeContract {

    interface  View:BaseView<Presenter>{
        fun showData(bean: HomeBean)
    }

    interface Presenter:BasePresenter{
        fun requestData()
    }
}
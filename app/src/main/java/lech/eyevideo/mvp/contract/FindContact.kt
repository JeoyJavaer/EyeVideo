package lech.eyevideo.mvp.contract

import lech.eyevideo.base.BasePresenter
import lech.eyevideo.base.BaseView
import lech.eyevideo.mvp.model.domain.FindBean

/**
 * Created by Android_61 on 2017/7/17.
 * Description
 * Others
 */
interface FindContact {

    interface  View:BaseView<Presenter>{
        fun setData(beans:MutableList<FindBean>)
    }


    interface  Presenter:BasePresenter{
        fun  requestData()
    }

}
package lech.eyevideo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lech.eyevideo.R
import lech.library.base.BaseFragment

/**
 * Created by Android_61 on 2017/7/13.
 * Description
 * Others
 */
class  DiscoveryFragment : BaseFragment(){
    override fun getLayoutView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.recyclerview,container,false)
    }

    override fun initView() {

    }

}
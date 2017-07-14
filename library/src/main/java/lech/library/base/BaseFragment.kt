package lech.library.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Android_61 on 2017/7/13.
 * Description
 * Others
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getLayoutView(inflater,container,savedInstanceState)
    }

    abstract fun  getLayoutView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    abstract fun init()

    open fun myStartActivity(clazz: Class<in Activity>,bundle: Bundle= Bundle.EMPTY){
        val intent = Intent(context, clazz)
        activity.startActivity(intent,bundle)
    }
}
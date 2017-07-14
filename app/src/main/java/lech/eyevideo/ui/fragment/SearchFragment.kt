package lech.eyevideo.ui.fragment

import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lech.eyevideo.R

/**
 * Created by Android_61 on 2017/7/14.
 * Description
 * Others
 */
class SearchFragment : DialogFragment() {
    lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        rootView = inflater?.inflate(R.layout.fragment_search, container, false)!!
        return rootView

    }


}
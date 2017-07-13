package lech.library.manger

import android.support.v7.widget.Toolbar

/**
 * Created by Android_61 on 2017/7/13.
 * Description
 * Others
 */
interface ToolbarManager {
    val toolbar: Toolbar

    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }


}
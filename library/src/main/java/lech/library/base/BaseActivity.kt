package lech.library.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by Android_61 on 2017/7/13.
 * Description  所有类库中activity的基类
 * Others
 */
 abstract class BaseActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doBeforeSetContentView()
        setContentView(getLayoutID())
        doAfterSetContentView()
        init()

    }

    open fun doAfterSetContentView() {}


    abstract fun init()



    abstract fun getLayoutID(): Int

    open fun doBeforeSetContentView() {

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        doBeforeDestroy()
    }

    open fun doBeforeDestroy() {}
}
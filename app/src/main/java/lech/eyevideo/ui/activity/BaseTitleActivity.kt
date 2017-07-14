package lech.eyevideo.ui.activity

import android.view.MenuItem
import kotlinx.android.synthetic.main.title.*
import lech.library.base.BaseActivity

/**
 * Created by Android_61 on 2017/7/14.
 * Description
 * Others
 */

abstract class BaseTitleActivity : BaseActivity() {


    override fun doAfterSetContentView() {
        super.doAfterSetContentView()

        setSupportActionBar(toolbar)
        supportActionBar?.title = intent.getStringExtra("title")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }




}

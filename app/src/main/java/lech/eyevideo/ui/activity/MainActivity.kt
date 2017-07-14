package lech.eyevideo.ui.activity

import android.os.SystemClock
import android.view.KeyEvent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.title_main.*
import lech.eyevideo.R
import lech.eyevideo.ui.fragment.*
import lech.library.base.BaseActivity
import org.jetbrains.anko.toast
import java.util.*

class MainActivity : BaseActivity() {
    var mHomeFragment: HomeFragment? = null
    var mDiscoveryFragment: DiscoveryFragment? = null
    var mHotFragment: HotFragment? = null
    var mMineFragment: MineFragment? = null
    val dayString = getTodayString()
    val discoveryString = "Discovery"
    val hotString = "Ranking"
    val mineString = ""
    var shouldShowSettingIcon = false
    lateinit var mSearchFragment: SearchFragment
    var mExitTime:Long=0


    private fun getTodayString(): String {
        val list = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        val data: Date = Date()
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = data
        var index: Int = calendar.get(Calendar.DAY_OF_WEEK) - 1
        if (index < 0) {
            index = 0
        }
        return list[index]
    }


    override fun init() {
        initTitle()
        initFragment()


        rgMain.setOnCheckedChangeListener {
            group, checkedId ->
            when (checkedId) {
                R.id.rbHome -> show(0)
                R.id.rbDiscovery -> show(1)
                R.id.rbHot -> show(2)
                R.id.rbMine -> show(3)

            }
        }


        imageView.setOnClickListener {

            showSearch()

        }
    }

    private fun initTitle() {
        toolbar.title = ""
        imageView.setImageResource(R.drawable.ic_search)
        setSupportActionBar(toolbar)
    }

    private fun initFragment() {
        mHomeFragment = HomeFragment()
        mDiscoveryFragment = DiscoveryFragment()
        mHotFragment = HotFragment()
        mMineFragment = MineFragment()

        supportFragmentManager.beginTransaction()
                .add(R.id.flContent, mHomeFragment)
                .add(R.id.flContent, mDiscoveryFragment)
                .add(R.id.flContent, mHotFragment)
                .add(R.id.flContent, mMineFragment)
                .hide(mDiscoveryFragment)
                .hide(mHotFragment)
                .hide(mMineFragment)
                .show(mHomeFragment)
                .commitAllowingStateLoss()
        tvTitle.text = dayString
    }

    private fun show(index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
                .hide(mDiscoveryFragment)
                .hide(mHotFragment)
                .hide(mMineFragment)
                .hide(mHomeFragment)

        if (0 == index) {
            tvTitle.text = dayString
            transaction.show(mHomeFragment)
            imageView.setImageResource(R.drawable.ic_search)
            shouldShowSettingIcon = false
        } else if (1 == index) {
            tvTitle.text = discoveryString
            transaction.show(mDiscoveryFragment)
            imageView.setImageResource(R.drawable.ic_search)
            shouldShowSettingIcon = false
        } else if (2 == index) {
            tvTitle.text = hotString
            transaction.show(mHotFragment)
            imageView.setImageResource(R.drawable.ic_search)
            shouldShowSettingIcon = false
        } else if (3 == index) {
            tvTitle.text = mineString
            transaction.show(mMineFragment)
            imageView.setImageResource(R.drawable.ic_settings)
            shouldShowSettingIcon = true
        }

        transaction.commitAllowingStateLoss()
    }


    override fun getLayoutID(): Int = R.layout.activity_main


    private fun showSearch() {
        if (shouldShowSettingIcon) {
            toast("展示设置")
        } else {
            mSearchFragment = SearchFragment()
            mSearchFragment.show(fragmentManager, "SearchFragment")
            toast("展示搜索")
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) < 3000) {
                finish()
            } else {
                toast("再按一次退出应用")
                mExitTime=System.currentTimeMillis()
            }
            return true
        } else {
            return super.onKeyDown(keyCode, event)
        }


    }


}

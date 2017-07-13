package lech.eyevideo.ui.activity

import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.title_main.*
import lech.eyevideo.R
import lech.eyevideo.ui.fragment.DiscoveryFragment
import lech.eyevideo.ui.fragment.HomeFragment
import lech.eyevideo.ui.fragment.HotFragment
import lech.eyevideo.ui.fragment.MineFragment
import lech.library.base.BaseActivity
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {
    var mHomeFragment: HomeFragment? = null
    var mDiscoveryFragment: DiscoveryFragment? = null
    var mHotFragment: HotFragment? = null
    var mMineFragment: MineFragment? = null
    val dayString = getTodayString()
    val discoveryString = "Discovery"
    val hotString = "Ranking"
    val mineString = ""
    var shouldShowSettingIcon=true



    private fun getTodayString(): String {
        return "Tuesday"
    }


    override fun initData() {}

    override fun initEvent() {
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
    }

    private fun initTitle() {
        toolbar.title=""
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
        tvTitle.text=dayString
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
            shouldShowSettingIcon=false
        } else if (1 == index) {
            tvTitle.text = discoveryString
            transaction.show(mDiscoveryFragment)
            shouldShowSettingIcon=false
        } else if (2 == index) {
            tvTitle.text = hotString
            transaction.show(mHotFragment)
            shouldShowSettingIcon=false
        } else if (3 == index) {
            tvTitle.text = mineString
            transaction.show(mMineFragment)
            shouldShowSettingIcon=true
        }

        transaction.commitAllowingStateLoss()
    }


    override fun getLayoutID(): Int = R.layout.activity_main

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu_search) {
            showSearch()
            return  true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showSearch() {
        toast("展示搜索")
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (shouldShowSettingIcon) {
            menu?.findItem(R.id.menu_search)?.setVisible(false)
            menu?.findItem(R.id.menu_setting)?.setVisible(true)
        } else {
            menu?.findItem(R.id.menu_search)?.setVisible(true)
            menu?.findItem(R.id.menu_setting)?.setVisible(false)
        }

        return super.onPrepareOptionsMenu(menu)
    }


}

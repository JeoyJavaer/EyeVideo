package lech.eyevideo.ui.activity

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


        imageView.setOnClickListener {

                showSearch()

        }
    }

    private fun initTitle() {
        toolbar.title=""
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
            imageView.setImageResource(R.drawable.ic_search)
            shouldShowSettingIcon=false
        } else if (1 == index) {
            tvTitle.text = discoveryString
            transaction.show(mDiscoveryFragment)
            imageView.setImageResource(R.drawable.ic_search)
            shouldShowSettingIcon=false
        } else if (2 == index) {
            tvTitle.text = hotString
            transaction.show(mHotFragment)
            imageView.setImageResource(R.drawable.ic_search)
            shouldShowSettingIcon=false
        } else if (3 == index) {
            tvTitle.text = mineString
            transaction.show(mMineFragment)
            imageView.setImageResource(R.drawable.ic_settings)
            shouldShowSettingIcon=true
        }

        transaction.commitAllowingStateLoss()
    }


    override fun getLayoutID(): Int = R.layout.activity_main






    private fun showSearch() {
        if (shouldShowSettingIcon) {
            toast("展示设置")
        } else {

            toast("展示搜索")
        }
    }




}

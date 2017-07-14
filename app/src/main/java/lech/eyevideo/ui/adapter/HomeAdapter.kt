package lech.eyevideo.ui.adapter

import lech.eyevideo.mvp.model.dao.ResponseHomeList
import lech.eyevideo.mvp.model.domain.ItemListBean
import lech.library.base.adapter.BaseViewHolder
import lech.library.base.adapter.QuickAdapter

/**
 * Created by Android_61 on 2017/7/14.
 * Description
 * Others
 */

class HomeAdapter(layoutResId: Int, data: List<ItemListBean>) : QuickAdapter<ItemListBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: ItemListBean) {

    }
}

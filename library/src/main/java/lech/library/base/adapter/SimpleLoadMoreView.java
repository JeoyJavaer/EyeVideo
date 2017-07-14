package lech.library.base.adapter;


import lech.library.R;
import lech.library.ui.widget.LoadMoreView;

/**
 * Created by Android_61 on 2017/7/12.
 * Description
 * Others
 */

public class SimpleLoadMoreView extends LoadMoreView {

    @Override public int getLayoutId() {
        return R.layout.quick_view_load_more;
    }

    @Override protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
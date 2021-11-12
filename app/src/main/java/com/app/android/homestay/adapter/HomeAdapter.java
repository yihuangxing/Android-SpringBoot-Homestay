package com.app.android.homestay.adapter;

import com.app.android.homestay.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

/**
 * author : yi...
 * date   : 2021/11/12/0012  20:06
 * desc   :
 */
public class HomeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public HomeAdapter() {
        super(R.layout.home_item);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, String s) {

    }
}

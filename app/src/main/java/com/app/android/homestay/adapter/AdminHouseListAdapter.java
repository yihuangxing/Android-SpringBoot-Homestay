package com.app.android.homestay.adapter;

import androidx.annotation.NonNull;

import com.app.android.homestay.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

/**
 * author : yi.huangxing
 * date   : 2021/11/13 9:57 上午
 * desc   :
 */
public class AdminHouseListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public AdminHouseListAdapter() {
        super(R.layout.admin_house_item);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, String s) {

    }
}

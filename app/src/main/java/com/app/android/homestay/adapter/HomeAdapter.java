package com.app.android.homestay.adapter;

import android.graphics.Paint;
import android.widget.TextView;

import com.app.android.homestay.GlideEngine;
import com.app.android.homestay.R;
import com.app.android.homestay.bean.HouseInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

/**
 * author : yi...
 * date   : 2021/11/12/0012  20:06
 * desc   :
 */
public class HomeAdapter extends BaseQuickAdapter<HouseInfo, BaseViewHolder> {

    private TextView original_price;


    public HomeAdapter() {
        super(R.layout.home_item);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, HouseInfo houseInfo) {
        GlideEngine.createGlideEngine().loadImage(getContext().getApplicationContext(), houseInfo.getHouse_img(), baseViewHolder.getView(R.id.image));


        baseViewHolder.setText(R.id.title, houseInfo.getIntroduce());
        baseViewHolder.setText(R.id.discount_price, "￥"+houseInfo.getDiscount_price());
        original_price = baseViewHolder.getView(R.id.original_price);
        original_price.setText("原价"+houseInfo.getOriginal_price());
        original_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        original_price.getPaint().setAntiAlias(true);
        //
        baseViewHolder.setText(R.id.address, houseInfo.getAddress());


    }
}

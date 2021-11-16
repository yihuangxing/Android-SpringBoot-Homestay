package com.app.android.homestay.adapter;

import android.graphics.Paint;
import android.widget.TextView;

import com.app.android.homestay.GlideEngine;
import com.app.android.homestay.R;
import com.app.android.homestay.bean.OrderInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

public class UserOrderAdapter extends BaseQuickAdapter<OrderInfo, BaseViewHolder> {


    private TextView original_price;

    public UserOrderAdapter() {
        super(R.layout.order_item);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, OrderInfo orderInfo) {
        holder.setText(R.id.username, orderInfo.getUsername());
        holder.setText(R.id.create_time, "下单时间：" + orderInfo.getCreate_time());
        GlideEngine.createGlideEngine().loadImage(getContext(), orderInfo.getHouse_img(), holder.getView(R.id.image));


        holder.setText(R.id.title, orderInfo.getIntroduce());
        holder.setText(R.id.discount_price, "￥"+orderInfo.getDiscount_price());
        original_price = holder.getView(R.id.original_price);
        original_price.setText("原价"+orderInfo.getOriginal_price());
        original_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        original_price.getPaint().setAntiAlias(true);
        //
        holder.setText(R.id.address, orderInfo.getAddress());

    }
}

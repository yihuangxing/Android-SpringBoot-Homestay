package com.app.android.homestay.adapter;


import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import com.app.android.homestay.Config;
import com.app.android.homestay.GlideEngine;
import com.app.android.homestay.R;
import com.app.android.homestay.activity.UserFeedBackActivity;
import com.app.android.homestay.bean.OrderInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class PayListAdapter extends BaseQuickAdapter<OrderInfo, BaseViewHolder> {

    private TextView original_price;
    private CircleImageView profile_image;

    public PayListAdapter() {
        super(R.layout.pay_item);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, OrderInfo orderInfo) {
        holder.setText(R.id.username, orderInfo.getUsername());
        holder.setText(R.id.order_num, "订单号：" + orderInfo.getOrder_num());
        GlideEngine.createGlideEngine().loadImage(getContext(), orderInfo.getHouse_img(), holder.getView(R.id.image));

        if (Config.getUserInfo() != null && Config.getUserInfo().getAvatar() != null) {
            GlideEngine.createGlideEngine().loadImage(getContext(), Config.getUserInfo().getAvatar(), holder.getView(R.id.profile_image));
        }


        holder.setText(R.id.title, orderInfo.getIntroduce());
        holder.setText(R.id.discount_price, "￥" + orderInfo.getDiscount_price());
        original_price = holder.getView(R.id.original_price);
        original_price.setText("原价" + orderInfo.getOriginal_price());
        original_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        original_price.getPaint().setAntiAlias(true);
        //
        holder.setText(R.id.address, orderInfo.getAddress());
        holder.getView(R.id.more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserFeedBackActivity.class);
                intent.putExtra("order_num", orderInfo.getOrder_num());
                intent.putExtra("username", orderInfo.getUsername());
                getContext().startActivity(intent);
            }
        });

    }
}

package com.app.android.homestay.adapter;

import android.graphics.Paint;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.app.android.homestay.GlideEngine;
import com.app.android.homestay.R;
import com.app.android.homestay.bean.OrderInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

public class AdminOrderListAdapter extends BaseQuickAdapter<OrderInfo, BaseViewHolder> {
    private TextView original_price;
    private TextView order_status;

    public AdminOrderListAdapter() {
        super(R.layout.admin_order_item);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, OrderInfo orderInfo) {
        order_status = holder.getView(R.id.order_status);
        holder.setText(R.id.username, "用户：" + orderInfo.getUsername());
        GlideEngine.createGlideEngine().loadImage(getContext(), orderInfo.getHouse_img(), holder.getView(R.id.image));
        holder.setText(R.id.title, orderInfo.getIntroduce());
        holder.setText(R.id.discount_price, "￥" + orderInfo.getDiscount_price());
        original_price = holder.getView(R.id.original_price);
        original_price.setText("原价" + orderInfo.getOriginal_price());
        original_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        original_price.getPaint().setAntiAlias(true);
        //
        holder.setText(R.id.address, orderInfo.getAddress());
        if (orderInfo.getPay_status() == 0) {
            order_status.setText("待付款");
            order_status.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.red));
            holder.setText(R.id.create_time, "下单时间：" + orderInfo.getCreate_time());
        } else {
            order_status.setText("支付成功");
            order_status.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.teal_200));
            holder.setText(R.id.create_time, "订单号：" + orderInfo.getOrder_num());
        }

    }
}

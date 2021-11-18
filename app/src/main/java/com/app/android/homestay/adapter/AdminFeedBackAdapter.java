package com.app.android.homestay.adapter;

import com.app.android.homestay.R;
import com.app.android.homestay.bean.FeedBackInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

public class AdminFeedBackAdapter extends BaseQuickAdapter<FeedBackInfo, BaseViewHolder> {
    public AdminFeedBackAdapter() {
        super(R.layout.admin_feed_item);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, FeedBackInfo feedBackInfo) {

        holder.setText(R.id.order_num, "订单号：" + feedBackInfo.getOrder_num());
        holder.setText(R.id.username, "反馈用户：" + feedBackInfo.getUsername());
        holder.setText(R.id.content, feedBackInfo.getFeed_content());
        holder.setText(R.id.create_time, "反馈时间：" + feedBackInfo.getCreate_time());

    }
}

package com.app.android.homestay.adapter;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.app.android.homestay.GlideEngine;
import com.app.android.homestay.R;
import com.app.android.homestay.bean.UserInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.othershe.cornerlabelview.CornerLabelView;

import de.hdodenhof.circleimageview.CircleImageView;


public class AdminUserListAdapter extends BaseQuickAdapter<UserInfo, BaseViewHolder> {
    private CircleImageView profile_image;
    private TextView username;
    private CornerLabelView cornerLabelView;

    public AdminUserListAdapter() {
        super(R.layout.admin_user_list_item);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, UserInfo userInfo) {
        profile_image = baseViewHolder.getView(R.id.profile_image);
        username = baseViewHolder.getView(R.id.username);
        cornerLabelView = baseViewHolder.getView(R.id.cornerLabelView);
        baseViewHolder.setText(R.id.email, "邮箱：" + userInfo.getEmail());
        baseViewHolder.setText(R.id.username, userInfo.getUsername());
        baseViewHolder.setText(R.id.mobile, "手机号：" + userInfo.getMobile());

        if (userInfo.getIdentity() == 1) {
            profile_image.setBorderColor(ContextCompat.getColor(getContext(), R.color.red));
            cornerLabelView.setBgColor(ContextCompat.getColor(getContext(), R.color.red));
            cornerLabelView.setText("管理员");
        } else {
            profile_image.setBorderColor(ContextCompat.getColor(getContext(), R.color.purple_500));
            cornerLabelView.setBgColor(ContextCompat.getColor(getContext(), R.color.purple_500));
            cornerLabelView.setText("注册用户");
        }

        if (userInfo.getAvatar() != null) {
            GlideEngine.createGlideEngine().loadImage(getContext(), userInfo.getAvatar(), profile_image);
        } else {
            profile_image.setImageResource(R.mipmap.logo);
        }

    }
}

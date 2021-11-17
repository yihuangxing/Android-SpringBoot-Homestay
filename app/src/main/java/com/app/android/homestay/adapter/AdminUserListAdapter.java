package com.app.android.homestay.adapter;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.app.android.homestay.R;
import com.app.android.homestay.bean.UserInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import de.hdodenhof.circleimageview.CircleImageView;


public class AdminUserListAdapter extends BaseQuickAdapter<UserInfo, BaseViewHolder> {
    private CircleImageView profile_image;

    public AdminUserListAdapter() {
        super(R.layout.admin_user_list_item);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, UserInfo userInfo) {
        profile_image = baseViewHolder.getView(R.id.profile_image);
        baseViewHolder.setText(R.id.email, "邮箱：" + userInfo.getEmail());
        baseViewHolder.setText(R.id.username, "用户名：" + userInfo.getUsername());

        if (userInfo.getIdentity()==1){
            profile_image.setBorderColor(ContextCompat.getColor(getContext(),R.color.red));
        }else {
            profile_image.setBorderColor(ContextCompat.getColor(getContext(),R.color.purple_500));
        }

    }
}

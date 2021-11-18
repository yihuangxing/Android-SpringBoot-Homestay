package com.app.android.homestay.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.allen.library.SuperTextView;
import com.app.android.homestay.Config;
import com.app.android.homestay.GlideEngine;
import com.app.android.homestay.R;
import com.app.android.homestay.activity.AdminFeedBookActivity;
import com.app.android.homestay.activity.UserEditInfoActivity;
import com.app.android.homestay.activity.UserPayOrderActivity;
import com.app.android.homestay.activity.UserUpdatePwdActivity;
import com.app.android.homestay.base.BaseFragment;
import com.app.android.homestay.bean.UserInfo;

import de.hdodenhof.circleimageview.CircleImageView;

public class CenterFragment extends BaseFragment {
    private TextView username;
    private SuperTextView email;
    private SuperTextView mobile;
    private CircleImageView profile_image;
    private TextView nickname;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_center;
    }

    @Override
    protected void initView() {
        username = mRootView.findViewById(R.id.username);
        email = mRootView.findViewById(R.id.email);
        mobile = mRootView.findViewById(R.id.mobile);
        profile_image = mRootView.findViewById(R.id.profile_image);
        nickname = mRootView.findViewById(R.id.nickname);

    }

    @Override
    protected void setListener() {

        mRootView.findViewById(R.id.feedback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Config.getUserInfo() != null) {
                    Intent intent = new Intent(getActivity(), AdminFeedBookActivity.class);
                    intent.putExtra("username", Config.getUserInfo().getUsername());
                    startActivity(intent);
                }


            }
        });

        mRootView.findViewById(R.id.paylist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UserPayOrderActivity.class));
            }
        });

        mRootView.findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), UserUpdatePwdActivity.class), 300);
            }
        });

        mRootView.findViewById(R.id.head).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), UserEditInfoActivity.class), 400);
            }
        });

        mRootView.findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("确定要退出登录吗？");
                builder.setMessage("退出登录将清空用户登录信息");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public void initData() {

        UserInfo userInfo = Config.getUserInfo();
        if (userInfo != null) {
            username.setText(userInfo.getUsername());
            email.setLeftString("邮箱：" + userInfo.getEmail());
            mobile.setLeftString("手机号：" + userInfo.getMobile());
            nickname.setText(userInfo.getNickname());

            if (TextUtils.isEmpty(userInfo.getAvatar())) {
                profile_image.setImageResource(R.mipmap.logo);
            } else {
                GlideEngine.createGlideEngine().loadImage(getActivity(), userInfo.getAvatar(), profile_image);
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 300) {
            getActivity().finish();
        } else if (requestCode == 400) {
            initData();
        }
    }
}
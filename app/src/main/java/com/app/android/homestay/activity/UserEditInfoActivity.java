package com.app.android.homestay.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.app.android.homestay.Config;
import com.app.android.homestay.GlideEngine;
import com.app.android.homestay.R;
import com.app.android.homestay.base.BaseActivity;
import com.app.android.homestay.bean.UserInfo;
import com.app.android.homestay.http.HttpStringCallback;
import com.app.android.homestay.utils.GsonUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.PostRequest;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserEditInfoActivity extends BaseActivity {
    private TextView username;
    private EditText mobile;
    private EditText email;
    private TextView nickname;
    private CircleImageView profile_image;
    private String compressPath;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_edit_info;
    }

    @Override
    protected void initView() {
        username = findViewById(R.id.username);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        profile_image = findViewById(R.id.profile_image);
        nickname = findViewById(R.id.nickname);

    }

    @Override
    protected void setListener() {

        findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String phone = mobile.getText().toString();
                String emailstr = email.getText().toString();
                String nickName = nickname.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    BaseToast("请输入手机号");
                } else if (TextUtils.isEmpty(emailstr)) {
                    BaseToast("请输入邮箱");
                } else if (TextUtils.isEmpty(emailstr)) {
                    BaseToast("请输入昵称");
                } else {
                    UserInfo userInfo = Config.getUserInfo();
                    if (userInfo != null) {
                        editUserInfo(userInfo.getUid(), phone, emailstr, nickName);
                    }

                }
            }
        });

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(UserEditInfoActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .imageEngine(GlideEngine.createGlideEngine())
                        .selectionMode(PictureConfig.SINGLE)
                        .isCompress(true)
                        .forResult(new OnResultCallbackListener<LocalMedia>() {
                            @Override
                            public void onResult(List<LocalMedia> result) {
                                if (result != null && result.size() > 0) {
                                    LocalMedia localMedia = result.get(0);
                                    compressPath = localMedia.getCompressPath();
                                    GlideEngine.createGlideEngine().loadImage(UserEditInfoActivity.this, compressPath, profile_image);
                                }


                            }

                            @Override
                            public void onCancel() {

                            }
                        });
            }
        });

    }

    private void editUserInfo(int uid, String mobile, String emailstr, String nickname) {

        PostRequest<String> post = OkGo.<String>post(Config.EDIT_USER_URL);
        post.params("uid", uid);
        post.params("mobile", mobile);
        post.params("email", emailstr);
        post.params("username", Config.getUserInfo().getUsername());
        post.params("nickname", nickname);

        if (!TextUtils.isEmpty(compressPath)) {
            post.params("file", new File(compressPath));
        }

        post.execute(new HttpStringCallback(this) {
            @Override
            protected void onSuccess(String msg, String response) {
                UserInfo userInfo = GsonUtils.parseJson(response, UserInfo.class);
                Config.setUserInfo(userInfo);
                BaseToast(msg);
                setResult(400);
                finish();
            }

            @Override
            protected void onError(String response) {
                BaseToast(response);
            }
        });

    }

    @Override
    protected void initData() {
        UserInfo userInfo = Config.getUserInfo();
        if (null != userInfo) {
            username.setText(userInfo.getUsername());
            mobile.setText(userInfo.getMobile());
            email.setText(userInfo.getEmail());
            nickname.setText(userInfo.getNickname());
            if (TextUtils.isEmpty(userInfo.getAvatar())) {
                profile_image.setImageResource(R.mipmap.logo);
            } else {
                GlideEngine.createGlideEngine().loadImage(this, userInfo.getAvatar(), profile_image);
            }
        }
    }
}
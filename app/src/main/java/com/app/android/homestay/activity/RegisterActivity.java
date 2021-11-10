package com.app.android.homestay.activity;

import android.widget.ImageView;

import com.app.android.homestay.R;
import com.app.android.homestay.base.BaseActivity;
import com.app.android.homestay.utils.CodeUtils;

public class RegisterActivity extends BaseActivity {
    private String title;
    private ImageView tv_code;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        tv_code = findViewById(R.id.tv_code);
        tv_code.setImageBitmap(CodeUtils.getInstance().createBitmap());

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        title = getIntent().getStringExtra("title");
        mToolbar.setTitle(title);

    }
}
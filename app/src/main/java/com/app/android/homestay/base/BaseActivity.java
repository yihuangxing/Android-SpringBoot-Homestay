package com.app.android.homestay.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.app.android.homestay.R;

/**
 * author : yi...
 * date   : 2021/11/10/0010  9:45
 * desc   :
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Activity mActivity;
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(getLayoutId());

        mToolbar = findViewById(R.id.toolbar);
        if (null != mToolbar) {
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        initView();

        setListener();

        initData();
    }


    protected abstract int getLayoutId();


    protected abstract void initView();


    protected abstract void setListener();


    protected abstract void initData();


    protected void BaseToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

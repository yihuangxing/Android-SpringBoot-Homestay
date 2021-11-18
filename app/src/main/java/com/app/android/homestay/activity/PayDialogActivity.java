package com.app.android.homestay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.android.homestay.Config;
import com.app.android.homestay.R;
import com.app.android.homestay.base.BaseActivity;
import com.app.android.homestay.http.HttpStringCallback;
import com.lzy.okgo.OkGo;

public class PayDialogActivity extends BaseActivity {
    private String discount_price;
    private TextView discountprice;
    private int uid;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_dialog;
    }

    @Override
    protected void initView() {
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.confirm_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pay();
            }
        });

        discountprice = findViewById(R.id.discount_price);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        discount_price = getIntent().getStringExtra("discount_price");
        uid = getIntent().getIntExtra("uid", 0);
        discountprice.setText("￥" + discount_price);

    }


    private void pay() {
        OkGo.<String>get(Config.PAY_URL)
                .params("uid", uid)
                .params("pay_status", 1)
                .execute(new HttpStringCallback(null) {
                    @Override
                    protected void onSuccess(String msg, String response) {
                        BaseToast(msg);
                        setResult(2000);
                        finish();
                    }

                    @Override
                    protected void onError(String response) {
                        BaseToast(response);
                    }
                });
    }
}
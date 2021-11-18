package com.app.android.homestay.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.app.android.homestay.Config;
import com.app.android.homestay.R;
import com.app.android.homestay.base.BaseActivity;
import com.app.android.homestay.http.HttpStringCallback;
import com.lzy.okgo.OkGo;

import org.w3c.dom.Text;

public class UserFeedBackActivity extends BaseActivity {
    private TextView order_num;
    private EditText content;
    private String orderNum;
    private String userName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_feed_back;
    }

    @Override
    protected void initView() {
        order_num = findViewById(R.id.order_num);
        content = findViewById(R.id.content);
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.confirm_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedContent = content.getText().toString().trim();
                if (TextUtils.isEmpty(feedContent)) {
                    BaseToast("请填写你的反馈意见");
                } else {
                    addFeed(feedContent);
                }
            }
        });

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        orderNum = getIntent().getStringExtra("order_num");
        userName = getIntent().getStringExtra("username");
        //
        order_num.setText("订单号：" + orderNum);

    }

    private void addFeed(String content) {
        OkGo.<String>post(Config.FEED_ADD_URL)
                .params("order_num", orderNum)
                .params("feed_content", content)
                .params("username", userName)
                .execute(new HttpStringCallback(this) {
                    @Override
                    protected void onSuccess(String msg, String response) {
                        BaseToast(msg);
                        finish();
                    }

                    @Override
                    protected void onError(String response) {
                        BaseToast(response);
                    }
                });
    }
}
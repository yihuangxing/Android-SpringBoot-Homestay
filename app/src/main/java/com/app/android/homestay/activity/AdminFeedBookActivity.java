package com.app.android.homestay.activity;

import androidx.recyclerview.widget.RecyclerView;

import com.app.android.homestay.Config;
import com.app.android.homestay.R;
import com.app.android.homestay.adapter.AdminFeedBackAdapter;
import com.app.android.homestay.base.BaseActivity;
import com.app.android.homestay.bean.FeedBackInfoBean;
import com.app.android.homestay.http.HttpStringCallback;
import com.app.android.homestay.utils.GsonUtils;
import com.lzy.okgo.OkGo;

/**
 * 用户反馈
 */
public class AdminFeedBookActivity extends BaseActivity {

    private AdminFeedBackAdapter mAdminFeedBackAdapter;

    private RecyclerView recyclerview;

    private String username;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_admin_feed_book;
    }

    @Override
    protected void initView() {
        recyclerview = findViewById(R.id.recyclerview);


        mAdminFeedBackAdapter = new AdminFeedBackAdapter();
        recyclerview.setAdapter(mAdminFeedBackAdapter);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        username = getIntent().getStringExtra("username");
        queryAll(username);
    }

    private void queryAll(String username) {
        OkGo.<String>post(Config.FEED_QUERY_URL)
                .params("username", username)
                .execute(new HttpStringCallback(this) {
                    @Override
                    protected void onSuccess(String msg, String response) {
                        FeedBackInfoBean feedBackInfoBean = GsonUtils.parseJson(response, FeedBackInfoBean.class);
                        mAdminFeedBackAdapter.setNewInstance(feedBackInfoBean.getList());

                    }

                    @Override
                    protected void onError(String response) {
                        BaseToast(response);
                    }
                });

    }
}
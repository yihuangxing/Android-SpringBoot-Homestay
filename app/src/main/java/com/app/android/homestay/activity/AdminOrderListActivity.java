package com.app.android.homestay.activity;

import androidx.recyclerview.widget.RecyclerView;

import com.app.android.homestay.Config;
import com.app.android.homestay.R;
import com.app.android.homestay.adapter.AdminOrderListAdapter;
import com.app.android.homestay.base.BaseActivity;
import com.app.android.homestay.bean.OderInfoBean;
import com.app.android.homestay.http.HttpStringCallback;
import com.app.android.homestay.utils.GsonUtils;
import com.lzy.okgo.OkGo;

public class AdminOrderListActivity extends BaseActivity {

    private RecyclerView recyclerview;
    private AdminOrderListAdapter mListAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_list;
    }

    @Override
    protected void initView() {
        recyclerview = findViewById(R.id.recyclerview);

        mListAdapter = new AdminOrderListAdapter();

        recyclerview.setAdapter(mListAdapter);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        queryOrderAll();
    }

    private void queryOrderAll() {
        OkGo.<String>post(Config.QUERY_ORDER_URL)
                .execute(new HttpStringCallback(this) {
                    @Override
                    protected void onSuccess(String msg, String response) {

                        OderInfoBean infoBean = GsonUtils.parseJson(response, OderInfoBean.class);
                        mListAdapter.setNewInstance(infoBean.getList());

                    }

                    @Override
                    protected void onError(String response) {
                        BaseToast(response);

                    }
                });

    }
}
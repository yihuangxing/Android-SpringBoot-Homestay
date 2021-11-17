package com.app.android.homestay.activity;

import androidx.recyclerview.widget.RecyclerView;

import com.app.android.homestay.Config;
import com.app.android.homestay.R;
import com.app.android.homestay.adapter.AdminUserListAdapter;
import com.app.android.homestay.base.BaseActivity;
import com.app.android.homestay.bean.UserBean;
import com.app.android.homestay.http.HttpStringCallback;
import com.app.android.homestay.utils.GsonUtils;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理
 */
public class AdminUserListActivity extends BaseActivity {
    private AdminUserListAdapter mListAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_list;
    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        mListAdapter = new AdminUserListAdapter();

        mRecyclerView.setAdapter(mListAdapter);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        queryUserList();

    }

    private void queryUserList() {

        OkGo.<String>get(Config.REGISTER_USER_URL)
                .execute(new HttpStringCallback(this) {
                    @Override
                    protected void onSuccess(String msg, String response) {

                        UserBean userBean = GsonUtils.parseJson(response, UserBean.class);
                        mListAdapter.setNewInstance(userBean.getList());
                    }

                    @Override
                    protected void onError(String response) {
                        BaseToast(response);
                    }
                });
    }
}
package com.app.android.homestay.fragment;

import androidx.recyclerview.widget.RecyclerView;

import com.app.android.homestay.Constants;
import com.app.android.homestay.R;
import com.app.android.homestay.adapter.UserOrderAdapter;
import com.app.android.homestay.base.BaseFragment;
import com.app.android.homestay.bean.OderInfoBean;
import com.app.android.homestay.http.HttpStringCallback;
import com.app.android.homestay.utils.GsonUtils;
import com.lzy.okgo.OkGo;

public class OrderFragment extends BaseFragment {
    private UserOrderAdapter mAdapter;
    private RecyclerView recyclerview;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initView() {
        recyclerview = mRootView.findViewById(R.id.recyclerview);

    }

    @Override
    protected void setListener() {

        mAdapter = new UserOrderAdapter();
        recyclerview.setAdapter(mAdapter);

    }

    @Override
    protected void initData() {

        queryAll();
    }


    public void queryAll() {
        String username = Constants.getUserInfo().getUsername();
        OkGo.<String>post(Constants.QUERY_ORDER_URL)
                .params("username", username)
                .execute(new HttpStringCallback(getActivity()) {
                    @Override
                    protected void onSuccess(String msg, String response) {

                        OderInfoBean infoBean = GsonUtils.parseJson(response, OderInfoBean.class);
                        mAdapter.setNewInstance(infoBean.getList());

                    }

                    @Override
                    protected void onError(String response) {
                        BaseToast(response);

                    }
                });

    }
}
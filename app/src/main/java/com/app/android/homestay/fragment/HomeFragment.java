package com.app.android.homestay.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.android.homestay.Config;
import com.app.android.homestay.R;
import com.app.android.homestay.adapter.HomeAdapter;
import com.app.android.homestay.base.BaseFragment;
import com.app.android.homestay.bean.HouseBean;
import com.app.android.homestay.bean.HouseInfo;
import com.app.android.homestay.http.HttpStringCallback;
import com.app.android.homestay.utils.GsonUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lzy.okgo.OkGo;

import org.jetbrains.annotations.NotNull;

public class HomeFragment extends BaseFragment {

    private RecyclerView recyclerview;
    private HomeAdapter mHomeAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        recyclerview = mRootView.findViewById(R.id.recyclerview);

        mHomeAdapter = new HomeAdapter();

        mHomeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("确定要添加到订单吗？");
                builder.setMessage("该订单可在订单列表中随时可删除");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        HouseInfo houseInfo = mHomeAdapter.getData().get(position);
                        addOrder(Config.getUserInfo().getUsername(), houseInfo);

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void initData() {

        recyclerview.setAdapter(mHomeAdapter);

        hostAll();

    }

    private void hostAll() {
        OkGo.<String>get(Config.HOUSE_LIST_URL)
                .execute(new HttpStringCallback(getActivity()) {
                    @Override
                    protected void onSuccess(String msg, String response) {

                        HouseBean houseBean = GsonUtils.parseJson(response, HouseBean.class);
                        mHomeAdapter.setNewInstance(houseBean.getList());

                    }

                    @Override
                    protected void onError(String response) {

                    }
                });
    }

    private void addOrder(String username, HouseInfo houseInfo) {

        OkGo.<String>post(Config.ADD_ORDER_URL)
                .params("username", username)
                .params("introduce", houseInfo.getIntroduce())
                .params("original_price", houseInfo.getOriginal_price())
                .params("discount_price", houseInfo.getDiscount_price())
                .params("address", houseInfo.getAddress())
                .params("house_img", houseInfo.getHouse_img())
                .execute(new HttpStringCallback(getActivity()) {
                    @Override
                    protected void onSuccess(String msg, String response) {
                        BaseToast(msg);
                    }

                    @Override
                    protected void onError(String response) {
                        BaseToast(response);
                    }
                });

    }
}
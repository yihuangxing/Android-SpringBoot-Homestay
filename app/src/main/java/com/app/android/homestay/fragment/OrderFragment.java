package com.app.android.homestay.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.android.homestay.Config;
import com.app.android.homestay.R;
import com.app.android.homestay.adapter.UserOrderAdapter;
import com.app.android.homestay.base.BaseFragment;
import com.app.android.homestay.bean.OderInfoBean;
import com.app.android.homestay.bean.OrderInfo;
import com.app.android.homestay.http.HttpStringCallback;
import com.app.android.homestay.utils.GsonUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.lzy.okgo.OkGo;

import org.jetbrains.annotations.NotNull;

public class OrderFragment extends BaseFragment {
    private UserOrderAdapter mOrderAdapter;
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

        mOrderAdapter = new UserOrderAdapter();
        mOrderAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(@NonNull @NotNull BaseQuickAdapter adapter, @NonNull @NotNull View view, int position) {
                OrderInfo orderInfo = mOrderAdapter.getData().get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("确定要删除该订单吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        del(orderInfo.getUid(), position);


                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();


                return true;
            }
        });
        recyclerview.setAdapter(mOrderAdapter);

    }

    @Override
    public void initData() {

        queryAll();
    }


    public void del(int uid, int position) {
        OkGo.<String>get(Config.ORDER_DEL_URL)
                .params("uid", uid)
                .execute(new HttpStringCallback(getActivity()) {
                    @Override
                    protected void onSuccess(String msg, String response) {
                        BaseToast(msg);
                        mOrderAdapter.removeAt(position);

                    }

                    @Override
                    protected void onError(String response) {
                        BaseToast(response);

                    }
                });

    }


    public void queryAll() {
        String username = Config.getUserInfo().getUsername();
        OkGo.<String>post(Config.QUERY_ORDER_URL)
                .params("username", username)
                .execute(new HttpStringCallback(getActivity()) {
                    @Override
                    protected void onSuccess(String msg, String response) {

                        OderInfoBean infoBean = GsonUtils.parseJson(response, OderInfoBean.class);
                        mOrderAdapter.setNewInstance(infoBean.getList());

                    }

                    @Override
                    protected void onError(String response) {
                        BaseToast(response);

                    }
                });

    }
}
package com.app.android.homestay.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.android.homestay.Config;
import com.app.android.homestay.R;
import com.app.android.homestay.adapter.PayListAdapter;
import com.app.android.homestay.base.BaseActivity;
import com.app.android.homestay.bean.OderInfoBean;
import com.app.android.homestay.bean.OrderInfo;
import com.app.android.homestay.http.HttpStringCallback;
import com.app.android.homestay.utils.GsonUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.lzy.okgo.OkGo;

import org.jetbrains.annotations.NotNull;

public class UserPayOrderActivity extends BaseActivity {
    private RecyclerView recyclerview;
    private PayListAdapter mPayListAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_order;
    }

    @Override
    protected void initView() {
        recyclerview = findViewById(R.id.recyclerview);

        mPayListAdapter = new PayListAdapter();
        mPayListAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(@NonNull @NotNull BaseQuickAdapter adapter, @NonNull @NotNull View view, int position) {
                OrderInfo orderInfo = mPayListAdapter.getData().get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(UserPayOrderActivity.this);
                builder.setTitle("确定要删除订单吗？");
                builder.setMessage("删除的订单将无法恢复，请谨慎删除");
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
        recyclerview.setAdapter(mPayListAdapter);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

        if (null != Config.getUserInfo().getUsername()) {
            queryAll(Config.getUserInfo().getUsername());
        }
    }

    public void queryAll(String username) {
        OkGo.<String>post(Config.QUERY_ORDER_URL)
                .params("username", username)
                .params("pay_status", 1)
                .execute(new HttpStringCallback(this) {
                    @Override
                    protected void onSuccess(String msg, String response) {

                        OderInfoBean infoBean = GsonUtils.parseJson(response, OderInfoBean.class);
                        mPayListAdapter.setNewInstance(infoBean.getList());

                    }

                    @Override
                    protected void onError(String response) {
                        BaseToast(response);

                    }
                });

    }

    public void del(int uid, int position) {
        OkGo.<String>get(Config.ORDER_DEL_URL)
                .params("uid", uid)
                .execute(new HttpStringCallback(this) {
                    @Override
                    protected void onSuccess(String msg, String response) {
                        BaseToast(msg);
                        mPayListAdapter.removeAt(position);

                    }

                    @Override
                    protected void onError(String response) {
                        BaseToast(response);

                    }
                });

    }
}
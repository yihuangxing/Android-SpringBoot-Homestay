package com.app.android.homestay.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.app.android.homestay.AdminMainActivity;
import com.app.android.homestay.Constants;
import com.app.android.homestay.R;
import com.app.android.homestay.adapter.AdminHouseListAdapter;
import com.app.android.homestay.base.BaseActivity;
import com.app.android.homestay.bean.HouseBean;
import com.app.android.homestay.bean.HouseInfo;
import com.app.android.homestay.http.HttpStringCallback;
import com.app.android.homestay.utils.GsonUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.lzy.okgo.OkGo;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 房间管理
 */
public class AdminHouseListActivity extends BaseActivity {
    private AdminHouseListAdapter mAdminHouseListAdapter;
    private List<HouseInfo> mStringList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private TextView add;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_admin_house_list;
    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        add = findViewById(R.id.add);

    }

    @Override
    protected void setListener() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(AdminHouseListActivity.this, AdminAddHouseActivity.class), 200);
            }
        });


    }

    @Override
    protected void initData() {

        mAdminHouseListAdapter = new AdminHouseListAdapter();
        mAdminHouseListAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                HouseInfo info = mAdminHouseListAdapter.getData().get(position);
                Intent intent = new Intent(AdminHouseListActivity.this, AdminUpdateHouseActivity.class);
                intent.putExtra("info", info);
                startActivityForResult(intent, 200);
            }
        });
        mAdminHouseListAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(@NonNull @NotNull BaseQuickAdapter adapter, @NonNull @NotNull View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminHouseListActivity.this);
                builder.setTitle("确定要删除吗？");
                builder.setMessage("删除之后需要重新上架,谨慎操作");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HouseInfo info = mAdminHouseListAdapter.getData().get(position);
                        del(info.getUid(), position);
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

        mAdminHouseListAdapter.setNewInstance(mStringList);
        mRecyclerView.setAdapter(mAdminHouseListAdapter);


        hostAll();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200) {
            hostAll();
        }
    }


    private void hostAll() {
        OkGo.<String>get(Constants.HOUSE_LIST_URL)
                .execute(new HttpStringCallback(this) {
                    @Override
                    protected void onSuccess(String msg, String response) {

                        HouseBean houseBean = GsonUtils.parseJson(response, HouseBean.class);
                        mAdminHouseListAdapter.setNewInstance(houseBean.getList());

                    }

                    @Override
                    protected void onError(String response) {

                    }
                });


    }

    private void del(int uid, int position) {

        OkGo.<String>get(Constants.DEL_HOUSE_URL)
                .params("uid", uid)
                .execute(new HttpStringCallback(null) {
                    @Override
                    protected void onSuccess(String msg, String response) {

                        mAdminHouseListAdapter.removeAt(position);
                        BaseToast(msg);
                    }

                    @Override
                    protected void onError(String response) {

                    }
                });
    }
}
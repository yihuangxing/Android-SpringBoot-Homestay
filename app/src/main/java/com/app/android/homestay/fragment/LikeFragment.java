package com.app.android.homestay.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.android.homestay.Config;
import com.app.android.homestay.R;
import com.app.android.homestay.adapter.CollectionAdapter;
import com.app.android.homestay.base.BaseFragment;
import com.app.android.homestay.bean.CollectionBean;
import com.app.android.homestay.bean.CollectionInfo;
import com.app.android.homestay.bean.HouseInfo;
import com.app.android.homestay.bean.UserInfo;
import com.app.android.homestay.http.HttpStringCallback;
import com.app.android.homestay.utils.GsonUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lzy.okgo.OkGo;

import org.jetbrains.annotations.NotNull;

public class LikeFragment extends BaseFragment {

    private CollectionAdapter mCollectionAdapter;
    private RecyclerView mRecyclerView;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_like;
    }

    @Override
    protected void initView() {
        mRecyclerView = mRootView.findViewById(R.id.recyclerview);
        mCollectionAdapter = new CollectionAdapter();

        mCollectionAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("确定要添加到订单吗？");
                builder.setMessage("添加后的订单可在订单列表中随时删除");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        CollectionInfo collectionInfo = mCollectionAdapter.getData().get(position);
                        addOrder(Config.getUserInfo().getUsername(), collectionInfo);

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


        mRecyclerView.setAdapter(mCollectionAdapter);
    }


    private void addOrder(String username, CollectionInfo collectionInfo) {

        OkGo.<String>post(Config.ADD_ORDER_URL)
                .params("username", username)
                .params("introduce", collectionInfo.getIntroduce())
                .params("original_price", collectionInfo.getOriginal_price())
                .params("discount_price", collectionInfo.getDiscount_price())
                .params("address", collectionInfo.getAddress())
                .params("house_img", collectionInfo.getHouse_img())
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

    @Override
    protected void setListener() {

    }

    @Override
    public void initData() {

        UserInfo userInfo = Config.getUserInfo();
        if (null != userInfo) {
            queryCollection(userInfo.getUsername());
        }
    }

    private void queryCollection(String username) {
        OkGo.<String>get(Config.COLLECTION_LIST_URL)
                .params("username", username)
                .execute(new HttpStringCallback(getActivity()) {
                    @Override
                    protected void onSuccess(String msg, String response) {
                        CollectionBean bean = GsonUtils.parseJson(response, CollectionBean.class);
                        mCollectionAdapter.setNewInstance(bean.getList());
                    }

                    @Override
                    protected void onError(String response) {

                    }
                });

    }
}
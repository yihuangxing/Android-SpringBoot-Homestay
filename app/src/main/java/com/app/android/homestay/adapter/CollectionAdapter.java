package com.app.android.homestay.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.android.homestay.Config;
import com.app.android.homestay.GlideEngine;
import com.app.android.homestay.R;
import com.app.android.homestay.bean.CollectionInfo;
import com.app.android.homestay.http.HttpStringCallback;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lzy.okgo.OkGo;

import org.jetbrains.annotations.NotNull;

/**
 * author : yi...
 * date   : 2021/11/17/0017  16:23
 * desc   :
 */
public class CollectionAdapter extends BaseQuickAdapter<CollectionInfo, BaseViewHolder> {

    private TextView original_price;

    public CollectionAdapter() {
        super(R.layout.collection_item);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, CollectionInfo collectionInfo) {

        GlideEngine.createGlideEngine().loadImage(getContext().getApplicationContext(), collectionInfo.getHouse_img(), baseViewHolder.getView(R.id.image));

        baseViewHolder.setText(R.id.title, collectionInfo.getIntroduce());
        baseViewHolder.setText(R.id.discount_price, "￥" + collectionInfo.getDiscount_price());
        original_price = baseViewHolder.getView(R.id.original_price);
        original_price.setText("原价" + collectionInfo.getOriginal_price());
        original_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        original_price.getPaint().setAntiAlias(true);

        //
        baseViewHolder.setText(R.id.address, collectionInfo.getAddress());

        baseViewHolder.getView(R.id.like).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("确定取消收藏吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        del(collectionInfo.getUid(), baseViewHolder.getLayoutPosition());
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


    private void del(int uid, int position) {
        OkGo.<String>get(Config.CANCEL_COLLECTION_URL)
                .params("uid", uid)
                .execute(new HttpStringCallback(null) {
                    @Override
                    protected void onSuccess(String msg, String response) {
                        removeAt(position);
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void onError(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

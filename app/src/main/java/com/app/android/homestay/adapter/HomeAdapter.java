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
import com.app.android.homestay.bean.HouseInfo;
import com.app.android.homestay.bean.UserInfo;
import com.app.android.homestay.http.HttpStringCallback;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lzy.okgo.OkGo;

import org.jetbrains.annotations.NotNull;

public class HomeAdapter extends BaseQuickAdapter<HouseInfo, BaseViewHolder> {

    private TextView original_price;


    public HomeAdapter() {
        super(R.layout.home_item);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, HouseInfo houseInfo) {
        GlideEngine.createGlideEngine().loadImage(getContext().getApplicationContext(), houseInfo.getHouse_img(), baseViewHolder.getView(R.id.image));

        baseViewHolder.setText(R.id.title, houseInfo.getIntroduce());
        baseViewHolder.setText(R.id.discount_price, "￥" + houseInfo.getDiscount_price());
        original_price = baseViewHolder.getView(R.id.original_price);
        original_price.setText("原价" + houseInfo.getOriginal_price());
        original_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        original_price.getPaint().setAntiAlias(true);
        //
        baseViewHolder.setText(R.id.address, houseInfo.getAddress());

        baseViewHolder.getView(R.id.like).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("是否添加收藏？");
                builder.setMessage("添加后可在收藏列表中查看");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserInfo userInfo = Config.getUserInfo();
                        if (userInfo != null) {
                            collection(userInfo.getUsername(), houseInfo);
                        }

                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }
        });
    }

    private void collection(String username, HouseInfo houseInfo) {

        OkGo.<String>post(Config.COLLECTION_URL)
                .params("collection_id", houseInfo.getUid())
                .params("introduce", houseInfo.getIntroduce())
                .params("original_price", houseInfo.getOriginal_price())
                .params("discount_price", houseInfo.getDiscount_price())
                .params("house_img", houseInfo.getHouse_img())
                .params("address", houseInfo.getAddress())
                .params("create_time", "")
                .params("username", username)
                .execute(new HttpStringCallback(null) {
                    @Override
                    protected void onSuccess(String msg, String response) {
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void onError(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                });


    }
}

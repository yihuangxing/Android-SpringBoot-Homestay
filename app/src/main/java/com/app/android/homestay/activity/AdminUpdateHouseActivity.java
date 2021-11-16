package com.app.android.homestay.activity;


import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.android.homestay.Constants;
import com.app.android.homestay.GlideEngine;
import com.app.android.homestay.R;
import com.app.android.homestay.base.BaseActivity;
import com.app.android.homestay.bean.HouseInfo;
import com.app.android.homestay.bean.JsonBean;
import com.app.android.homestay.http.HttpStringCallback;
import com.app.android.homestay.utils.GetJsonDataUtil;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.PostRequest;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AdminUpdateHouseActivity extends BaseActivity {
    private HouseInfo mHouseInfo;
    private EditText introduce;
    private EditText original_price;
    private EditText discount_price;
    private TextView address;
    private ImageView image;


    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();


    private String compressPath;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_admin_update_house;
    }

    @Override
    protected void initView() {
        introduce = findViewById(R.id.introduce);
        original_price = findViewById(R.id.original_price);
        discount_price = findViewById(R.id.discount_price);
        address = findViewById(R.id.address);
        image = findViewById(R.id.image);

    }

    @Override
    protected void setListener() {
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPickerView(address);
            }
        });

        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String introduceStr = introduce.getText().toString().trim();
                String originalprice = original_price.getText().toString().trim();
                String discountprice = discount_price.getText().toString().trim();
                String addressStr = address.getText().toString().trim();
                if (TextUtils.isEmpty(introduceStr)) {
                    BaseToast("请输入房间介绍的内容");
                } else if (TextUtils.isEmpty(originalprice)) {
                    BaseToast("请输入原价");
                } else if (TextUtils.isEmpty(discountprice)) {
                    BaseToast("请输入折扣价");
                } else if (TextUtils.isEmpty(addressStr)) {
                    BaseToast("请输入地址");
                } else {
                    if (mHouseInfo != null) {
                        update(mHouseInfo.getUid(), introduceStr, originalprice, discountprice, addressStr, compressPath);
                    }

                }
            }


        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(AdminUpdateHouseActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .imageEngine(GlideEngine.createGlideEngine())
                        .isCompress(true)
                        .selectionMode(PictureConfig.SINGLE)
                        .forResult(new OnResultCallbackListener<LocalMedia>() {
                            @Override
                            public void onResult(List<LocalMedia> result) {
                                if (result != null && result.size() > 0) {
                                    LocalMedia localMedia = result.get(0);
                                    compressPath = localMedia.getCompressPath();
                                    GlideEngine.createGlideEngine().loadImage(AdminUpdateHouseActivity.this, compressPath, image);
                                }


                            }

                            @Override
                            public void onCancel() {

                            }
                        });
            }
        });
    }


    private void update(int uid, String introduceStr, String originalprice, String discountprice, String addressStr, String compressPath) {

        PostRequest<String> post = OkGo.<String>post(Constants.HOUSE_UPDATE_URL);
        post.params("uid", uid);
        post.params("introduce", introduceStr);
        post.params("original_price", originalprice);
        post.params("discount_price", discountprice);
        post.params("address", addressStr);
        if (!TextUtils.isEmpty(compressPath)) {
            post.params("file", new File(compressPath));
        }
        post.execute(new HttpStringCallback(this) {
            @Override
            protected void onSuccess(String msg, String response) {
                BaseToast(msg);
                setResult(200);
                finish();
            }

            @Override
            protected void onError(String response) {
                BaseToast(response);
            }
        });


    }

    @Override
    protected void initData() {

        mHouseInfo = (HouseInfo) getIntent().getSerializableExtra("info");
        if (mHouseInfo != null) {
            introduce.setText(mHouseInfo.getIntroduce());
            original_price.setText(mHouseInfo.getOriginal_price());
            discount_price.setText(mHouseInfo.getDiscount_price());
            address.setText(mHouseInfo.getAddress());
            GlideEngine.createGlideEngine().loadImage(this, mHouseInfo.getHouse_img(), image);
        }

        initJsonData();

    }


    private void initJsonData() {


        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/
                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }

    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }


    private void showPickerView(TextView textView) {


        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";
                //如果tx 为null 说明没有选择地点
                textView.setText(opt1tx + "-" + opt2tx + "-" + opt3tx);
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();


    }
}
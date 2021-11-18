package com.app.android.homestay.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.app.android.homestay.Config;
import com.app.android.homestay.R;
import com.app.android.homestay.base.BaseActivity;
import com.app.android.homestay.http.HttpStringCallback;
import com.app.android.homestay.utils.CodeUtils;
import com.lzy.okgo.OkGo;

public class RegisterActivity extends BaseActivity {
    private String title;
    private ImageView ivcode;
    private EditText username, password, confirmpwd, mobile, email, et_code;
    private String codeStr;

    private int identity = 0;    //0普通用户     1管理员

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        ivcode = findViewById(R.id.iv_code);
        ivcode.setImageBitmap(CodeUtils.getInstance().createBitmap());
        codeStr = CodeUtils.getInstance().getCode();

        //
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmpwd = findViewById(R.id.confirmpwd);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        et_code = findViewById(R.id.et_code);

    }

    @Override
    protected void setListener() {
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                String newpwd = confirmpwd.getText().toString().trim();
                String phone = mobile.getText().toString().trim();
                String emailStr = email.getText().toString().trim();
                String code = et_code.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    BaseToast("请输入用户名");
                } else if (TextUtils.isEmpty(pwd)) {
                    BaseToast("请输入密码");
                } else if (TextUtils.isEmpty(newpwd)) {
                    BaseToast("请输入确定密码");
                } else if (!pwd.equals(newpwd)) {
                    BaseToast("两次密码不一致");
                } else if (TextUtils.isEmpty(phone)) {
                    BaseToast("请输入手机号");
                } else if (TextUtils.isEmpty(emailStr)) {
                    BaseToast("请输入邮箱地址");
                } else if (TextUtils.isEmpty(code)) {
                    BaseToast("请输入验证码");
                } else if (!codeStr.equals(code)) {
                    BaseToast("验证码输入错误");
                } else {
                    register(name, pwd, phone, emailStr, identity, "这个人很懒，什么都没有留下~");
                }
            }
        });

    }

    @Override
    protected void initData() {
        title = getIntent().getStringExtra("title");
        mToolbar.setTitle(title);

        if (title.equals("管理员注册")) {
            identity = 1;
        } else {
            identity = 0;
        }

    }


    private void register(String username, String password, String mobile, String email, int identity, String nickname) {
        OkGo.<String>get(Config.REGISTER_URL)
                .params("username", username)
                .params("password", password)
                .params("mobile", mobile)
                .params("email", email)
                .params("identity", identity)
                .params("nickname", nickname)
                .execute(new HttpStringCallback(mActivity) {
                    @Override
                    protected void onSuccess(String msg, String response) {
                        BaseToast(msg);
                        finish();
                    }

                    @Override
                    protected void onError(String response) {
                        BaseToast(response);
                    }
                });

    }
}
package com.app.android.homestay.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * author : yi...
 * date   : 2021/11/10/0010  9:51
 * desc   :
 */
public abstract class BaseFragment extends Fragment {
    protected View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        return mRootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

        setListener();

        initData();
    }


    protected abstract int getLayoutId();


    protected abstract void initView();


    protected abstract void setListener();


    public abstract void initData();


    protected void BaseToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}

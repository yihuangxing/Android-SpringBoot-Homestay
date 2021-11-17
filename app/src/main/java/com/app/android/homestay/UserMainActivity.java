package com.app.android.homestay;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.app.android.homestay.base.BaseActivity;
import com.app.android.homestay.fragment.CenterFragment;
import com.app.android.homestay.fragment.HomeFragment;
import com.app.android.homestay.fragment.LikeFragment;
import com.app.android.homestay.fragment.OrderFragment;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class UserMainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private HomeFragment mHomeFragment;
    private LikeFragment mLikeFragment;
    private OrderFragment mOrderFragment;
    private CenterFragment mCenterFragment;

    private BottomNavigationBar mBottomNavigationBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_main;
    }

    @Override
    protected void initView() {

        mBottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setActiveColor("#03A9F4") //选中颜色
                .setInActiveColor("#707070") //未选中颜色
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setBarBackgroundColor("#ffffff");//导航栏背景色


        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_home, "民宿").setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_home_normal)))
                .addItem(new BottomNavigationItem(R.mipmap.ic_like, "收藏").setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_like_normal)))
                .addItem(new BottomNavigationItem(R.mipmap.ic_order, "订单").setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_order_normal)))
                .addItem(new BottomNavigationItem(R.mipmap.ic_mine, "我的").setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.ic_mine_normal)))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        selectedFragment(0);

    }

    private void selectedFragment(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(fragmentTransaction);
        if (position == 0) {
            if (mHomeFragment == null) {
                mHomeFragment = new HomeFragment();
                fragmentTransaction.add(R.id.content, mHomeFragment);
            } else {
                fragmentTransaction.show(mHomeFragment);
            }
        } else if (position == 1) {
            if (mLikeFragment == null) {
                mLikeFragment = new LikeFragment();
                fragmentTransaction.add(R.id.content, mLikeFragment);
            } else {
                fragmentTransaction.show(mLikeFragment);
                mLikeFragment.initData();
            }
        } else if (position == 2) {
            if (mOrderFragment == null) {
                mOrderFragment = new OrderFragment();
                fragmentTransaction.add(R.id.content, mOrderFragment);
            } else {
                fragmentTransaction.show(mOrderFragment);
                mOrderFragment.queryAll();
            }
        } else {
            if (mCenterFragment == null) {
                mCenterFragment = new CenterFragment();
                fragmentTransaction.add(R.id.content, mCenterFragment);
            } else {
                fragmentTransaction.show(mCenterFragment);
            }
        }

        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction beginTransaction) {
        if (null != mHomeFragment) {
            beginTransaction.hide(mHomeFragment);
        }

        if (null != mCenterFragment) {
            beginTransaction.hide(mCenterFragment);
        }
        if (null != mLikeFragment) {
            beginTransaction.hide(mLikeFragment);
        }
        if (null != mOrderFragment) {
            beginTransaction.hide(mOrderFragment);
        }
    }

    @Override
    public void onTabSelected(int position) {
        selectedFragment(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
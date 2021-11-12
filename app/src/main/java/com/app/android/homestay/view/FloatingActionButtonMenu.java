package com.app.android.homestay.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.OvershootInterpolator;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.app.android.homestay.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * author : yi...
 * date   : 2021/11/10/0010  13:04
 * desc   :
 */
public class FloatingActionButtonMenu extends LinearLayoutCompat {

    private static final String TAG = FloatingActionButtonMenu.class.getSimpleName();
    private static final int ITEM_DELAY = 70;
    private static final int ITEM_ANIM_DURATION = 100;
    private FloatingActionButton mSwitchButton;
    private boolean mExpanded = false;
    private boolean mIsAnimRunning = false;
    private OnMenuItemClickListener mOnMenuItemClickListener;

    public FloatingActionButtonMenu(Context context) {
        this(context, null);
    }

    public FloatingActionButtonMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatingActionButtonMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override
    protected void onLayout(boolean changed, int l, final int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (null == mSwitchButton) {
            mSwitchButton = new FloatingActionButton(getContext());
            mSwitchButton.setImageResource(R.mipmap.logo);
            int size = (int) getResources().getDimension(R.dimen.design_fab_size_normal);
            LayoutParams params = new LayoutParams(size, size);
            params.leftMargin = getResources().getDimensionPixelSize(R.dimen.fab_margin);
            params.topMargin = getResources().getDimensionPixelSize(R.dimen.fab_margin);
            params.rightMargin = getResources().getDimensionPixelSize(R.dimen.fab_margin);
            params.bottomMargin = getResources().getDimensionPixelSize(R.dimen.fab_margin);

            mSwitchButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mExpanded && !mIsAnimRunning) {
                        collapse();
                    } else if (!mExpanded && !mIsAnimRunning) {
                        expand();
                    }
                }
            });
            addView(mSwitchButton, params);

            initMenuItems();
            hideMenuItems();
        }
    }

    private void initMenuItems() {
        for (int i = 0; i < getChildCount() - 1; i++) {
            View item = getChildAt(i);
            item.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (null != mOnMenuItemClickListener) {
                        mOnMenuItemClickListener.onMenuItemClick((FloatingActionButton) view, view.getId());
                    }
                    collapse();
                }
            });
            LayoutParams params = (LinearLayoutCompat.LayoutParams) item.getLayoutParams();
            params.topMargin = getResources().getDimensionPixelSize(R.dimen.fab_margin);
        }
    }

    private void hideMenuItems() {
        for (int i = 0; i < getChildCount() - 1; i++) {
            View item = getChildAt(i);
            item.setScaleX(0);
            item.setScaleY(0);
            item.setVisibility(View.GONE);
        }
    }

    private void showMenuItems() {
        for (int i = 0; i < getChildCount() - 1; i++) {
            View item = getChildAt(i);
            item.setVisibility(View.VISIBLE);
        }
    }

    public void expand() {
        mExpanded = true;
        List<Animator> itemAnims = new ArrayList<>();
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator switchAnim = ObjectAnimator.ofFloat(mSwitchButton, "rotation", -225);
        switchAnim.setInterpolator(new OvershootInterpolator());
        itemAnims.add(switchAnim);

        showMenuItems();
        for (int i = getChildCount() - 2; i >= 0; i--) {
            View item = getChildAt(i);
            PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0, 1);
            PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0, 1);
            ObjectAnimator itemAnim = ObjectAnimator.ofPropertyValuesHolder(item, scaleX, scaleY);
            itemAnim.setDuration(ITEM_ANIM_DURATION);
            itemAnim.setStartDelay(ITEM_DELAY * (getChildCount() - 1 - i));
            itemAnim.setInterpolator(new OvershootInterpolator());
            itemAnims.add(itemAnim);
        }
        set.playTogether(itemAnims);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                mIsAnimRunning = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mIsAnimRunning = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.start();
    }

    public void collapse() {
        mExpanded = false;
        List<Animator> itemAnims = new ArrayList<>();
        AnimatorSet set = new AnimatorSet();

        ObjectAnimator switchAnim = ObjectAnimator.ofFloat(mSwitchButton, "rotation", 0);
        switchAnim.setInterpolator(new AnticipateOvershootInterpolator());
        itemAnims.add(switchAnim);

        for (int i = 0; i < getChildCount() - 1; i++) {
            View item = getChildAt(i);
            PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1, 0);
            PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1, 0);
            ObjectAnimator itemAnim = ObjectAnimator.ofPropertyValuesHolder(item, scaleX, scaleY);
            itemAnim.setDuration(ITEM_ANIM_DURATION);
            itemAnim.setStartDelay(ITEM_DELAY * i);
            itemAnims.add(itemAnim);
        }
        set.playTogether(itemAnims);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                mIsAnimRunning = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                hideMenuItems();
                mIsAnimRunning = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.start();
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
        mOnMenuItemClickListener = listener;
    }

    public interface OnMenuItemClickListener {
        void onMenuItemClick(FloatingActionButton button, int btnId);
    }

    public boolean isExpanded() {
        return mExpanded;
    }
}

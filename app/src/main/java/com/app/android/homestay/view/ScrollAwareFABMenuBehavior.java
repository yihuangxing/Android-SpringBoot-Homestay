package com.app.android.homestay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.app.android.homestay.R;

/**
 * author : yi...
 * date   : 2021/11/10/0010  13:08
 * desc   :
 */
public class ScrollAwareFABMenuBehavior extends CoordinatorLayout.Behavior<FloatingActionButtonMenu> {

    private static final android.view.animation.Interpolator INTERPOLATOR =
            new FastOutSlowInInterpolator();
    private boolean mIsAnimatingOut = false;

    public ScrollAwareFABMenuBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       FloatingActionButtonMenu child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||
                super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target,
                        nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButtonMenu child,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed,
                dyUnconsumed);
        if (dyConsumed > 5 && !this.mIsAnimatingOut && child.getVisibility() == View.VISIBLE) {
            if (child.isExpanded()) {
                child.collapse();
            }
            animateOut(child);
        } else if (dyConsumed < -5 && child.getVisibility() != View.VISIBLE) {
            animateIn(child);
        }
    }

    // Same animation that FloatingActionButton.Behavior uses to
    // hide the FAB when the AppBarLayout exits
    private void animateOut(final FloatingActionButtonMenu menu) {
        Animation anim = AnimationUtils.loadAnimation(menu.getContext(), R.anim.hide_to_bottom);
        anim.setInterpolator(INTERPOLATOR);
        anim.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
                ScrollAwareFABMenuBehavior.this.mIsAnimatingOut = true;
            }

            public void onAnimationEnd(Animation animation) {
                ScrollAwareFABMenuBehavior.this.mIsAnimatingOut = false;
                menu.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(final Animation animation) {
            }
        });
        menu.startAnimation(anim);
    }

    // Same animation that FloatingActionButton.Behavior
    // uses to show the FAB when the AppBarLayout enters
    private void animateIn(FloatingActionButtonMenu menu) {
        menu.setVisibility(View.VISIBLE);
        Animation anim = AnimationUtils.loadAnimation(menu.getContext(), R.anim.show_from_bottom);
        anim.setInterpolator(INTERPOLATOR);
        menu.startAnimation(anim);
    }
}

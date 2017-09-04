package com.renny.translate.helper;

import android.animation.Animator;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by LuckyCrystal on 2017/9/2.
 *
 */

public class CircularRevealAnimator {

    public static Builder builder(@NonNull View view) {
        return new Builder(view);
    }

    public static class Builder {

        private boolean afterVisibility;
        private View view;
        private int mDuration = 500;
        private Interpolator interpolator = new LinearInterpolator();
        int centerX, centerY;
        float startRadius, endRadius;


        private Builder(@NonNull View view) {
            this.view = view;
        }

        public Builder setDuration(int duration) {
            mDuration = duration;
            return this;
        }

        public Builder setAfterVisibility(boolean afterVisibility) {
            this.afterVisibility = afterVisibility;
            return this;
        }

        public Builder setCenter(int centerX, int centerY) {
            this.centerX = centerX;
            this.centerY = centerY;
            return this;
        }

        public Builder setStartRadius(float startRadius) {
            this.startRadius = startRadius;
            return this;
        }

        public Builder setEndRadius(float endRadius) {
            this.endRadius = endRadius;
            return this;
        }

        public Builder setInterpolator(Interpolator interpolator) {
            this.interpolator = interpolator;
            return this;
        }

        public void start() {
            if (afterVisibility) {
                view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                        animator();
                        v.removeOnLayoutChangeListener(this);
                    }
                });
            } else {
                animator();
            }

        }

        private void animator() {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                Animator animator = ViewAnimationUtils.createCircularReveal(
                        view,
                        centerX,
                        centerY,
                        startRadius,
                        endRadius);
                animator.setInterpolator(interpolator);
                animator.setDuration(mDuration);
                animator.start();
            }

        }
    }
}

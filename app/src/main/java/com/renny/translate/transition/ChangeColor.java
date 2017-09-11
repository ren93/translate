package com.renny.translate.transition;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LuckyCrystal on 2017/9/4.
 */

public class ChangeColor extends Transition {


    private static final String PROPNAME_BACKGROUND =
            "changecolor:background";

    // 开始的状态，这里会对视图树中所有的View调用，这里我们可以记录一下View的我们感兴趣的状态，比如这里：background
    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        if (transitionValues.view.getBackground() instanceof ColorDrawable) {
            captureValues(transitionValues);
        }
    }

    // 结束也会对所有的View进行调用
    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        if (transitionValues.view.getBackground() instanceof ColorDrawable) {
            captureValues(transitionValues);
        }
    }

    private void captureValues(TransitionValues transitionValues) {
        transitionValues.values.put(PROPNAME_BACKGROUND, ((ColorDrawable) transitionValues.view.getBackground()).getColor());
    }

    //新建动画
    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        if (null == startValues || null == endValues) {
            return null;
        }
        final View view = endValues.view;
        int startBackground = (Integer) startValues.values.get(PROPNAME_BACKGROUND);
        int endBackground = (Integer) endValues.values.get(PROPNAME_BACKGROUND);

        if (startBackground != endBackground) {
            ValueAnimator animator = ValueAnimator.ofObject(new ArgbEvaluator(),
                    startBackground, endBackground);
            animator.setDuration(getDuration());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Object value = animation.getAnimatedValue();
                    if (null != value) {
                        view.setBackgroundColor((Integer) value);
                    }
                }
            });
            return animator;
        }
        return null;
    }

}
package com.renny.translate.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LuckyCrystal on 2017/9/4.
 */

public class ChangeRect extends Transition {

    private static final String PROPNAME_BER =
            "changeposition:Rect";

    // 开始的状态，这里会对视图树中所有的View调用，这里我们可以记录一下View的我们感兴趣的状态，比如这里：background
    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        Log.d("xxx","captureStartValues");
        captureValues(transitionValues);

    }

    // 结束也会对所有的View进行调用
    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        Log.d("xxx","captureEndValues"+transitionValues.view);
        captureValues(transitionValues);
    }

    private void captureValues(TransitionValues transitionValues) {
        float[] location = new float[2];
        location[0] = transitionValues.view.getX();
        location[1] = transitionValues.view.getY();
        transitionValues.values.put(PROPNAME_BER, location);
    }

    //新建动画
    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        Log.d("xxx1","createAnimator"+startValues+endValues);
        if (null == startValues || null == endValues) {
            return null;
        }

        final View view = endValues.view;
        float[] startPosition = (float[]) startValues.values.get(PROPNAME_BER);
        float[] endPosition = (float[]) endValues.values.get(PROPNAME_BER);

        if (startPosition[0] != endPosition[0] || startPosition[1] != endPosition[1]) {
            Path path=new Path();
            path.moveTo(startPosition[0],startPosition[1]);
            path.lineTo(endPosition[0],startPosition[1]);
            path.lineTo(endPosition[0],endPosition[1]);
            ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.X, View.Y, path);
            animator.setDuration(getDuration());
            animator.start();

            return animator;
        }
        return null;
    }


}
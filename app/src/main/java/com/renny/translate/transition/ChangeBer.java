package com.renny.translate.transition;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

import com.renny.translate.evaluator.BezierEvaluator;

/**
 * Created by LuckyCrystal on 2017/9/4.
 */

public class ChangeBer extends Transition {


    private static final String PROPNAME_BER =
            "changeposition:ber";

    // 开始的状态，这里会对视图树中所有的View调用，这里我们可以记录一下View的我们感兴趣的状态，比如这里：background
    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);

    }

    // 结束也会对所有的View进行调用
    @Override
    public void captureEndValues(TransitionValues transitionValues) {
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
        if (null == startValues || null == endValues) {
            return null;
        }
        final View view = endValues.view;
        float[] startPosition = (float[]) startValues.values.get(PROPNAME_BER);
        float[] endPosition = (float[]) endValues.values.get(PROPNAME_BER);

        if (startPosition[0] != endPosition[0] || startPosition[1] != endPosition[1]) {
            PointF pointF = new PointF((float) (Math.abs(startPosition[0] + endPosition[0]) * 0.7),
                    (float) (Math.abs(startPosition[1] + endPosition[1]) * 0.3));
            if (startPosition[1] > endPosition[1]) {
                pointF = new PointF((float) (Math.abs(startPosition[0] + endPosition[0]) * 0.3),
                        (float) (Math.abs(startPosition[1] + endPosition[1]) * 0.3));
            }
            if (startPosition[1] == endPosition[1]) {
                pointF = new PointF((float) (Math.abs(startPosition[0] + endPosition[0]) * 0.5),
                        (float) (Math.abs(startPosition[1] + endPosition[1]) * 0.7));
            }
            ValueAnimator animator = ValueAnimator.ofObject(new BezierEvaluator(pointF),
                    new PointF(startPosition[0], startPosition[1]), new PointF(endPosition[0], endPosition[1]));
            animator.setDuration(1000);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    PointF pointF = (PointF) animation.getAnimatedValue();
                    view.setX(pointF.x);
                    view.setY(pointF.y);
                    view.invalidate();
                }
            });
            return animator;
        }
        return null;
    }


}
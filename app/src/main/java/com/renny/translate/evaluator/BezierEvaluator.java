package com.renny.translate.evaluator;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by LuckyCrystal on 2017/9/4.
 */


public class BezierEvaluator implements TypeEvaluator<PointF> {

    private PointF mPointF;

    public BezierEvaluator(PointF mPointF) {
        this.mPointF = mPointF;
    }

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        return calculateBezierPointForQuadratic(fraction, startValue, mPointF, endValue);
    }
    /**
     * B(t) = (1 - t)^2 * P0 + 2t * (1 - t) * P1 + t^2 * P2, t ∈ [0,1]
     *
     * @param t  曲线长度比例
     * @param p0 起始点
     * @param p1 控制点
     * @param p2 终止点
     * @return t对应的点
     */
    private PointF calculateBezierPointForQuadratic(float t, PointF p0, PointF p1, PointF p2) {
        PointF point = new PointF();
        float temp = 1 - t;
        point.x = temp * temp * p0.x + 2 * t * temp * p1.x + t * t * p2.x;
        point.y = temp * temp * p0.y + 2 * t * temp * p1.y + t * t * p2.y;
        return point;
    }
}


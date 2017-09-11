package com.renny.translate.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.renny.translate.R;

import java.util.ArrayList;
import java.util.List;


public class TransPathView extends View implements ValueAnimator.AnimatorUpdateListener,
        Animator.AnimatorListener {

    private static final String LOG_TAG = "TransPathView";

    private static final int DEFAULT_DURATION = 800;
    private static final int STROKE = 1;
    private static final int FILL = 2;
    private static final int STROKE_FILL = 4;
    private static final int STROKE_COLOR = Color.parseColor("#fa6262");
    private static final int FILL_COLOR = Color.parseColor("#47a4ec");
    private static final int VIEW_PORT_WIDTH = 100;
    private static final int VIEW_PORT_HEIGHT = 100;
    private static final int STROKE_WIDTH = 4;

    private int duration;

    private String path1;
    private String path2;

    private String fromPath;
    private String toPath;

    private int strokeType;

    private int strokeColor;

    private float strokeWidth;

    private float viewportWidth = 240;

    private float viewportHeight = 240;

    private boolean withRotate = false;

    private float rotate = 0;

    private Path path = new Path();

    private List<SVGAction> actions = new ArrayList<>();

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float scale = 1;

    private ValueAnimator anim;

    private float fraction;

    public TransPathView(Context context) {
        this(context, null);
    }

    public TransPathView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TransPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadAttrs(context, attrs);
    }

    private void loadAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TransPathView);
        strokeType = a.getInt(R.styleable.TransPathView_strokeType, STROKE);
        strokeColor = a.getColor(R.styleable.TransPathView_strokeColor, STROKE_COLOR);
        viewportHeight = a.getInt(R.styleable.TransPathView_viewPortHeight, VIEW_PORT_HEIGHT);
        viewportWidth = a.getInt(R.styleable.TransPathView_viewPortWidth, VIEW_PORT_WIDTH);
        duration = a.getInt(R.styleable.TransPathView_animDuration, DEFAULT_DURATION);
        strokeWidth = a.getDimension(R.styleable.TransPathView_strokeWidth, STROKE_WIDTH);
        path1 = a.getString(R.styleable.TransPathView_path1);
        path2 = a.getString(R.styleable.TransPathView_path2);
        a.recycle();
        Log.d("trasn", "strokeType" + strokeType + "strokeColor" + strokeColor);
        Log.d("trasn", "viewportHeight" + viewportHeight + "viewportWidth" + viewportWidth);
        if (!TextUtils.isEmpty(path1) && !TextUtils.isEmpty(path2)) {
            setPaths(path1,path2);
        }

        if (strokeType == STROKE) {
            paint.setStyle(Paint.Style.STROKE);
        } else if (strokeType == FILL) {
            paint.setStyle(Paint.Style.FILL);
        } else {
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
        }

        paint.setColor(strokeColor);
        paint.setStrokeWidth(strokeWidth);
    }

    public void setPaths(String path1, String path2) {
        this.path1 = path1;
        this.path2 = path2;
        if (path1 == null || path1.isEmpty() || path2 == null || path2.isEmpty()) {
            Log.e(LOG_TAG, "pathString is null.");
            return;
        }
        fromPath = format(path1);
        toPath = format(path2);
        buildActions();
    }

    public void setViewPort(float viewportWidth, float viewportHeight) {
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        float viewportS = viewportHeight / viewportWidth;
        float viewS = 1.0f * height / width;
        if (viewportS == viewS) {

        } else if (viewportS > viewS) {
            width = (int) (height / viewportS);
        } else {
            height = (int) (width * viewportS);
        }

        setMeasuredDimension(width, height);

        scale = Math.min(width / viewportWidth, height / viewportHeight);
        initShow();
    }

    public void setStrokeType(int strokeType) {
        this.strokeType = strokeType;
    }

    private void buildActions() {
        String[] arr1 = fromPath.split("--");
        String[] arr2 = toPath.split("--");
        if (arr1.length != arr2.length) {
            Log.e(LOG_TAG, "The length of path1 do not equals path2.");
            return;
        }
        actions.clear();
        for (int i = 0; i < arr1.length; i++) {
            String str1 = arr1[i];
            String str2 = arr2[i];
            SVGAction action = new SVGAction();
            if (str1.equalsIgnoreCase(SVGAction.ACTION_Z) && str2.equalsIgnoreCase(SVGAction.ACTION_Z)) {
                action.setAction(SVGAction.ACTION_Z);
            } else {
                String actionStr1 = str1.substring(0, 1);
                String actionStr2 = str2.substring(0, 1);
                if (!actionStr1.equals(actionStr2)) {
                    Log.e(LOG_TAG, "path1 is not suitable for path2.");
                    return;
                }
                String valueStr1 = str1.substring(1, str1.length()).trim();
                String valueStr2 = str2.substring(1, str2.length()).trim();
                String[] values1 = valueStr1.split(",");
                String[] values2 = valueStr2.split(",");

                List<Float> valueFrom = new ArrayList<>();
                for (String value : values1) {
                    try {
                        valueFrom.add(Float.parseFloat(value)+50);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                List<Float> valueTo = new ArrayList<>();
                for (String value : values2) {
                    try {
                        valueTo.add(Float.parseFloat(value)+50);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                }

                action.setAction(actionStr1);

                action.setValueFrom(valueFrom);
                action.setValueTo(valueTo);
            }
            actions.add(action);
        }
    }

    private String format(String path) {
        String trimPath = path.replace(" ", "");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < trimPath.length(); i++) {
            char item = trimPath.charAt(i);
            if ((item >= 'A' && item <= 'Z' || item >= 'a' && item <= 'z') && i != 0) {
                sb.append("--");
                sb.append(item);
            } else {
                sb.append(item);
            }
        }
        return sb.toString();
    }

    public void startTransWithRotate(float rotate) {
        this.withRotate = true;
        this.rotate = rotate;
        startTrans();
    }

    public void startTransWithOutRotate() {

        this.withRotate = false;
        this.rotate = 0;
        startTrans();
    }

    private void startTrans() {
        if (actions == null || actions.isEmpty()) {
            return;
        }
        recycler();
        anim = ValueAnimator.ofFloat(0, 1);
        anim.setDuration(duration);
        anim.addUpdateListener(this);
        anim.addListener(this);
        anim.start();
    }

    private void exchangePath1AndPath2() {
        fromPath = format(path2);
        toPath = format(path1);
    }

    private void actionPath(SVGAction action) {
        List<Float> value = action.getValue();
        switch (action.getAction().toUpperCase()) {
            case SVGAction.ACTION_M:
                path.moveTo(value.get(0) * scale, value.get(1) * scale);
                break;
            case SVGAction.ACTION_L:
                path.lineTo(value.get(0) * scale, value.get(1) * scale);
                break;
            case SVGAction.ACTION_Q:
                path.quadTo(value.get(0) * scale, value.get(1) * scale, value.get(2) * scale, value.get(3) * scale);
                break;
            case SVGAction.ACTION_C:
                path.cubicTo(value.get(0) * scale, value.get(1) * scale, value.get(2) * scale, value.get(3) * scale, value.get(4) * scale, value.get(5) * scale);
                break;
            case SVGAction.ACTION_Z:
                path.close();
                break;
        }
    }

    private void initShow() {
        path.reset();
        for (SVGAction a : actions) {
            actionPath(a);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void recycler() {
        if (anim != null) {
            anim.cancel();
            anim.removeAllListeners();
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float fraction = valueAnimator.getAnimatedFraction();
        setFraction(fraction);
    }

    private void refresh() {
        for (SVGAction a : actions) {
            if (a.getAction().equalsIgnoreCase(SVGAction.ACTION_Z)) {
                continue;
            }
            a.computeValue(fraction);
        }

        path.reset();
        for (SVGAction a : actions) {
            actionPath(a);
        }
        invalidate();

        if (withRotate) {
            setRotation(fraction * rotate);
        }
    }

    @Override
    public void onAnimationStart(Animator animator) {

    }

    @Override
    public void onAnimationEnd(Animator animator) {
        exchangePath1AndPath2();
        buildActions();
    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }

    public float getFraction() {
        return fraction;
    }

    public void setFraction(float fraction) {
        this.fraction = fraction;
        this.refresh();
    }

    public static class SVGAction {

        public static final String ACTION_M = "M";
        public static final String ACTION_L = "L";
        public static final String ACTION_C = "C";
        public static final String ACTION_Q = "Q";
        public static final String ACTION_Z = "Z";

        private String action;

        private List<Float> value = new ArrayList<>();

        private List<Float> valueFrom;

        private List<Float> valueTo;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public List<Float> getValue() {
            return value;
        }

        public void computeValue(float fraction) {
            for (int i = 0; i < valueFrom.size(); i++) {
                float from = valueFrom.get(i);
                float to = valueTo.get(i);
                float current = from + ((to - from) * fraction);
                value.set(i, current);
            }
        }

        public void setValueFrom(List<Float> valueFrom) {
            this.valueFrom = valueFrom;
            value.clear();
            for (float f : valueFrom) {
                value.add(f);
            }
        }

        public void setValueTo(List<Float> valueTo) {
            this.valueTo = valueTo;
        }
    }


}